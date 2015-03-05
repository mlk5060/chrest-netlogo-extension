import java.util.logging.Level;
import java.util.logging.Logger;
import org.nlogo.api.AgentException;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;

/**
 * Reports the calling turtle's CHREST "_clock" value.
 * 
 * @author Martyn Lloyd-Kelly <mlk5060@liverpool.ac.uk>
 */
public class GetChrestClock extends DefaultReporter{
  
  @Override
  public Object report(Argument args[], Context context){
    Double chrestTime = null;
    
    try {
      if(BaseExtensionVariablesAndMethods.agentHasChrestInstance(context)){
        chrestTime = Double.valueOf(BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(context).getClock());
      }
    } catch (AgentException ex) {
      Logger.getLogger(GetChrestClock.class.getName()).log(Level.SEVERE, ex);
    }
    
    return chrestTime;
  }
}