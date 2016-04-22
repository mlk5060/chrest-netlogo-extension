package org.nlogo.extensions.chrest.architecture.chrest;

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
public class IsAttentionFree extends DefaultReporter{

  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(
      new int[]{
        Syntax.NumberType()
      },
      Syntax.BooleanType()
    );
  }
  
  /**
   * 
   * @param args See parameter description for {@link 
   * jchrest.architecture.Chrest#isAttentionFree(int)}.
   * @param context
   * 
   * @return The result of invoking {@link 
   * jchrest.architecture.Chrest#isAttentionFree(int)} with the parameter passed
   * to this primitive in context of the calling {@link org.nlogo.agent.Agent 
   * Agent's} {@link jchrest.architecture.Chrest} instance.
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    return ChrestExtension.getTurtlesChrestInstance(context).isAttentionFree(args[0].getIntValue());
  }
  
}
