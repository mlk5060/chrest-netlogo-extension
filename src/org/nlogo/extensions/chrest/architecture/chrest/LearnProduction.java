package org.nlogo.extensions.chrest.architecture.chrest;

import jchrest.lib.ListPattern;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;
import org.nlogo.extensions.chrest.ChrestExtension;

/**
 *
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class LearnProduction extends DefaultReporter {
  
  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(
      new int[]{
        Syntax.WildcardType(),
        Syntax.WildcardType(),
        Syntax.NumberType()
      }, 
      Syntax.BooleanType()
    );
  }

  /**
   * 
   * @param args See {@link 
   * jchrest.architecture.Chrest#learnProduction(jchrest.lib.ListPattern, 
   * jchrest.lib.ListPattern, int)}.
   * @param context
   * 
   * @return The result of invoking {@link 
   * jchrest.architecture.Chrest#learnProduction(jchrest.lib.ListPattern, 
   * jchrest.lib.ListPattern, int)} with the primitive parameters specified in 
   * context of the calling {@link org.nlogo.agent.Agent Agent's} {@link 
   * jchrest.architecture.Chrest} model.
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    return ChrestExtension.getTurtlesChrestInstance(context).learnProduction(
      (ListPattern)args[0].get(), 
      (ListPattern)args[1].get(), 
      args[2].getIntValue());
  }
  
}
