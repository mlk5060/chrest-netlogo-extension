package org.nlogo.extensions.chrest.ModelTests.Tileworld.GeneratePlan;

import java.lang.reflect.Field;
import java.util.ArrayList;
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
public class Test1 extends DefaultCommand {
  
  @Override
  public Syntax getSyntax(){
    return Syntax.commandSyntax(new int[]{
      Syntax.NumberType(),
      Syntax.StringType()
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
    creatorDetails.add(args[1].getString());
    creatorDetails.add(new Square(2, 2));
    
    VisualSpatialField vsf = new VisualSpatialField("Tile position: " + tilePosition,
      5, 5, 0, 0, turtlesChrestInstance, creatorDetails, 0
    );
    
    VisualSpatialFieldObject tile1 = new VisualSpatialFieldObject("1", TileworldDomain.TILE_SCENE_OBJECT_TYPE_TOKEN, turtlesChrestInstance, vsf, 0, false, false);
    VisualSpatialFieldObject hole1 = new VisualSpatialFieldObject("2", TileworldDomain.HOLE_SCENE_OBJECT_TYPE_TOKEN, turtlesChrestInstance, vsf, 0, false, false);
    VisualSpatialFieldObject tile2 = new VisualSpatialFieldObject("3", TileworldDomain.TILE_SCENE_OBJECT_TYPE_TOKEN, turtlesChrestInstance, vsf, 0, false, false);
    VisualSpatialFieldObject opponent1 = new VisualSpatialFieldObject("4", TileworldDomain.OPPONENT_SCENE_OBJECT_TYPE_TOKEN, turtlesChrestInstance, vsf, 0, false, false);
    VisualSpatialFieldObject tile3 = new VisualSpatialFieldObject("5", TileworldDomain.TILE_SCENE_OBJECT_TYPE_TOKEN, turtlesChrestInstance, vsf, 0, false, false);
    VisualSpatialFieldObject tile4 = new VisualSpatialFieldObject("6", TileworldDomain.TILE_SCENE_OBJECT_TYPE_TOKEN, turtlesChrestInstance, vsf, 0, false, false);
    VisualSpatialFieldObject tile5 = new VisualSpatialFieldObject("7", TileworldDomain.TILE_SCENE_OBJECT_TYPE_TOKEN, turtlesChrestInstance, vsf, 0, false, false);
    VisualSpatialFieldObject opponent2 = new VisualSpatialFieldObject("8", TileworldDomain.OPPONENT_SCENE_OBJECT_TYPE_TOKEN, turtlesChrestInstance, vsf, 0, false, false);
    VisualSpatialFieldObject opponent3 = new VisualSpatialFieldObject("9", TileworldDomain.OPPONENT_SCENE_OBJECT_TYPE_TOKEN, turtlesChrestInstance, vsf, 0, false, false);
    
    try {
      Field visualSpatialFieldField = VisualSpatialField.class.getDeclaredField("_visualSpatialField");
      visualSpatialFieldField.setAccessible(true);
      
      //Place tile 1
      ArrayList<ArrayList<ArrayList<VisualSpatialFieldObject>>> visualSpatialField = (ArrayList<ArrayList<ArrayList<VisualSpatialFieldObject>>>)visualSpatialFieldField.get(vsf);
      visualSpatialField
        .get(tilePosition == 90 ? 3 : tilePosition == 270 ? 1 : 2)
        .get(tilePosition == 0 ? 3 : tilePosition == 180 ? 1 : 2)
        .add(tile1);
      
      //Place hole 1
      visualSpatialField
        .get(tilePosition == 0 || tilePosition == 90 ? 3 : 1)
        .get(tilePosition == 0 || tilePosition == 270 ? 3 : 1)
        .add(hole1);
      
      //Place tile 2
      visualSpatialField
        .get(tilePosition == 0 ? 3 : tilePosition == 180 ? 1 : 2)
        .get(tilePosition == 90 ? 1 : tilePosition == 270 ? 3 : 2)
        .add(tile2);
      
      //Place opponent 1
      visualSpatialField
        .get(tilePosition == 0 ? 4 : tilePosition == 180 ? 0 : 2)
        .get(tilePosition == 90 ? 0 : tilePosition == 270 ? 4 : 2)
        .add(opponent1);
      
      //Place tile 3
      visualSpatialField
        .get(tilePosition == 90 ? 1 : tilePosition == 270 ? 3 : 2)
        .get(tilePosition == 0 ? 1 : tilePosition == 180 ? 3 : 2)
        .add(tile3);
      
      //Place tile 4
      visualSpatialField
        .get(tilePosition == 90 ? 0 : tilePosition == 270 ? 4 : 2)
        .get(tilePosition == 0 ? 0 : tilePosition == 180 ? 4 : 2)
        .add(tile4);
      
      //Place tile 5
      visualSpatialField
        .get(tilePosition == 0 ? 1 : tilePosition == 180 ? 3 : 2)
        .get(tilePosition == 90 ? 3 : tilePosition == 270 ? 1 : 2)
        .add(tile5);
      
      //Place opponent 2
      visualSpatialField
        .get(tilePosition == 0 ? 0 : tilePosition == 180 ? 4 : 2)
        .get(tilePosition == 90 ? 4 : tilePosition == 270 ? 0 : 2)
        .add(opponent2);
      
      //Place opponent 3
      visualSpatialField
        .get(tilePosition == 0 || tilePosition == 90 ? 1 : 3)
        .get(tilePosition == 0 || tilePosition == 90 ? 3 : 1)
        .add(opponent3);
      
      //Add the visual-spatial field to the CHREST turtle's visual-spatial field 
      //database.
      Field chrestVisualSpatialFields = Chrest.class.getDeclaredField("_visualSpatialFields");
      chrestVisualSpatialFields.setAccessible(true);
      
      HistoryTreeMap<Integer, VisualSpatialField> turtlesVisualSpatialFields = new HistoryTreeMap<>();
      turtlesVisualSpatialFields.put(0, vsf);
      chrestVisualSpatialFields.set(turtlesChrestInstance, turtlesVisualSpatialFields);
      
      //Set visual STM hypothesis
      Field chrestVisualStmField = Chrest.class.getDeclaredField("_visualStm");
      chrestVisualStmField.setAccessible(true);
      
      
      
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
      Logger.getLogger(Test1.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    
  }
  
}
