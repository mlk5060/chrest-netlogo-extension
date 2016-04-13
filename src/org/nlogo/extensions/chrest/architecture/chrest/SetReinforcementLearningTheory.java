package org.nlogo.extensions.chrest.architecture.chrest;

import org.nlogo.extensions.chrest.ChrestExtension;
import jchrest.architecture.Chrest;
import jchrest.lib.ReinforcementLearning;
import jchrest.lib.ReinforcementLearning.ReinforcementLearningTheories;
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
   * Invokes {@link jchrest.architecture.Chrest#setReinforcementLearningTheory(
   * jchrest.lib.ReinforcementLearning.ReinforcementLearningTheories)} in 
   * context of the calling turtle's {@link jchrest.architecture.Chrest} 
   * instance.
   * 
   * @param args See parameters for {@link 
   * jchrest.architecture.Chrest#setReinforcementLearningTheory(
   * jchrest.lib.ReinforcementLearning.ReinforcementLearningTheories)}.  
   * <p>
   * The value for the first parameter can be obtained using {@link 
   * lib.ReinforcementLearning.Values#report(org.nlogo.api.Argument[], 
   * org.nlogo.api.Context)}.
   * @param context
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public void perform(Argument[] args, Context context) throws ExtensionException, LogoException {
    Chrest chrestInstance = ChrestExtension.getTurtlesChrestInstance(context);
    String specifiedTheory = args[0].getString();

    if(!specifiedTheory.equalsIgnoreCase("null") || !specifiedTheory.isEmpty()){
      ReinforcementLearningTheories[] reinforcementLearningTheories = ReinforcementLearning.getReinforcementLearningTheories();
      boolean reinforcementLearningTheoryRecognised = false;

      for(ReinforcementLearning.ReinforcementLearningTheories reinforcementLearningTheory : reinforcementLearningTheories){
        if(specifiedTheory.equalsIgnoreCase(reinforcementLearningTheory.toString())){
          reinforcementLearningTheoryRecognised = true;
          chrestInstance.setReinforcementLearningTheory(reinforcementLearningTheory);
          break;
        }
      }

      if(!reinforcementLearningTheoryRecognised){
        throw new ExtensionException("The reinforcement theory specified (" + specifiedTheory + ") is not a reinforcement learning theory recognised by CHREST.");
      }
    }
  }
}
