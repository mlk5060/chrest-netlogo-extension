import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import jchrest.lib.Scene;
import org.nlogo.api.AgentException;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultCommand;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.LogoList;
import org.nlogo.api.Syntax;

/**
 * Instantiates a mind's eye for the calling turtle and returns a boolean value
 * indicating whether the mind's eye has been instantiated for the calling 
 * turtle (true) or not (false).
 * 
 * Six parameters must be passed when the Netlogo extension primitive that 
 * invokes this class is used in a Netlogo model:
 * 
 * Param #      Data Type       Description
 * -------      ---------       -----------
 * 1            Number          The amount of time (in milliseconds) that the
 *                              mind's eye should exist for after instantiation 
 *                              or interaction (mind's eye lifespan).
 * 2            Number          The amount of time (in milliseconds) that it
 *                              takes to place a single object in the mind's eye
 *                              during instantiation of the visual-spatial 
 *                              field.
 * 3            Number          The amount of time (in milliseconds) that the 
 *                              calling turtle should take when accessing the
 *                              mind's eye.
 * 4            Number          The amount of time (in milliseconds) that the 
 *                              calling turtle should take when moving an object
 *                              in the mind's eye.
 * 5            Number          The current time in the Netlogo model that the
 *                              call to this extension primitive was made (in 
 *                              milliseconds).
 * 6            Number          The lifespan for recognised objects (objects
 *                              in the scene that are currently present in the
 *                              calling turtle's visual STM).
 * 7            Number          The lifespan for unrecognised objects (objects
 *                              in the scene that are not currently present in 
 *                              the calling turtle's visual STM).        
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class InstantiateMindsEye extends DefaultCommand {
  
  @Override
  public Syntax getSyntax(){
    return Syntax.commandSyntax(new int[] { Syntax.ListType(), Syntax.NumberType(), Syntax.NumberType(), Syntax.NumberType(), Syntax.NumberType(), Syntax.NumberType()});
  }
  
  @Override
  public void perform(Argument args[], Context context) throws ExtensionException, LogoException {
    try {
      Scene currentScene = (Scene)BaseExtensionVariablesAndMethods.getAgent(context).getBreedVariable(BaseExtensionVariablesAndMethods.CURRENT_SCENE_BREED_VAR_NAME);
      BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(context).createNewMindsEye(
        currentScene, 
        args[1].getIntValue(),
        args[2].getIntValue(),
        args[3].getIntValue(),
        args[4].getIntValue(),
        args[5].getIntValue(),
        args[6].getIntValue(),
        args[7].getIntValue()
      );
    } catch (AgentException ex) {
      Logger.getLogger(InstantiateMindsEye.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
    }
  }
}
