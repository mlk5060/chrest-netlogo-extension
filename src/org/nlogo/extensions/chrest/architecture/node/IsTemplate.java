package org.nlogo.extensions.chrest.architecture.node;

import jchrest.architecture.Node;
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
public class IsTemplate extends DefaultReporter {
  
  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(
      new int[]{
        Syntax.WildcardType(),
        Syntax.NumberType()
      },
      Syntax.BooleanType()
    );
  }

  /**
   * 
   * @param args First parameter should be a {@link 
   * jchrest.architecture.Node}.  For other parameters see {@link 
   * jchrest.architecture.Node#isTemplate(int)}.
   * @param context
   * 
   * @return The result of invoking {@link 
   * jchrest.architecture.Node#isTemplate(int)} in context of the {@link 
   * jchrest.architecture.Node} passed as a parameter to this primitive. 
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    return ((Node)args[0].get()).isTemplate(args[1].getIntValue());
  }
}
