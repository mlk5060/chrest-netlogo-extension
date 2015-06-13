import java.util.logging.Level;
import java.util.logging.Logger;
import jchrest.lib.Scene;
import org.nlogo.api.AgentException;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultCommand;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 * Instantiates a mind's eye for the calling turtle at the current time.
 * 
 * Eight parameters must be passed when the Netlogo extension primitive that 
 * invokes this class is used in a Netlogo model:
 * 
 * Param #      Data Type                 Description
 * -------      ---------                 -----------
 * 1            {@link jchrest.lib.Scene} The scene that should be used to 
 *                                        instantiate the mind's eye.  An 
 *                                        instance of this object can be created 
 *                                        in Netlogo using the primitive that 
 *                                        calls the {@link CreateScene} class in 
 *                                        this extension.
 * 2            Number                    The amount of time (in milliseconds) 
 *                                        that it takes to place a single object 
 *                                        in the mind's eye during instantiation 
 *                                        of the visual-spatial field.
 * 3            Number                    The amount of time (in milliseconds) 
 *                                        that it takes to place an empty square 
 *                                        in the mind's eye during instantiation 
 *                                        of the visual-spatial field.
 * 4            Number                    The amount of time (in milliseconds) 
 *                                        that the calling turtle should take 
 *                                        when accessing the mind's eye.
 * 5            Number                    The amount of time (in milliseconds) 
 *                                        that the calling turtle should take 
 *                                        when moving an object in the mind's 
 *                                        eye.
 * 6            Number                    The lifespan for recognised objects 
 *                                        (objects in the scene that are 
 *                                        currently present in the calling 
 *                                        turtle's visual STM).
 * 7            Number                    The lifespan for unrecognised objects 
 *                                        (objects in the scene that are not 
 *                                        currently present in the calling 
 *                                        turtle's visual STM).
 * 8            Number                    The number of fixations that should be 
 *                                        made when scanning the scene to be 
 *                                        transposed into the visual-spatial 
 *                                        field of the mind's eye.
 * 9            Number                    The current time in the Netlogo model 
 *                                        that the call to this extension 
 *                                        primitive was made (in milliseconds).
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class InstantiateMindsEye extends DefaultCommand {
  
  @Override
  public Syntax getSyntax(){
    return Syntax.commandSyntax(new int[]{ 
      Syntax.WildcardType(),
      Syntax.NumberType(), 
      Syntax.NumberType(), 
      Syntax.NumberType(), 
      Syntax.NumberType(), 
      Syntax.NumberType(),
      Syntax.NumberType(),
      Syntax.NumberType(),
      Syntax.NumberType()
    });
  }
  
  @Override
  public void perform(Argument args[], Context context) throws ExtensionException, LogoException {
    try {
      BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(context).createNewMindsEye(
        (Scene)args[0].get(), 
        args[1].getIntValue(),
        args[2].getIntValue(),
        args[3].getIntValue(),
        args[4].getIntValue(),
        args[5].getIntValue(),
        args[6].getIntValue(),
        args[7].getIntValue(),
        args[8].getIntValue()
      );
    } catch (AgentException ex) {
      Logger.getLogger(InstantiateMindsEye.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
    }
  }
}
