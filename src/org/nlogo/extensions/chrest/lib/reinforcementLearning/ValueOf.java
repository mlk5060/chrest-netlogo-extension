package org.nlogo.extensions.chrest.lib.reinforcementLearning;

import jchrest.lib.ReinforcementLearning;
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
public class ValueOf extends DefaultReporter {
  
  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(
      new int[]{
        Syntax.StringType()
      }, 
      Syntax.WildcardType()
    );
  }

  /**
   * 
   * @param args
   * @param context
   * 
   * @return The result of invoking {@link 
   * jchrest.lib.ReinforcementLearning.Theory#valueOf(java.lang.String)} when
   * the {@link java.lang.String} specified as a parameter is passed.
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    return ReinforcementLearning.Theory.valueOf(args[0].getString());
  }
  
}
