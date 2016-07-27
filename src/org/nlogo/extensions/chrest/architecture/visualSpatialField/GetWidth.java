package org.nlogo.extensions.chrest.architecture.visualSpatialField;

import jchrest.architecture.VisualSpatialField;
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
public class GetWidth extends DefaultReporter {

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
   * @param args A {@link jchrest.architecture.VisualSpatialField}.
   * @param context
   * 
   * @return The result of invoking {@link 
   * jchrest.architecture.VisualSpatialField#getWidth()} on the {@link 
   * jchrest.architecture.VisualSpatialField} passed as a parameter.
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    return (double)((VisualSpatialField)args[0].get()).getWidth();
  }
  
}
