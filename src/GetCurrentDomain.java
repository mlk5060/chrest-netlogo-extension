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
 * Returns the simple name (result of {@link java.lang.Class#getSimpleName()}) 
 * of the domain class that the calling turtle's CHREST model is currently set 
 * to.
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class GetCurrentDomain extends DefaultReporter{
  
  @Override
  public Syntax getSyntax() {
    return Syntax.reporterSyntax(Syntax.StringType());
  }

  @Override
  public Object report(Argument[] argmnts, Context cntxt) throws ExtensionException, LogoException {
    String currentDomain = "";
    
    try {
      currentDomain = BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(cntxt).getDomainSpecifics().getClass().getSimpleName();
    } catch (AgentException ex) {
      Logger.getLogger(GetCurrentDomain.class.getName()).log(Level.SEVERE, "", ex);
    }
    
    return currentDomain;
  }
  
}
