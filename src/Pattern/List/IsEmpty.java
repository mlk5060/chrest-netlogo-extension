package Pattern.List;

import jchrest.lib.ListPattern;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 * Returns the result of invoking {@link jchrest.lib.ListPattern#isEmpty()}.
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class IsEmpty extends DefaultReporter {

  @Override
  public Syntax getSyntax() {
    return Syntax.reporterSyntax(
      new int[]{
        Syntax.WildcardType() //ListPattern
      },
      Syntax.BooleanType()
    );
  }

  @Override
  public Object report(Argument[] args, Context cntxt) throws ExtensionException, LogoException {
    return ((ListPattern)args[0].get()).isEmpty();
  }
  
}
