import java.util.logging.Level;
import java.util.logging.Logger;
import org.nlogo.api.AgentException;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 * Returns boolean true if the calling turtle's CHREST instance has an 
 * associated mind's eye instance whose terminus value is greater than the 
 * current time, false otherwise.
 * 
 * One parameter must be passed when the Netlogo extension primitive that 
 * invokes this class is used in a Netlogo model:
 * 
 * Param #      Data Type       Description
 * -------      ---------       -----------
 * 1            Number          The current Netlogo time (in milliseconds).
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
class MindsEyeExist extends DefaultReporter {

  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(new int[]{Syntax.NumberType()}, Syntax.BooleanType());
  }
  
  @Override
  public Object report(Argument args[], Context context) throws ExtensionException, LogoException{
    boolean mindsEyeExist = false;
    
    try {
      if(BaseExtensionVariablesAndMethods.agentHasChrestInstance(context)){
        mindsEyeExist = BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(context).mindsEyeExists(args[0].getIntValue());
      }
    } catch (AgentException ex) {
      Logger.getLogger(MindsEyeExist.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    return mindsEyeExist;
  }
  
}
