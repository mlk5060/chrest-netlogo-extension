package org.nlogo.extensions.chrest.domainSpecifics.tileworld;

import jchrest.domainSpecifics.tileworld.TileworldDomain;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 * Returns the result of invoking {@link 
 * jchrest.lib.TileworldDomain#getHoleIdentifier()}.
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class GetHoleSceneObjectTypeToken extends DefaultReporter {
  
  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(Syntax.StringType());
  }

  @Override
  public Object report(Argument[] args, Context cntxt) throws ExtensionException, LogoException {
    return TileworldDomain.HOLE_SCENE_OBJECT_TYPE_TOKEN;
  }
  
}
