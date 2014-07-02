
import jchrest.lib.ReinforcementLearning;
import jchrest.lib.ReinforcementLearning.ReinforcementLearningTheories;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.LogoListBuilder;
import org.nlogo.api.Syntax;

/**
 * Returns a list of all enum values from the "jchrest.lib.ReinforcementLearning"
 * class in the CHREST architecture as strings within a Netlogo list.
 * 
 * @author Martyn Lloyd-Kelly <mlk5060@liverpool.ac.uk>
 */
public class GetReinforcementLearningTheories extends DefaultReporter {
  
  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(new int[]{}, Syntax.ListType());
  }

  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    LogoListBuilder reinforcementLearningTheoriesList = new LogoListBuilder();
    ReinforcementLearningTheories[] reinforcementLearningTheories = ReinforcementLearning.getReinforcementLearningTheories();
    for (ReinforcementLearningTheories reinforcementLearningTheory : reinforcementLearningTheories) {
      reinforcementLearningTheoriesList.add(reinforcementLearningTheory.toString());
    }
    return reinforcementLearningTheoriesList.toLogoList();
  }
}
