package org.nlogo.extensions.chrest.ModelTests.Tileworld.GenerateVisualSpatialFieldMoves;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import jchrest.architecture.Chrest;
import jchrest.architecture.VisualSpatialField;
import jchrest.lib.Square;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultCommand;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.extensions.chrest.ChrestExtension;

/**
 *
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class Test1 extends DefaultCommand {

  @Override
  public void perform(Argument[] args, Context context) throws ExtensionException, LogoException {
    Chrest turtlesModel = ChrestExtension.getTurtlesChrestInstance(context);
    
    ArrayList<Object> creatorDetails = new ArrayList();
    creatorDetails.add("" + context.getAgent().id());
    creatorDetails.add(new Square(2, 2));
    VisualSpatialField vsf = new VisualSpatialField("", 3, 3, 2, 2, turtlesModel, creatorDetails, 0);
    
    try {
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
      Logger.getLogger(Test1.class.getName()).log(Level.SEVERE, null, ex);
    }
    
  }
  
}
