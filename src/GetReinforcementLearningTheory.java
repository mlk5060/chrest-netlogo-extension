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
 * Reports the value that the calling turtle's "_reinforcementLearningTheory" 
 * variable is set to in its CHREST instance.
 * 
 * @author Martyn Lloyd-Kelly <mlk5060@liverpool.ac.uk>
 */
public class GetReinforcementLearningTheory extends DefaultReporter {
  
  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(new int[]{}, Syntax.StringType());
  }

  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    String reinforcementLearningTheory = "";
    
    try {
      if(BaseExtensionVariablesAndMethods.agentHasChrestInstance(context)){
        reinforcementLearningTheory = BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(context).getReinforcementLearningTheory();
      }
    } catch (AgentException ex) {
      Logger.getLogger(GetReinforcementLearningTheory.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
    }
    
    return reinforcementLearningTheory;
  }
}
