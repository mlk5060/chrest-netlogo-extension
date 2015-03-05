import java.util.logging.Level;
import java.util.logging.Logger;
import org.nlogo.api.AgentException;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;

/**
 * Reports the value that the calling turtle's "_discriminationTime" variable is 
 * set to in its CHREST instance.
 * 
 * @author Martyn Lloyd-Kelly <mlk5060@liverpool.ac.uk>
 */
public class GetDiscriminationTime extends DefaultReporter {
  
  @Override
  public Object report(Argument args[], Context context){
    Double discriminationTime = null;
    
    try {
      if(BaseExtensionVariablesAndMethods.agentHasChrestInstance(context)){
        discriminationTime = Double.valueOf(BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(context).getDiscriminationTime());
      }
    } catch (AgentException ex) {
      Logger.getLogger(GetDiscriminationTime.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
    }
    
    return discriminationTime;
  }
}
