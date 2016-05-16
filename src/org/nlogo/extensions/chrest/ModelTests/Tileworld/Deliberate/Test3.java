package org.nlogo.extensions.chrest.ModelTests.Tileworld.Deliberate;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import jchrest.architecture.Chrest;
import jchrest.architecture.Node;
import jchrest.architecture.Perceiver;
import jchrest.architecture.Stm;
import jchrest.domainSpecifics.Fixation;
import jchrest.domainSpecifics.Scene;
import jchrest.domainSpecifics.SceneObject;
import jchrest.domainSpecifics.fixations.PeripheralSquareFixation;
import jchrest.domainSpecifics.tileworld.TileworldDomain;
import jchrest.lib.HistoryTreeMap;
import jchrest.lib.ItemSquarePattern;
import jchrest.lib.ListPattern;
import jchrest.lib.Modality;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;
import org.nlogo.extensions.chrest.ChrestExtension;

/**
 *
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class Test3 extends DefaultReporter {
  
  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(Syntax.BooleanType());
  }

  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    
    Chrest turtlesModel = ChrestExtension.getTurtlesChrestInstance(context);
    
    boolean tileIncludedInHypothesis = false;
    
    ListPattern nodeContents = new ListPattern(Modality.VISUAL);
    String item = this.selectRandomObject();
    if (item.equals(TileworldDomain.TILE_SCENE_OBJECT_TYPE_TOKEN)) tileIncludedInHypothesis = true;
    nodeContents.add(new ItemSquarePattern(item, 0, 1));
    
    ListPattern nodeImage = new ListPattern(Modality.VISUAL);
    item = this.selectRandomObject();
    if (item.equals(TileworldDomain.TILE_SCENE_OBJECT_TYPE_TOKEN)) tileIncludedInHypothesis = true;
    nodeImage.add(new ItemSquarePattern(item, 0, 1));
    
    item = this.selectRandomObject();
    if (item.equals(TileworldDomain.TILE_SCENE_OBJECT_TYPE_TOKEN)) tileIncludedInHypothesis = true;
    ItemSquarePattern filledItemSlot = new ItemSquarePattern(item, 0, 1);
    ArrayList filledItemSlots = new ArrayList();
    filledItemSlots.add(filledItemSlot);
    HistoryTreeMap filledItemSlotsHistory = new HistoryTreeMap();
    filledItemSlotsHistory.put(1, filledItemSlots);
    
    item = this.selectRandomObject();
    if (item.equals(TileworldDomain.TILE_SCENE_OBJECT_TYPE_TOKEN)) tileIncludedInHypothesis = true;
    ItemSquarePattern filledPositionSlot = new ItemSquarePattern(item, 0, 1);
    ArrayList filledPositionSlots = new ArrayList();
    filledPositionSlots.add(filledPositionSlot);
    HistoryTreeMap filledPositionSlotsHistory = new HistoryTreeMap();
    filledPositionSlotsHistory.put(1, filledPositionSlots);
    
    //Sets content and image
    Node node = new Node(turtlesModel, nodeContents, nodeImage, 0);
    
    try {
      
      /************************************/
      /***** SET FILLED SLOTS HISTORY *****/
      /************************************/
      
      Field filledItemSlotsHistoryField = Node.class.getDeclaredField("_filledItemSlotsHistory");
      Field filledPositionSlotsHistoryField = Node.class.getDeclaredField("_filledPositionSlotsHistory");
      filledItemSlotsHistoryField.setAccessible(true);
      filledPositionSlotsHistoryField.setAccessible(true);
      
      filledItemSlotsHistoryField.set(node, filledItemSlotsHistory);
      filledPositionSlotsHistoryField.set(node, filledPositionSlotsHistory);
      
      /*************************/
      /***** SET FIXATIONS *****/
      /*************************/
      
      //Construct the Scene fixated on first.
      Scene sceneFixatedOn = new Scene("", 5, 5, 0, 0, null);
      
      //Populate the Scene fixated on.  To do this the test needs the Scene's 
      //actual scene data structure and its height/width.
      Field sceneSceneField = Scene.class.getDeclaredField("_scene");
      Field sceneHeightField = Scene.class.getDeclaredField("_height");
      Field sceneWidthField = Scene.class.getDeclaredField("_width");
      sceneSceneField.setAccessible(true);
      sceneHeightField.setAccessible(true);
      sceneWidthField.setAccessible(true);
      
      ArrayList<ArrayList<SceneObject>> scene = (ArrayList)sceneSceneField.get(sceneFixatedOn);
      for(int col = 0; col < sceneWidthField.getInt(sceneFixatedOn); col++){
        for(int row = 0; row < sceneHeightField.getInt(sceneFixatedOn); row++){
          scene.get(col).set(row, new SceneObject(Scene.EMPTY_SQUARE_TOKEN));
        }
      }
      scene.get(2).set(2, new SceneObject(Scene.CREATOR_TOKEN));
      
      //Construct a new Fixation that fixates on the tile in the Scene fixated 
      //on.  Note that the Fixation will be considered as being performed so is
      //viable for consideration during problem-solving.
      PeripheralSquareFixation fixation = new PeripheralSquareFixation(turtlesModel, 0);
      Field fixationPerformanceTimeField = Fixation.class.getDeclaredField("_performanceTime");
      Field fixationPerformedField = Fixation.class.getDeclaredField("_performed");
      Field fixationSceneField = Fixation.class.getDeclaredField("_scene");
      Field fixationColFixatedOnField = Fixation.class.getDeclaredField("_colFixatedOn");
      Field fixationRowFixatedOnField = Fixation.class.getDeclaredField("_rowFixatedOn");
      Field fixationObjectSeenField = Fixation.class.getDeclaredField("_objectSeen");
      
      fixationPerformanceTimeField.setAccessible(true);
      fixationPerformedField.setAccessible(true);
      fixationSceneField.setAccessible(true);
      fixationColFixatedOnField.setAccessible(true);
      fixationRowFixatedOnField.setAccessible(true);
      fixationObjectSeenField.setAccessible(true);
      
      fixationPerformanceTimeField.set(fixation, 1);
      fixationPerformedField.set(fixation, true);
      fixationSceneField.set(fixation, sceneFixatedOn);
      fixationColFixatedOnField.set(fixation, 2);
      fixationRowFixatedOnField.set(fixation, 0);
      fixationObjectSeenField.set(
        fixation, 
        scene.get((Integer)fixationColFixatedOnField.get(fixation))
          .get((Integer)fixationRowFixatedOnField.get(fixation)) 
      );
       
      //Include this Fixation in the "Fixations Attempted" data structure found 
      //in the Perceiver associated with the calling turtle's CHREST model.
      ArrayList<Fixation> fixations = new ArrayList();
      fixations.add(fixation);
      HistoryTreeMap fixationsHistory = new HistoryTreeMap();
      fixationsHistory.put(1, fixations);
      
      Field chrestPerceiverField = Chrest.class.getDeclaredField("_perceiver");
      Field perceiverFixationsField = Perceiver.class.getDeclaredField("_fixations");
      perceiverFixationsField.setAccessible(true);
      chrestPerceiverField.setAccessible(true);
      
      perceiverFixationsField.set(chrestPerceiverField.get(turtlesModel), fixationsHistory);
      
      /**************************/
      /***** SET VISUAL STM *****/
      /**************************/
      
      //Need to get the visual STM of the calling turtle's CHREST model.  This 
      //is a private field so needs to be made accessible so it can be set 
      //"manually".
      Field chrestVisualStmField = Chrest.class.getDeclaredField("_visualStm");
      chrestVisualStmField.setAccessible(true);
      
      //Need to get the item history of visual STM for the calling turtle's 
      //CHREST model.  This is a private field so needs to be made accessible so 
      //it can be set "manually".
      Field stmItemHistoryField = Stm.class.getDeclaredField("_itemHistory");
      stmItemHistoryField.setAccessible(true);
      
      //Add the Node to Visual STM at a time when its slots are filled, i.e. 1.
      ArrayList<Node> visualStmState = new ArrayList();
      visualStmState.add(node);
      
      HistoryTreeMap visualStm = (HistoryTreeMap)stmItemHistoryField.get(chrestVisualStmField.get(turtlesModel));
      visualStm.put(1, visualStmState);
      
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
      Logger.getLogger(Test3.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    return tileIncludedInHypothesis;
  }
  
  private String selectRandomObject(){
    ArrayList<String> tileworldObjects = new ArrayList();
    tileworldObjects.add(TileworldDomain.HOLE_SCENE_OBJECT_TYPE_TOKEN);
    tileworldObjects.add(TileworldDomain.OPPONENT_SCENE_OBJECT_TYPE_TOKEN);
    tileworldObjects.add(TileworldDomain.TILE_SCENE_OBJECT_TYPE_TOKEN);
    tileworldObjects.add(Scene.EMPTY_SQUARE_TOKEN);
      
    Random r = new Random();
    
    return tileworldObjects.get(r.nextInt(tileworldObjects.size()));
  }
  
}
