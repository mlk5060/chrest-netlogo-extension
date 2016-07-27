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
public class GetSceneSpecificRowFromDomainSpecificRow extends DefaultReporter{
  
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
   * jchrest.domainSpecifics.Scene#getSceneSpecificRowFromDomainSpecificRow(int)
   * }.
   * @param context
   * 
   * @return The result of invoking {@link 
   * jchrest.domainSpecifics.Scene#getSceneSpecificRowFromDomainSpecificRow(
   * int)} in context of the parameters passed to this primitive.
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    return (double)((Scene)args[0].get()).getSceneSpecificRowFromDomainSpecificRow(args[1].getIntValue());
  }
}
