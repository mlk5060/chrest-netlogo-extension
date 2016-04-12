package domainSpecifics.Scene;

import jchrest.lib.Scene;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class GetAsListPattern extends DefaultReporter {
  
  @Override
  public Syntax getSyntax() {
    return Syntax.reporterSyntax(
      new int[]{
        Syntax.WildcardType(),
        Syntax.BooleanType()
      },
      Syntax.WildcardType()
    );
  }

  /**
   * 
   * @param args The first parameter should be the {@link jchrest.lib.Scene} 
   * that {@link jchrest.lib.Scene#getAsListPattern(boolean)} will be invoked in
   * context of.  For other parameters see {@link 
   * jchrest.lib.Scene#getAsListPattern(boolean)}.
   * @param context
   * 
   * @return The result of invoking {@link 
   * jchrest.lib.Scene#getAsListPattern(boolean)} in context of the {@link 
   * jchrest.lib.Scene} passed as a parameter to this primitive.
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    return ((Scene)args[0].get()).getAsListPattern(args[1].getBooleanValue());
  }
  
}
