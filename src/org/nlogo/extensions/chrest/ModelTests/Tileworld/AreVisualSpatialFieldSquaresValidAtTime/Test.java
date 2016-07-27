package org.nlogo.extensions.chrest.ModelTests.Tileworld.AreVisualSpatialFieldSquaresValidAtTime;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import jchrest.architecture.Chrest;
import jchrest.architecture.VisualSpatialField;
import jchrest.domainSpecifics.tileworld.TileworldDomain;
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
public class Test extends DefaultCommand {
  
  @Override
  public Syntax getSyntax(){
    return Syntax.commandSyntax(new int[]{Syntax.NumberType()});
  }

  @Override
  public void perform(Argument[] args, Context context) throws ExtensionException, LogoException {
    int scenario = args[0].getIntValue();
    Chrest turtlesModel = ChrestExtension.getTurtlesChrestInstance(context);
    
    //Specify location of creator according to the scenario (see test 
    //documentation).
    ArrayList<Object> creatorDetails = new ArrayList();
    creatorDetails.add(String.valueOf(context.getAgent().id()));
    creatorDetails.add(new Square(
      (scenario == 4 ? 2 : 1), 
      (
        scenario == 3 ? 2 :
        scenario == 5 ? 0 :  
        1
      )
    ));
    VisualSpatialField vsf = new VisualSpatialField("", 3, 3, 0, 0, turtlesModel, creatorDetails, 0);
    
    Field visualSpatialFieldField;
    try {
      
      //Place objects on visual-spatial field.
      visualSpatialFieldField = VisualSpatialField.class.getDeclaredField("_visualSpatialField");
      visualSpatialFieldField.setAccessible(true);
      ArrayList<ArrayList<ArrayList<VisualSpatialFieldObject>>> vsfVisualSpatialField = (ArrayList)visualSpatialFieldField.get(vsf);
      
      /*********************************************************************/
      /***** PLACE OBJECTS WHOSE LOCATIONS ARE STATIC ACROSS SCENARIOS *****/
      /*********************************************************************/
      
      //Place hole.
      vsfVisualSpatialField.get(2).get(1).add(new VisualSpatialFieldObject(
        "2", 
        TileworldDomain.HOLE_SCENE_OBJECT_TYPE_TOKEN, 
        turtlesModel,
        vsf, 
        0, 
        false,
        false
      ));
      
      //Place opponent
      vsfVisualSpatialField.get(1).get(0).add(new VisualSpatialFieldObject(
        "3", 
        TileworldDomain.OPPONENT_SCENE_OBJECT_TYPE_TOKEN, 
        turtlesModel,
        vsf, 
        0, 
        false,
        false
      ));
      
      //Place non-moveable tile
      vsfVisualSpatialField.get(0).get(1).add(new VisualSpatialFieldObject(
        "4", 
        TileworldDomain.TILE_SCENE_OBJECT_TYPE_TOKEN, 
        turtlesModel,
        vsf, 
        0, 
        false,
        false
      ));
      
      /**********************************************************************/
      /***** PLACE OBJECTS WHOSE LOCATIONS ARE DYNAMIC ACROSS SCENARIOS *****/
      /**********************************************************************/ 
      
      //Place moveable tile
      int tileCol = (
        scenario == 1 ? 0 : 
        scenario == 6 ? 2 :
        1
       );
      
      int tileRow = (
        scenario == 1 || scenario == 6 ? 1 :
        scenario == 2 ? 0 :
        2
      );
      
      vsfVisualSpatialField.get(tileCol).get(tileRow).add(new VisualSpatialFieldObject(
        "1", 
        TileworldDomain.TILE_SCENE_OBJECT_TYPE_TOKEN, 
        turtlesModel,
        vsf, 
        0, 
        false,
        false
      ));
      
      Field chrestVisualSpatialFields = Chrest.class.getDeclaredField("_visualSpatialFields");
      chrestVisualSpatialFields.setAccessible(true);
      TreeMap<Integer, VisualSpatialField> turtlesVisualSpatialFields = (TreeMap)chrestVisualSpatialFields.get(turtlesModel);
      turtlesVisualSpatialFields.put(0, vsf);
      chrestVisualSpatialFields.set(turtlesModel, turtlesVisualSpatialFields);
      
      //Set the attention clock to the time the VisualSpatialField is "created"
      //(0) otherwise, when the model procedure tries to get the location of the 
      //creator, it will look for a VisualSpatialField at time -1 (the default
      //time for any CHREST model's attention clock) and will return null since
      //a CHREST model's VisualSpatialField database is initialised with an 
      //entry for time -1 and its value is null. This causes the test to fail 
      //unless accounted for.
      Field chrestAttentionClock = Chrest.class.getDeclaredField("_attentionClock");
      chrestAttentionClock.setAccessible(true);
      chrestAttentionClock.set(turtlesModel, 0);
      
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
      Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
    }
      
  }
  
}
