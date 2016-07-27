package org.nlogo.extensions.chrest.ModelTests.Tileworld.GeneratePlan;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import jchrest.architecture.Chrest;
import jchrest.architecture.VisualSpatialField;
import jchrest.domainSpecifics.tileworld.TileworldDomain;
import jchrest.lib.HistoryTreeMap;
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
public class Test5 extends DefaultCommand {

  @Override
  public void perform(Argument[] args, Context context) throws ExtensionException, LogoException {
    
    Chrest turtlesChrestInstance = ChrestExtension.getTurtlesChrestInstance(context);
    
    ArrayList<Object> creatorDetails = new ArrayList();
    creatorDetails.add("0");
    creatorDetails.add(new Square(2, 2));
    
    VisualSpatialField vsf = new VisualSpatialField("Immoveable avatar",
      5, 5, 0, 0, turtlesChrestInstance, creatorDetails, 0
    );
    
    VisualSpatialFieldObject tile1 = new VisualSpatialFieldObject("1", TileworldDomain.TILE_SCENE_OBJECT_TYPE_TOKEN, turtlesChrestInstance, vsf, 0, false, false);
    VisualSpatialFieldObject tile2 = new VisualSpatialFieldObject("2", TileworldDomain.TILE_SCENE_OBJECT_TYPE_TOKEN, turtlesChrestInstance, vsf, 0, false, false);
    VisualSpatialFieldObject hole3 = new VisualSpatialFieldObject("3", TileworldDomain.HOLE_SCENE_OBJECT_TYPE_TOKEN, turtlesChrestInstance, vsf, 0, false, false);
    VisualSpatialFieldObject opponent4 = new VisualSpatialFieldObject("4", TileworldDomain.OPPONENT_SCENE_OBJECT_TYPE_TOKEN, turtlesChrestInstance, vsf, 0, false, false);
    VisualSpatialFieldObject hole5 = new VisualSpatialFieldObject("5", TileworldDomain.HOLE_SCENE_OBJECT_TYPE_TOKEN, turtlesChrestInstance, vsf, 0, false, false);
    
    try {
      Field visualSpatialFieldField = VisualSpatialField.class.getDeclaredField("_visualSpatialField");
      visualSpatialFieldField.setAccessible(true);
      
      ArrayList<ArrayList<TreeMap<Integer, ArrayList<VisualSpatialFieldObject>>>> 
        visualSpatialField = 
      (ArrayList<ArrayList<TreeMap<Integer, ArrayList<VisualSpatialFieldObject>>>>)
        visualSpatialFieldField.get(vsf);
      
      visualSpatialField.get(2).get(3).lastEntry().getValue().add(tile1);
      visualSpatialField.get(2).get(4).lastEntry().getValue().add(tile2);
      visualSpatialField.get(3).get(2).lastEntry().getValue().add(hole3);
      visualSpatialField.get(2).get(1).lastEntry().getValue().add(opponent4);
      visualSpatialField.get(1).get(2).lastEntry().getValue().add(hole5);
      
      //Add the visual-spatial field to the CHREST turtle's visual-spatial field 
      //database.
      Field chrestVisualSpatialFields = Chrest.class.getDeclaredField("_visualSpatialFields");
      chrestVisualSpatialFields.setAccessible(true);
      
      HistoryTreeMap<Integer, VisualSpatialField> turtlesVisualSpatialFields = new HistoryTreeMap<>();
      turtlesVisualSpatialFields.put(0, vsf);
      chrestVisualSpatialFields.set(turtlesChrestInstance, turtlesVisualSpatialFields);
      
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
      Logger.getLogger(Test2.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}

