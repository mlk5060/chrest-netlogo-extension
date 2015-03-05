import java.util.logging.Level;
import java.util.logging.Logger;
import org.nlogo.api.AgentException;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultCommand;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 * Destroys the mind's eye instance associated with the calling turtle's CHREST
 * instance (if it has one).
 * 
 * One parameter must be passed when the Netlogo extension primitive that 
 * invokes this class is used in a Netlogo model:
 * 
 * Param #      Data Type       Description
 * -------      ---------       -----------
 * 1            Number          The current domain time (in milliseconds).
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class DestroyMindsEye extends DefaultCommand {
  
  @Override
  public Syntax getSyntax() {
    return Syntax.commandSyntax(new int[]{Syntax.NumberType()});
  }

  @Override
  public void perform(Argument[] args, Context context) throws ExtensionException, LogoException {
    try {
      if(BaseExtensionVariablesAndMethods.agentHasChrestInstance(context)){
        BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(context).destroyMindsEye(args[0].getIntValue());
      }
    } catch (AgentException ex) {
      Logger.getLogger(DestroyMindsEye.class.getName()).log(Level.SEVERE,"", ex);
    }
  }
  
}
