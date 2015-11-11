package Chrest;

import Shared.BaseExtensionVariablesAndMethods;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 * Returns the result of invoking {@link 
 * jchrest.architecture.Chrest#getLearningClock()} for the calling turtle.
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class GetLearningClock extends DefaultReporter {
  
  @Override
  public Syntax getSyntax() {
    return Syntax.reporterSyntax(Syntax.NumberType());
  }

  @Override
  public Object report(Argument[] argmnts, Context cntxt) throws ExtensionException, LogoException {
    return Double.valueOf(BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(cntxt).getLearningClock());
  }
  
}
