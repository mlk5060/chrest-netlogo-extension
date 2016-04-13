package org.nlogo.extensions.chrest.lib.listPattern;

import jchrest.lib.ListPattern;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class IsEmpty extends DefaultReporter {

  @Override
  public Syntax getSyntax() {
    return Syntax.reporterSyntax(
      new int[]{
        Syntax.WildcardType()
      },
      Syntax.BooleanType()
    );
  }

  /**
   * 
   * @param args The {@link jchrest.lib.ListPattern} to invoke {@link 
   * jchrest.lib.ListPattern#isEmpty()} in context of.
   * @param context
   * 
   * @return The result of invoking {@link jchrest.lib.ListPattern#isEmpty()} on
   * the {@link jchrest.lib.ListPattern} passed as a parameter to this 
   * primitive.
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    return ((ListPattern)args[0].get()).isEmpty();
  }
  
}
