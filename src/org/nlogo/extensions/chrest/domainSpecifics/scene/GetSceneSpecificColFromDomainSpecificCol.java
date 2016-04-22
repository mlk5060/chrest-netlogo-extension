package org.nlogo.extensions.chrest.domainSpecifics.scene;

import jchrest.domainSpecifics.Scene;
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
public class GetSceneSpecificColFromDomainSpecificCol extends DefaultReporter{

  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(
      new int[]{
        Syntax.WildcardType(),
        Syntax.NumberType()
      },
      Syntax.NumberType()
    );
  }
  
  /**
   * 
   * @param args The first parameter should be a {@link 
   * jchrest.domainSpecifics.Scene}.  For other parameters see {@link 
   * jchrest.domainSpecifics.Scene#getSceneSpecificColFromDomainSpecificCol(int)
   * }.
   * @param context
   * 
   * @return The result of invoking {@link 
   * jchrest.domainSpecifics.Scene#getSceneSpecificColFromDomainSpecificCol(
   * int)} in context of the parameters passed to this primitive.
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    return ((Scene)args[0].get()).getSceneSpecificColFromDomainSpecificCol(args[1].getIntValue());
  }
  
}
