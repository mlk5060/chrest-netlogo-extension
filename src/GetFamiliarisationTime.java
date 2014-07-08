import java.util.logging.Level;
import java.util.logging.Logger;
import org.nlogo.api.AgentException;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;

/**
 * Reports the value that the calling turtle's "_familiarisationTime" variable 
 * is set to in its CHREST instance.
 * 
 * @author Martyn Lloyd-Kelly <mlk5060@liverpool.ac.uk>
 */
public class GetFamiliarisationTime extends DefaultReporter {
  
  @Override
  public Object report(Argument args[], Context context){
    Double familiarisationTime = null;
    
    try {
      if(BaseExtensionVariablesAndMethods.agentHasChrestInstance(context)){
        familiarisationTime = Double.valueOf(BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(context).getFamiliarisationTime());
      }
    } catch (AgentException ex) {
      Logger.getLogger(GetFamiliarisationTime.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    return familiarisationTime;
  }
  
}
