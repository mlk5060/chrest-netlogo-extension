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
public class Test3 extends DefaultCommand {
  
  @Override
  public Syntax getSyntax(){
    return Syntax.commandSyntax(new int[]{
      Syntax.NumberType()
    });
  }

  @Override
  public void perform(Argument[] args, Context context) throws ExtensionException, LogoException {
    
    int openChannelHeading = args[0].getIntValue();
    if(openChannelHeading != 0 && openChannelHeading != 90 && openChannelHeading != 180 && openChannelHeading != 270){
      throw new IllegalArgumentException("The open channel heading specified (" + openChannelHeading + ") is not supported");
    }
    
    Chrest turtlesChrestInstance = ChrestExtension.getTurtlesChrestInstance(context);
    
    ArrayList<Object> creatorDetails = new ArrayList();
    creatorDetails.add("0");
    creatorDetails.add(new Square(1, 1));
    
    VisualSpatialField vsf = new VisualSpatialField("Open channel heading: " + openChannelHeading,
      3, 3, 0, 0, turtlesChrestInstance, creatorDetails, 0
    );
    
    VisualSpatialFieldObject hole1 = new VisualSpatialFieldObject("1", TileworldDomain.HOLE_SCENE_OBJECT_TYPE_TOKEN, turtlesChrestInstance, vsf, 0, false, false);
    VisualSpatialFieldObject hole2 = new VisualSpatialFieldObject("2", TileworldDomain.HOLE_SCENE_OBJECT_TYPE_TOKEN, turtlesChrestInstance, vsf, 0, false, false);
    VisualSpatialFieldObject hole3 = new VisualSpatialFieldObject("3", TileworldDomain.HOLE_SCENE_OBJECT_TYPE_TOKEN, turtlesChrestInstance, vsf, 0, false, false);
    VisualSpatialFieldObject hole4 = new VisualSpatialFieldObject("4", TileworldDomain.HOLE_SCENE_OBJECT_TYPE_TOKEN, turtlesChrestInstance, vsf, 0, false, false);
    VisualSpatialFieldObject opponent1 = new VisualSpatialFieldObject("5", TileworldDomain.OPPONENT_SCENE_OBJECT_TYPE_TOKEN, turtlesChrestInstance, vsf, 0, false, false);
    
    try {
      Field visualSpatialFieldField = VisualSpatialField.class.getDeclaredField("_visualSpatialField");
      visualSpatialFieldField.setAccessible(true);
      
      ArrayList<ArrayList<TreeMap<Integer, ArrayList<VisualSpatialFieldObject>>>> 
        visualSpatialField = 
      (ArrayList<ArrayList<TreeMap<Integer, ArrayList<VisualSpatialFieldObject>>>>)
        visualSpatialFieldField.get(vsf);
      
      //Place hole 1
      visualSpatialField
        .get(openChannelHeading == 0 || openChannelHeading == 90 ? 2 : 0)
        .get(openChannelHeading == 0 || openChannelHeading == 270 ? 2 : 0)
        .lastEntry().getValue().add(hole1);
      
      //Place hole 2
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
        .get(openChannelHeading == 90 || openChannelHeading == 270 ? 1 : openChannelHeading == 0 ? 2 : 0)
        .get(openChannelHeading == 0 || openChannelHeading == 180 ? 1 : openChannelHeading == 90 ? 0 : 2)
        .lastEntry().getValue().add(hole2);
      
      //Place hole 3
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
        .get(openChannelHeading == 0 || openChannelHeading == 180 ? 1 : openChannelHeading == 90 ? 0 : 2)
        .get(openChannelHeading == 90 || openChannelHeading == 270 ? 1 : openChannelHeading == 0 ? 0 : 2)
        .lastEntry().getValue().add(hole3);
      
      //Place hole 4
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
        .get(openChannelHeading == 90 || openChannelHeading == 270 ? 1 : openChannelHeading == 0 ? 0 : 2)
        .get(openChannelHeading == 0 || openChannelHeading == 180 ? 1 : openChannelHeading == 90 ? 2 : 0)
        .lastEntry().getValue().add(hole4);
      
      //Place opponent 1
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
        .get(openChannelHeading == 0 || openChannelHeading == 270 ? 0 : 2)
        .get(openChannelHeading == 0 || openChannelHeading == 90 ? 2 : 0)
        .lastEntry().getValue().add(opponent1);
      
      
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
