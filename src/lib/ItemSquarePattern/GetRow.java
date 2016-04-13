package lib.ItemSquarePattern;

import jchrest.lib.ItemSquarePattern;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class GetRow extends DefaultReporter {
  
  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(
      new int[]{
        Syntax.WildcardType()
      }, 
      Syntax.NumberType()
    );
  }

  /**
   * 
   * @param The {@link jchrest.lib.ItemSquarePattern} to invoke {@link 
   * jchrest.lib.ItemSquarePattern#getRow()} in context of.
   * @param context
   * 
   * @return The result of invoking {@link 
   * jchrest.lib.ItemSquarePattern#getRow()} on the {@link 
   * jchrest.lib.ItemSquarePattern} passed as a parameter to this primitive.
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    return (double)((ItemSquarePattern)args[0].get()).getRow();
  }
}
