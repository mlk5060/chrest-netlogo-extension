
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import jchrest.lib.Scene;
import org.nlogo.agent.AgentSet;
import org.nlogo.agent.Patch;
import org.nlogo.agent.Turtle;
import org.nlogo.agent.World;
import org.nlogo.api.AgentException;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultCommand;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.LogoList;
import org.nlogo.api.Syntax;

/**
 * Creates a new {@link jchrest.lib.Scene} instance for CHREST to work with to 
 * the calling turtle's "current-scene" turtle variable.
 * 
 * One parameter must be passed when the Netlogo extension primitive that 
 * invokes this class is used in a Netlogo model:
 * 
 * Param #      Data Type       Description
 * -------      ---------       -----------
 * 1            List            What the agent can see on patches as a list of 
 *                              strings with three pieces of information 
 *                              declared in the following order and separated by 
 *                              whitespace:
 *                              - Object identifier (empty squares should be 
 *                                denoted by a period/full-stop).
 *                              - x-cor of the patch (can be relative or 
 *                                absolute to the calling turtle).
 *                              - y-cor of the patch (can be relative or 
 *                                absolute to the calling turtle).
 *                              NOTE: a decision must be made on whether all 
 *                              x/y-cor offsets will be relative OR absolute.
 *                              Mixtures of relative and absolute coordinates 
 *                              will result in incorrect Scene instances being
 *                              generated.
 * 2            String          The name of the scene (first parameter used by 
 *                              {@link jchrest.lib.Scene#Scene(java.lang.String, int, int)}).
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class SetCurrentScene extends DefaultCommand {

  @Override
  public Syntax getSyntax(){
    return Syntax.commandSyntax(new int[]{Syntax.ListType(), Syntax.StringType()});
  }
  
  @Override
  public void perform(Argument[] args, Context context) throws ExtensionException, LogoException {
    org.nlogo.agent.Agent callingAgent = BaseExtensionVariablesAndMethods.getAgent(context);
    
    try {
      //First, analyse the scene passed to establish the smallest and greatest 
      //width and height (x/y-coordinates, respectively) so that a 
      //jchrest.lib.Scene instance can be correctly constructed.
      LogoList originalScene = args[0].getList();
      Integer minX = null;
      Integer maxX = null;
      Integer minY = null;
      Integer maxY = null;
      
      for(int i = 0; i < originalScene.size(); i++){
        Object originalSceneInfo = originalScene.get(i);
        if(originalSceneInfo instanceof String){
          String originalSceneUnit = (String)originalSceneInfo;
          String[] originalSceneUnitInfo = originalSceneUnit.split("\\s+");
          if(originalSceneUnitInfo.length == 3){
            Integer xcor = Integer.valueOf(originalSceneUnitInfo[1]);
            Integer ycor = Integer.valueOf(originalSceneUnitInfo[2]);
            
            //Set the minX value accordingly.
            if(minX == null){
              minX = xcor;
            }
            else if(xcor < minX){
              minX = xcor;
            }
            
            //Set the minY value accordingly.
            if(minY == null){
              minY = ycor;
            }
            else if(ycor < minY){
              minY = ycor;
            }
            
            //Set the maxX value accordingly.
            if(maxX == null){
              maxX = xcor;
            }
            else if(xcor < maxX){
              maxX = xcor;
            }
            
            //Set the maxY value accordingly.
            if(maxY == null){
              maxY = ycor;
            }
            else if(ycor < maxY){
              maxY = ycor;
            }
          }
          else{
            throw new ExtensionException("The information for item " + i + " in the scene passed to "
              + "the 'set-current-scene' CHREST extention does not contain 3 units of information but "
              + "contains " + originalSceneUnitInfo.length + " (" + Arrays.toString(originalSceneUnitInfo) 
              + ").  Please ensure that there is a suitable object identifier, x-cor offset and y-cor "
              + "offset specified in this order and seperated by whitespace i.e. "
              + "'objectIdentifier xcorOffset ycorOffset'.");
          }
        }
        else{
          throw new ExtensionException("The information for item " + i + " in the scene passed to "
              + "the 'set-current-scene' CHREST extention is not a String instance.");
        }
      }
      
      //Create a new scene instance using the max/min x/y values set above.  Add
      //1 to the result since if maxX and minX are 2 and 1 respectively, the 
      //result (1) does not take into account the 2 xcors.
      Scene scene = new Scene(args[1].getString(), (maxY - minY) + 1, (maxX - minX) + 1);
      
      //Set the domain-specific to non domain-specific (Scene coordinate) 
      //converters (Scene coordinates start at 0).
      int xcorConverter = 0 - minX;
      int ycorConverter = 0 - minY;
  
      //Populate the scene using info from the list passed.
      for(int i = 0; i < originalScene.size(); i++){
        Object originalSceneInfo = originalScene.get(i);
        String originalSceneUnit = (String)originalSceneInfo;
        String[] originalSceneUnitInfo = originalSceneUnit.split(" ");
        scene.addItemToSquare(
          Integer.valueOf(originalSceneUnitInfo[2]) + ycorConverter, //row (ycor)
          Integer.valueOf(originalSceneUnitInfo[1]) + xcorConverter, //col (xcor)
          originalSceneUnitInfo[0] //item identifier
        );
      }
      
      //Finally, set the calling turtle's "current-scene" variable to the Scene 
      //generated.
      callingAgent.setBreedVariable(BaseExtensionVariablesAndMethods.CURRENT_SCENE_BREED_VAR_NAME, scene);
    } catch (AgentException ex) {
      Logger.getLogger(SetCurrentScene.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
    }
  }
}
