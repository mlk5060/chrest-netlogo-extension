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
 * Sets the calling turtle's "_discriminationTime" variable in its CHREST 
 * architecture to the value specified (this should be in milliseconds).
 * 
 * One parameter must be passed when the Netlogo extension primitive that 
 * invokes this class is used in a Netlogo model:
 * 
 * Param #      Data Type       Description
 * -------      ---------       -----------
 * 1            Number          The amount of time (in milliseconds) that the 
 *                              calling turtle should take when discriminating a
 *                              new node in LTM.
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liv.ac.uk>
 */
public class SetDiscriminationTime extends DefaultCommand {
  
  @Override
  public Syntax getSyntax(){
    return Syntax.commandSyntax(new int[] {Syntax.NumberType()});
  }
  
  @Override
  public void perform(Argument args[], Context context) throws ExtensionException, LogoException{
    try {
      if(BaseExtensionVariablesAndMethods.agentHasChrestInstance(context)){
        BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(context).setDiscriminationTime(args[0].getIntValue());
      }
    } catch (AgentException ex) {
      Logger.getLogger(SetDiscriminationTime.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
