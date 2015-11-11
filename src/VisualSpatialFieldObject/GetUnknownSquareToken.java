package VisualSpatialFieldObject;

import jchrest.lib.VisualSpatialFieldObject;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 * Returns the result of invoking {@link 
 * jchrest.lib.VisualSpatialFieldObject#getUnknownSquareToken()}.
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class GetUnknownSquareToken extends DefaultReporter{

  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(Syntax.StringType());
  }
  
  @Override
  public Object report(Argument[] argmnts, Context cntxt) throws ExtensionException, LogoException {
    return VisualSpatialFieldObject.getUnknownSquareToken();
  }
  
}
