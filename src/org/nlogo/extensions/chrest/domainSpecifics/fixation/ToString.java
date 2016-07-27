package org.nlogo.extensions.chrest.domainSpecifics.fixation;

import jchrest.domainSpecifics.Fixation;
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
public class ToString extends DefaultReporter {

  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(
      new int[]{
        Syntax.WildcardType()
      },
      Syntax.StringType()
    );
  }
  
  /**
   * 
   * @param args A {@link jchrest.domainSpecifics.Fixation}.
   * @param context
   * 
   * @return The result of invoking {@link 
   * jchrest.domainSpecifics.Fixation#toString()} on the parameter passed to 
   * this primitive.
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    return ((Fixation)args[0].get()).toString();
  }
  
}
