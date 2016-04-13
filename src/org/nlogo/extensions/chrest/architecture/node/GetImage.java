package org.nlogo.extensions.chrest.architecture.node;

import jchrest.architecture.Node;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class GetImage extends DefaultReporter {
  
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
   * @param args The first parameter should be the {@link 
   * jchrest.architecture.Node} that {@link 
   * jchrest.architecture.Node#getImage(int)} will be invoked in context of. For
   * other parameters, see {@link jchrest.architecture.Node#getImage(int)}
   * @param cntxt
   * 
   * @return  The result of invoking {@link 
   * jchrest.architecture.Node#getImage(int)} for the {@link 
   * jchrest.architecture.Node} passed as a parameter to this primitive.
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Object report(Argument[] args, Context cntxt) throws ExtensionException, LogoException {
    return ((Node)args[0].get()).getImage(args[1].getIntValue());
  }

}
