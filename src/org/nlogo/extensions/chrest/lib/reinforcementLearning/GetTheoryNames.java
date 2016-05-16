package org.nlogo.extensions.chrest.lib.reinforcementLearning;

import jchrest.lib.ReinforcementLearning;
import jchrest.lib.ReinforcementLearning.Theory;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.LogoListBuilder;
import org.nlogo.api.Syntax;

/**
 * @author Martyn Lloyd-Kelly <martynlk@liv.ac.uk>
 */
public class GetTheoryNames extends DefaultReporter {
  
  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(Syntax.ListType());
  }

  /**
   * 
   * @param args
   * @param context
   * 
   * @return The result of invoking {@link java.lang.Enum#toString()} on each 
   * {@link java.lang.Enum} returned by {@link 
   * jchrest.lib.ReinforcementLearning#getReinforcementLearningTheories()} as a
   * {@link org.nlogo.api.LogoList}
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    LogoListBuilder reinforcementLearningTheoriesList = new LogoListBuilder();
    Theory[] reinforcementLearningTheories = ReinforcementLearning.getReinforcementLearningTheories();
    for (Theory reinforcementLearningTheory : reinforcementLearningTheories) {
      reinforcementLearningTheoriesList.add(reinforcementLearningTheory.toString());
    }
    return reinforcementLearningTheoriesList.toLogoList();
  }
}
