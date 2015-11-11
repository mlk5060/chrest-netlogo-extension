package Pattern.ItemSquare;

import jchrest.lib.ItemSquarePattern;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 * Returns the result of invoking {@link 
 * jchrest.lib.ItemSquarePattern#toString()}.
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class GetAsString extends DefaultReporter {
  
  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(
      new int[]{
        Syntax.WildcardType() //ItemSquarePattern to retrieve as a String.
      }, 
      Syntax.StringType()
    );
  }

  @Override
  public Object report(Argument[] args, Context cntxt) throws ExtensionException, LogoException {
    return ((ItemSquarePattern)args[0].get()).toString();
  }
}
