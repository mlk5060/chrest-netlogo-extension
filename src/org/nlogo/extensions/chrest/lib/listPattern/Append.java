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
public class Append extends DefaultReporter {
  
  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(
      new int[]{
        Syntax.WildcardType(),
        Syntax.WildcardType()
      }, 
      Syntax.WildcardType()
    );
  }

  /**
   * 
   * @param args Two {@link jchrest.lib.ListPattern ListPatterns}, the first is
   * the {@link jchrest.lib.ListPattern} to append the second {@link 
   * jchrest.lib.ListPattern} to.
   * @param context
   * 
   * @return The result of invoking {@link 
   * jchrest.lib.ListPattern#append(jchrest.lib.ListPattern)} on the first 
   * {@link jchrest.lib.ListPattern} passed as a parameter to this primitive and
   * the second {@link jchrest.lib.ListPattern} passed as a parameter to
   * {@link jchrest.lib.ListPattern#append(jchrest.lib.ListPattern)}.
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    return ((ListPattern)args[0].get()).append((ListPattern)args[1].get());
  }
  
}
