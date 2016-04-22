package org.nlogo.extensions.chrest.domainSpecifics.scene;

import jchrest.domainSpecifics.Scene;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class GetEmptySquareToken extends DefaultReporter{
  
  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(Syntax.StringType());
  }

  /**
   * 
   * @param args
   * @param context
   * 
   * @return The result of {@link jchrest.domainSpecifics.Scene#getEmptySquareToken()}.
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    return Scene.getEmptySquareToken();
  }
}
