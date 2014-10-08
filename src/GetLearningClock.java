import java.util.logging.Level;
import java.util.logging.Logger;
import org.nlogo.api.AgentException;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;

/**
 * Reports the calling turtle's CHREST "_learningClock" value.
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class GetLearningClock extends DefaultReporter{
  
  @Override
  public Object report(Argument args[], Context context){
    Double chrestTime = null;
    
    try {
      if(BaseExtensionVariablesAndMethods.agentHasChrestInstance(context)){
        chrestTime = Double.valueOf(BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(context).getLearningClock());
      }
    } catch (AgentException ex) {
      Logger.getLogger(GetLearningClock.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    return chrestTime;
  }
}
