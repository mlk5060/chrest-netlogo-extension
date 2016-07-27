package org.nlogo.extensions.chrest.domainSpecifics.scene;

import jchrest.domainSpecifics.Scene;
import jchrest.lib.Square;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 *@author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class GetLocationOfCreator extends DefaultReporter {

  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(
      new int[] {
        Syntax.WildcardType()
      },
      Syntax.WildcardType()
    );
  }
  
  /**
   * 
   * @param args A {@link jchrest.domainSpecifics.Scene}.
   * @param context
   * 
   * @return The result of invoking {@link 
   * jchrest.domainSpecifics.Scene#getLocationOfCreator()} in context of the 
   * {@link jchrest.lib.Scene} passed as a parameter to this primitive.  If the
   * creator can not be found, an empty {@link java.lang.String} will be 
   * returned.
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    Square locationOfCreator = ((Scene)args[0].get()).getLocationOfCreator();
    return (locationOfCreator == null ? "" : locationOfCreator);
  }
  
}
