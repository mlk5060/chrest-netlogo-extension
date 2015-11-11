package DomainSpecifics;

import Shared.BaseExtensionVariablesAndMethods;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 * Returns result of invoking {@link java.lang.Class#getSimpleName()} on the
 * result of invoking {@link jchrest.architecture.Chrest#getDomainSpecifics()}
 * for the calling turtle.
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class GetCurrentDomainName extends DefaultReporter{
  
  @Override
  public Syntax getSyntax() {
    return Syntax.reporterSyntax(Syntax.StringType());
  }

  @Override
  public Object report(Argument[] argmnts, Context cntxt) throws ExtensionException, LogoException {
    return BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(cntxt).getDomainSpecifics().getClass().getSimpleName();
  }
  
}
