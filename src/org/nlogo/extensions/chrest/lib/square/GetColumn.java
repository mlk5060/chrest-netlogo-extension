package org.nlogo.extensions.chrest.lib.square;

import jchrest.lib.Square;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 *
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class GetColumn extends DefaultReporter {

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
   * @param args A {@link jchrest.lib.Square}.
   * @param context
   * 
   * @return The result of invoking {@link jchrest.lib.Square#getColumn()} in 
   * context of the {@link jchrest.lib.Square} passed as a parameter to this
   * primitive.
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    return (double)(((Square)args[0].get()).getColumn());
  }
  
}
