package Scene;

import jchrest.lib.Scene;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 * Returns the result of invoking {@link jchrest.lib.Scene#getCreatorToken()}.
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class GetCreatorToken extends DefaultReporter {
  
  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(Syntax.StringType());
  }

  @Override
  public Object report(Argument[] argmnts, Context cntxt) throws ExtensionException, LogoException {
    return Scene.getCreatorToken();
  }
}
