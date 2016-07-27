package org.nlogo.extensions.chrest.ModelTests.Tileworld.GeneratePlan;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import jchrest.architecture.Chrest;
import jchrest.architecture.Node;
import jchrest.architecture.Stm;
import jchrest.architecture.VisualSpatialField;
import jchrest.domainSpecifics.tileworld.TileworldDomain;
import jchrest.lib.HistoryTreeMap;
import jchrest.lib.ItemSquarePattern;
import jchrest.lib.ListPattern;
import jchrest.lib.Modality;
import jchrest.lib.Square;
import jchrest.lib.VisualSpatialFieldObject;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultCommand;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;
import org.nlogo.extensions.chrest.ChrestExtension;

/**
 *
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class Test1 extends DefaultCommand {
  
  @Override
  public Syntax getSyntax(){
    return Syntax.commandSyntax(new int[]{
      Syntax.NumberType(),
      Syntax.NumberType()
    });
  }

  @Override
  public void perform(Argument[] args, Context context) throws ExtensionException, LogoException {
    
    try {
      Field visualSpatialFieldField = VisualSpatialField.class.getDeclaredField("_visualSpatialField");
      visualSpatialFieldField.setAccessible(true);
          
      Field nodeProductionHistoryField = Node.class.getDeclaredField("_productionHistory");
      nodeProductionHistoryField.setAccessible(true);
      
      Field chrestVisualStmField = Chrest.class.getDeclaredField("_visualStm");
      chrestVisualStmField.setAccessible(true);
      
      Field stmItemHistoryField = Stm.class.getDeclaredField("_itemHistory");
      stmItemHistoryField.setAccessible(true);
      
      Chrest turtlesChrestInstance = ChrestExtension.getTurtlesChrestInstance(context);
      int testPart = args[0].getIntValue();
      int modelTime = args[1].getIntValue();
      
      //////////////////////////////////////////
      ///// CONSTRUCT VISUAL-SPATIAL FIELD /////
      //////////////////////////////////////////
      
      if(testPart == 0){
        
        //Set details of creator on visual-spatial field
        ArrayList<Object> creatorDetails = new ArrayList();
        creatorDetails.add("0");
        creatorDetails.add(new Square(2, 2));
        
        //Construct visual-spatial field
        VisualSpatialField vsf = new VisualSpatialField("", 5, 5, 0, 0, turtlesChrestInstance, creatorDetails, modelTime);
        ArrayList<ArrayList<TreeMap<Integer, ArrayList<VisualSpatialFieldObject>>>> 
        visualSpatialField = 
      (ArrayList<ArrayList<TreeMap<Integer, ArrayList<VisualSpatialFieldObject>>>>)
        visualSpatialFieldField.get(vsf);
        
        //Construct visual-spatial field objects
        VisualSpatialFieldObject tile1 = new VisualSpatialFieldObject("1", TileworldDomain.TILE_SCENE_OBJECT_TYPE_TOKEN, turtlesChrestInstance, vsf, 0, false, false);
        VisualSpatialFieldObject hole1 = new VisualSpatialFieldObject("2", TileworldDomain.HOLE_SCENE_OBJECT_TYPE_TOKEN, turtlesChrestInstance, vsf, 0, false, false);
        VisualSpatialFieldObject tile2 = new VisualSpatialFieldObject("3", TileworldDomain.TILE_SCENE_OBJECT_TYPE_TOKEN, turtlesChrestInstance, vsf, 0, false, false);
        VisualSpatialFieldObject opponent1 = new VisualSpatialFieldObject("4", TileworldDomain.OPPONENT_SCENE_OBJECT_TYPE_TOKEN, turtlesChrestInstance, vsf, 0, false, false);
        VisualSpatialFieldObject tile3 = new VisualSpatialFieldObject("5", TileworldDomain.TILE_SCENE_OBJECT_TYPE_TOKEN, turtlesChrestInstance, vsf, 0, false, false);
        VisualSpatialFieldObject tile4 = new VisualSpatialFieldObject("6", TileworldDomain.TILE_SCENE_OBJECT_TYPE_TOKEN, turtlesChrestInstance, vsf, 0, false, false);
        VisualSpatialFieldObject tile5 = new VisualSpatialFieldObject("7", TileworldDomain.TILE_SCENE_OBJECT_TYPE_TOKEN, turtlesChrestInstance, vsf, 0, false, false);
        VisualSpatialFieldObject opponent2 = new VisualSpatialFieldObject("8", TileworldDomain.OPPONENT_SCENE_OBJECT_TYPE_TOKEN, turtlesChrestInstance, vsf, 0, false, false);
        VisualSpatialFieldObject opponent3 = new VisualSpatialFieldObject("9", TileworldDomain.OPPONENT_SCENE_OBJECT_TYPE_TOKEN, turtlesChrestInstance, vsf, 0, false, false);
      
        //Place visual-spatial-field-objects
        visualSpatialField.get(2).get(3).lastEntry().getValue().add(tile1);
        visualSpatialField.get(3).get(3).lastEntry().getValue().add(hole1);
        visualSpatialField.get(3).get(2).lastEntry().getValue().add(tile2);
        visualSpatialField.get(4).get(2).lastEntry().getValue().add(opponent1);
        visualSpatialField.get(2).get(1).lastEntry().getValue().add(tile3);
        visualSpatialField.get(2).get(0).lastEntry().getValue().add(tile4);
        visualSpatialField.get(1).get(2).lastEntry().getValue().add(tile5);
        visualSpatialField.get(0).get(2).lastEntry().getValue().add(opponent2);
        visualSpatialField.get(1).get(3).lastEntry().getValue().add(opponent3);
          
        //Add the visual-spatial field to the CHREST turtle's visual-spatial field
        //database.
        try{
          Field chrestVisualSpatialFields = Chrest.class.getDeclaredField("_visualSpatialFields");
          chrestVisualSpatialFields.setAccessible(true);
          
          HistoryTreeMap<Integer, VisualSpatialField> turtlesVisualSpatialFields = new HistoryTreeMap<>();
          turtlesVisualSpatialFields.put(modelTime, vsf);
          chrestVisualSpatialFields.set(turtlesChrestInstance, turtlesVisualSpatialFields);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
          Logger.getLogger(Test1.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
          
      /////////////////////////////////////
      ///// SET VISUAL STM HYPOTHESIS /////
      /////////////////////////////////////

      ListPattern visualNodeContents = new ListPattern(Modality.VISUAL);
      ListPattern actionNodeContents = new ListPattern(Modality.ACTION);

      if(testPart == 0){
        visualNodeContents.add(new ItemSquarePattern("H", 1, 1));
        actionNodeContents.add(new ItemSquarePattern("MV", 180, 1));
        actionNodeContents.add(new ItemSquarePattern("MV", 180, 1));
      }
      else if(testPart == 1){
        visualNodeContents.add(new ItemSquarePattern("T", 0, 1));
        actionNodeContents.add(new ItemSquarePattern("PT", 0, 1));
        actionNodeContents.add(new ItemSquarePattern("MV", 270, 1));
      }
      else{
        visualNodeContents.add(new ItemSquarePattern("H", 1, 0));
        actionNodeContents.add(new ItemSquarePattern("MV", 180, 1));
        actionNodeContents.add(new ItemSquarePattern("MV", 0, 1));
      }

      Node actionNode = new Node(turtlesChrestInstance, actionNodeContents, actionNodeContents, modelTime);
      Node visualNode = new Node(turtlesChrestInstance, visualNodeContents, visualNodeContents, modelTime);
      LinkedHashMap<Node, Double> visualNodeProductions = new LinkedHashMap<>();
      visualNodeProductions.put(actionNode, 1.0);
      HistoryTreeMap<Integer, LinkedHashMap<Node, Double>> visualNodeProductionHistory = new HistoryTreeMap<>();
      visualNodeProductionHistory.put(modelTime, visualNodeProductions);
      nodeProductionHistoryField.set(visualNode, visualNodeProductionHistory);

      ArrayList<Node> stmItems = new ArrayList();
      stmItems.add(visualNode);
      HistoryTreeMap<Integer, List<Node>> stmItemHistory = new HistoryTreeMap<>();
      stmItemHistory.put(modelTime, stmItems);
      stmItemHistoryField.set(chrestVisualStmField.get(turtlesChrestInstance), stmItemHistory);

    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
      Logger.getLogger(Test1.class.getName()).log(Level.SEVERE, null, ex);
    }
      
  }
  
}
