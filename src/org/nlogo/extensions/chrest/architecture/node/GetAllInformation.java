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
public class GetAllInformation extends DefaultReporter {

  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(
      new int[]{
        Syntax.WildcardType(),
        Syntax.NumberType()
      },
      Syntax.WildcardType()
    );
  }
  
  /**
   * 
   * @param args The first argument should be the {@link 
   * jchrest.architecture.Node} to invoke {@link 
   * jchrest.architecture.Node#getAllInformation(int)} in context of.  For other
   * arguments, see parameter descriptions for {@link 
   * jchrest.architecture.Node#getAllInformation(int)}.
   * @param context
   * 
   * @return The result of invoking {@link 
   * jchrest.architecture.Node#getAllInformation(int)} using the {@code args}
   * specified.
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    return ((Node)args[0].get()).getAllInformation(args[1].getIntValue());
  }
  
}
