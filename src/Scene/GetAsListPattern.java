package Scene;

import jchrest.lib.Scene;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 * Returns the result of invoking {@link 
 * jchrest.lib.Scene#getAsListPattern(boolean, boolean)}.
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class GetAsListPattern extends DefaultReporter {
  
  @Override
  public Syntax getSyntax() {
    return Syntax.reporterSyntax(
      new int[]{
        Syntax.WildcardType(), //Scene
        Syntax.BooleanType(), //Creator-relative coordinates?
        Syntax.BooleanType() //Identify objects by object class?
      },
      Syntax.WildcardType()
    );
  }

  @Override
  public Object report(Argument[] args, Context cntxt) throws ExtensionException, LogoException {
    return ((Scene)args[0].get()).getAsListPattern(args[1].getBooleanValue(), args[2].getBooleanValue());
  }
  
}
