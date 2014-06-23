
import java.util.logging.Level;
import java.util.logging.Logger;
import org.nlogo.api.AgentException;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;


/**
 *
 * @author Martyn Lloyd-Kelly <mlk5060@liverpool.ac.uk>
 */
public class GetReinforcementLearningTheory extends DefaultReporter {

  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    String reinforcementLearningTheory = "";
    
    try {
      if(BaseExtensionVariablesAndMethods.agentHasChrestInstance(context)){
        reinforcementLearningTheory = BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(context).getReinforcementLearningTheory().toString();
      }
    } catch (AgentException ex) {
      Logger.getLogger(GetReinforcementLearningTheory.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    return reinforcementLearningTheory;
  }
}