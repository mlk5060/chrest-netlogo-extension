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
public class Test2 extends DefaultCommand {
  
  @Override
  public Syntax getSyntax(){
    return Syntax.commandSyntax(new int[]{
      Syntax.NumberType()
    });
  }

  @Override
  public void perform(Argument[] args, Context context) throws ExtensionException, LogoException {
    
    int tilePosition = args[0].getIntValue();
    if(tilePosition != 0 && tilePosition != 90 && tilePosition != 180 && tilePosition != 270){
      throw new IllegalArgumentException("The tile position specified (" + tilePosition + ") is not supported");
    }
    
    Chrest turtlesChrestInstance = ChrestExtension.getTurtlesChrestInstance(context);
    
    ArrayList<Object> creatorDetails = new ArrayList();
    creatorDetails.add("0");
    creatorDetails.add(new Square(1, 1));
    
    VisualSpatialField vsf = new VisualSpatialField("Tile position: " + tilePosition,
      3, 3, 0, 0, turtlesChrestInstance, creatorDetails, 0
    );
    
    VisualSpatialFieldObject tile1 = new VisualSpatialFieldObject("1", TileworldDomain.TILE_SCENE_OBJECT_TYPE_TOKEN, turtlesChrestInstance, vsf, 0, false, false);
    VisualSpatialFieldObject hole2 = new VisualSpatialFieldObject("2", TileworldDomain.HOLE_SCENE_OBJECT_TYPE_TOKEN, turtlesChrestInstance, vsf, 0, false, false);
    VisualSpatialFieldObject opponent3 = new VisualSpatialFieldObject("3", TileworldDomain.OPPONENT_SCENE_OBJECT_TYPE_TOKEN, turtlesChrestInstance, vsf, 0, false, false);
    VisualSpatialFieldObject hole4 = new VisualSpatialFieldObject("4", TileworldDomain.HOLE_SCENE_OBJECT_TYPE_TOKEN, turtlesChrestInstance, vsf, 0, false, false);
    VisualSpatialFieldObject opponent5 = new VisualSpatialFieldObject("5", TileworldDomain.OPPONENT_SCENE_OBJECT_TYPE_TOKEN, turtlesChrestInstance, vsf, 0, false, false);
    VisualSpatialFieldObject hole6 = new VisualSpatialFieldObject("6", TileworldDomain.HOLE_SCENE_OBJECT_TYPE_TOKEN, turtlesChrestInstance, vsf, 0, false, false);
    
    try {
      Field visualSpatialFieldField = VisualSpatialField.class.getDeclaredField("_visualSpatialField");
      visualSpatialFieldField.setAccessible(true);
      
      ArrayList<ArrayList<TreeMap<Integer, ArrayList<VisualSpatialFieldObject>>>> 
        visualSpatialField = 
      (ArrayList<ArrayList<TreeMap<Integer, ArrayList<VisualSpatialFieldObject>>>>)
        visualSpatialFieldField.get(vsf);
      
      //Place tile 1
      //Column
      //  north: 1
      //  east: 2
      //  south: 1
      //  west: 0
      //Row
      //  north: 2
      //  east: 1
      //  south: 0
      //  west: 1
      visualSpatialField
        .get(tilePosition == 0 || tilePosition == 180 ? 1 : tilePosition == 90 ? 2 : 0)
        .get(tilePosition == 90 || tilePosition == 270 ? 1 : tilePosition == 0 ? 2 : 0)
        .lastEntry().getValue().add(tile1);
      
      //Place hole 2
      //Column
      //  north: 2
      //  east: 2
      //  south: 0
      //  west: 0
      //Row
      //  north: 2
      //  east: 0
      //  south: 0
      //  west: 2
      visualSpatialField
        .get(tilePosition == 0 || tilePosition == 90 ? 2 : 0)
        .get(tilePosition == 0 || tilePosition == 270 ? 2 : 0)
        .lastEntry().getValue().add(hole2);
      
      //Place opponent 3
      //Column
      //  north: 2
      //  east: 1
      //  south: 0
      //  west: 1
      //Row
      //  north: 1
      //  east: 0
      //  south: 1
      //  west: 2
      visualSpatialField
        .get(tilePosition == 90 || tilePosition == 270 ? 1 : tilePosition == 0 ? 2 : 0)
        .get(tilePosition == 0 || tilePosition == 180 ? 1 : tilePosition == 90 ? 0 : 2)
        .lastEntry().getValue().add(opponent3);
      
      //Place hole 4
      //Column
      //  north: 1
      //  east: 0
      //  south: 1
      //  west: 2
      //Row
      //  north: 0
      //  east: 1
      //  south: 2
      //  west: 1
      visualSpatialField
        .get(tilePosition == 0 || tilePosition == 180 ? 1 : tilePosition == 90 ? 0 : 2)
        .get(tilePosition == 90 || tilePosition == 270 ? 1 : tilePosition == 0 ? 0 : 2)
        .lastEntry().getValue().add(hole4);
      
      //Place opponent 5
      //Column
      //  north: 0
      //  east: 1
      //  south: 2
      //  west: 1
      //Row
      //  north: 1
      //  east: 2
      //  south: 1
      //  west: 0
      visualSpatialField
        .get(tilePosition == 90 || tilePosition == 270 ? 1 : tilePosition == 0 ? 0 : 2)
        .get(tilePosition == 0 || tilePosition == 180 ? 1 : tilePosition == 90 ? 2 : 0)
        .lastEntry().getValue().add(opponent5);
      
      //Place hole 6
      //Column
      //  north: 0
      //  east: 2
      //  south: 2
      //  west: 0
      //Row
      //  north: 2
      //  east: 2
      //  south: 0
      //  west: 0
      visualSpatialField
        .get(tilePosition == 0 || tilePosition == 270 ? 0 : 2)
        .get(tilePosition == 0 || tilePosition == 90 ? 2 : 0)
        .lastEntry().getValue().add(hole6);
      
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
