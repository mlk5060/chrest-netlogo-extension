import jchrest.lib.DomainSpecifics;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.LogoListBuilder;
import org.nlogo.api.Syntax;

/**
 * Gets the result of {@link jchrest.lib.DomainSpecifics#getDeclaredDomains()}
 * from the CHREST package used by the calling turtle and packages the elements
 * into a {@link org.nlogo.api.LogoList}.
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class GetDeclaredDomains extends DefaultReporter{
  
  @Override
  public Syntax getSyntax() {
    return Syntax.reporterSyntax(Syntax.ListType());
  }

  @Override
  public Object report(Argument[] argmnts, Context cntxt) throws ExtensionException, LogoException {
    LogoListBuilder declaredDomainSpecificNamesAndClasses = new LogoListBuilder();
    declaredDomainSpecificNamesAndClasses.addAll(DomainSpecifics.getDeclaredDomains());
    return declaredDomainSpecificNamesAndClasses.toLogoList();
  }
  
}
