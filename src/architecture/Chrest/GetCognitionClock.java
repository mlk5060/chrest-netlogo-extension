package architecture.Chrest;

import Shared.BaseExtensionVariablesAndMethods;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class GetCognitionClock extends DefaultReporter {
  
  @Override
  public Syntax getSyntax() {
    return Syntax.reporterSyntax(Syntax.NumberType());
  }

  /**
   * 
   * @param args
   * @param context
   * 
   * @return The result of invoking {@link 
   * jchrest.architecture.Chrest#getCognitionClock()} in context of the 
   * calling turtle's {@link jchrest.architecture.Chrest} instance.
   * 
   * @throws ExtensionException 
   */
  @Override
  public Object report(Argument[] argmnts, Context cntxt) throws ExtensionException, LogoException {
    return Double.valueOf(BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(cntxt).getCognitionClock());
  }
  
}
