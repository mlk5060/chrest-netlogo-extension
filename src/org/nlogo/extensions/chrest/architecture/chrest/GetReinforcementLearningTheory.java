package org.nlogo.extensions.chrest.architecture.chrest;

import jchrest.lib.ReinforcementLearning.Theory;
import org.nlogo.extensions.chrest.ChrestExtension;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 * @author Martyn Lloyd-Kelly <martynlk@liv.ac.uk>
 */
public class GetReinforcementLearningTheory extends DefaultReporter {
  
  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(Syntax.StringType());
  }

  /**
   * 
   * @param args
   * @param context
   * 
   * @return The result of invoking {@link 
   * jchrest.architecture.Chrest#getReinforcementLearningTheory()} in context of 
   * the calling turtle's {@link jchrest.architecture.Chrest} instance.  If this
   * is {@code null}, an empty string is returned.
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    Theory reinforcementLearningTheory = ChrestExtension.getTurtlesChrestInstance(context).getReinforcementLearningTheory();
    return (reinforcementLearningTheory == null ? "" : reinforcementLearningTheory);
  }
}
