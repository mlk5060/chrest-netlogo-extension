package Reinforcement;

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
 * Returns the result of invoking
 * {@link java.lang.Enum#toString()} on each {@link java.lang.Enum} returned by
 * invoking {@link 
 * jchrest.lib.ReinforcementLearning#getReinforcementLearningTheories()} as 
 * a {@link org.nlogo.api.LogoList}.
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liv.ac.uk>
 */
public class GetReinforcementLearningTheories extends DefaultReporter {
  
  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(Syntax.ListType());
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
