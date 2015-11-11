package Chrest;

import Shared.BaseExtensionVariablesAndMethods;
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
  
  @Override
  public void perform(Argument[] args, Context context) throws ExtensionException, LogoException {
    Chrest chrestInstance = BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(context);
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
