package org.nlogo.extensions.chrest.lib.visualSpatialFieldObject;

import jchrest.lib.VisualSpatialFieldObject;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class GetUnknownSquareToken extends DefaultReporter{

  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(Syntax.StringType());
  }
  
  /**
   * 
   * @param argmnts
   * @param context
   * 
   * @return The result of {@link 
   * jchrest.lib.VisualSpatialFieldObject#getUnknownSquareToken()}.
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Object report(Argument[] argmnts, Context context) throws ExtensionException, LogoException {
    return VisualSpatialFieldObject.getUnknownSquareToken();
  }
  
}
