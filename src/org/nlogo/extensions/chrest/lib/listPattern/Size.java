package org.nlogo.extensions.chrest.lib.listPattern;

import jchrest.lib.ListPattern;
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
public class Size extends DefaultReporter {

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
   * @param args A {@link jchrest.lib.ListPattern}.
   * @param context
   * 
   * @return The result of invoking {@link jchrest.lib.ListPattern#size()} on
   * the {@link jchrest.lib.ListPattern} passed as a parameter to {@link #this}.
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    return (double)((ListPattern)args[0].get()).size();
  }
  
}
