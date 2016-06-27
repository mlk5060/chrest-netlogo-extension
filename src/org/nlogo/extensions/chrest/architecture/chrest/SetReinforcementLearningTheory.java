package org.nlogo.extensions.chrest.architecture.chrest;

import org.nlogo.extensions.chrest.ChrestExtension;
import jchrest.lib.ReinforcementLearning.Theory;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultCommand;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 * Invokes {@link jchrest.architecture.Chrest#setReinforcementLearningTheory(
 * jchrest.lib.ReinforcementLearning.ReinforcementLearningTheories)} for the
 * calling turtle.
 * 
 * All {@link java.lang.String} representations of reinforcement learning 
 * theories currently supported by CHREST can be obtained as a {@link 
 * org.nlogo.api.LogoList} by using the primitive that invokes {@link 
 * Reinforcement.GetReinforcementLearningTheories} in this extension.
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liv.ac.uk>
 */
public class SetReinforcementLearningTheory extends DefaultCommand {

  @Override
  public Syntax getSyntax(){
    return Syntax.commandSyntax(
      new int[] {
        Syntax.StringType()
      }
    );
  }
  
  /**
   * Invokes {@link 
   * jchrest.architecture.Chrest#setReinforcementLearningTheory(jchrest.lib.ReinforcementLearning.Theory) 
   * } in context of the calling turtle's {@link jchrest.architecture.Chrest} 
   * model.
   * 
   * @param args See parameters for {@link 
   * jchrest.architecture.Chrest#setReinforcementLearningTheory(jchrest.lib.ReinforcementLearning.Theory) 
   * }.
   * @param context
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public void perform(Argument[] args, Context context) throws ExtensionException, LogoException {
    ChrestExtension.getTurtlesChrestInstance(context).setReinforcementLearningTheory((Theory)args[0].get());
  }
}
