package org.nlogo.extensions.chrest.domainSpecifics.scene;

import jchrest.domainSpecifics.Scene;
import jchrest.domainSpecifics.SceneObject;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 * Returns the result of packaging the results of invoking {@link 
 * jchrest.lib.SceneObject#getIdentifier()} and {@link 
 * jchrest.lib.SceneObject#getObjectClass()} on the result of invoking
 * {@link jchrest.lib.Scene#getSquareContents(int, int)} as a .
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class GetSquareContents extends DefaultReporter {
  
  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(
      new int[]{
        Syntax.WildcardType(),
        Syntax.NumberType(),
        Syntax.NumberType()
      }, 
      Syntax.WildcardType()
    );
  }

  /**
   * 
   * @param args The first parameter should be the {@link 
   * jchrest.domainSpecifics.Scene} that {@link 
   * jchrest.domainSpecifics.Scene#getSquareContents(int, int)} is to be invoked
   * in context of. For other parameters see {@link 
   * jchrest.domainSpecifics.Scene#getSquareContents(int, int)}.
   * @param context
   * 
   * @return The result of invoking {@link 
   * jchrest.domainSpecifics.Scene#getSquareContents(int, int)} in context of 
   * the {@link jchrest.domainSpecifics.Scene} passed as the first parameter to 
   * this primitive.  If {@code null} is returned by {@link 
   * jchrest.domainSpecifics.Scene#getSquareContents(int, int)}, an empty {@link 
   * java.lang.String) will be returned.
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    SceneObject squareContents = ((Scene)args[0].get()).getSquareContents(args[1].getIntValue(), args[2].getIntValue());
    return (squareContents == null ? "" : squareContents);
  }
  
}
