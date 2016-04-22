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
public class GetReference extends DefaultReporter{

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
   * @param args A {@link jchrest.architecture.Node}.
   * @param context
   * 
   * @return The result of invoking {@link 
   * jchrest.architecture.Node#getReference()} on the {@link 
   * jchrest.architecture.Node} passed as a parameter to this primitive.
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    return Double.parseDouble( "" + ((Node)args[0].get()).getReference());
  }
  
}
