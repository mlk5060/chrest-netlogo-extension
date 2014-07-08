import java.util.logging.Level;
import java.util.logging.Logger;
import org.nlogo.api.AgentException;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;

/**
 * Reports the value that the calling turtle's "_addLinkTime" variable is set to
 * in its CHREST instance.
 * 
 * @author Martyn Lloyd-Kelly <mlk5060@liverpool.ac.uk>
 */
public class GetAddLinkTime extends DefaultReporter {
  
  @Override
  public Object report(Argument args[], Context context){
    Double addLinkTime = null;
    
    try {
      if(BaseExtensionVariablesAndMethods.agentHasChrestInstance(context)){
        addLinkTime = Double.valueOf(BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(context).getAddLinkTime());
      }
    } catch (AgentException ex) {
      Logger.getLogger(GetAddLinkTime.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    return addLinkTime;
  }
}
