import java.util.logging.Level;
import java.util.logging.Logger;
import jchrest.architecture.Chrest;
import jchrest.lib.ReinforcementLearning;
import jchrest.lib.ReinforcementLearning.ReinforcementLearningTheories;
import org.nlogo.api.AgentException;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultCommand;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 * Sets the calling turtle's "_reinforcementTheory" variable in its CHREST 
 * architecture to the reinforcment learning enum value specified.  All 
 * reinforcement learning theories currently supported by CHREST can be obtained
 * as a Netlogo list by using the "get-reinforcement-theories primitive in this
 * extension {@link #GetReinforcementLearningTheories}.
 * 
 * One parameter must be passed when the Netlogo extension primitive that 
 * invokes this class is used in a Netlogo model:
 * 
 * Param #      Data Type       Description
 * -------      ---------       -----------
 * 1            String          The reinforcement learning theory enum value 
 *                              that the calling turtle's _reinforcementTheory
 *                              variable should be set to.
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liv.ac.uk>
 */
public class SetReinforcementLearningTheory extends DefaultCommand {

  @Override
  public Syntax getSyntax(){
    return Syntax.commandSyntax(new int[] {Syntax.StringType()});
  }
  
  @Override
  public void perform(Argument[] args, Context context) throws ExtensionException, LogoException {
    try {
      if(BaseExtensionVariablesAndMethods.agentHasChrestInstance(context)){
        Chrest chrestInstance = BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(context);
        String specifiedTheory = args[0].getString();
        
        if(!specifiedTheory.equalsIgnoreCase("null") || !specifiedTheory.isEmpty()){
          ReinforcementLearningTheories[] reinforcementLearningTheories = ReinforcementLearning.getReinforcementLearningTheories();
          for(ReinforcementLearning.ReinforcementLearningTheories reinforcementLearningTheory : reinforcementLearningTheories){
            if(specifiedTheory.equalsIgnoreCase(reinforcementLearningTheory.toString())){
              chrestInstance.setReinforcementLearningTheory(reinforcementLearningTheory);
              break;
            }
          }
        }
      }
    } catch (AgentException ex) {
      Logger.getLogger(SetReinforcementLearningTheory.class.getName()).log(Level.SEVERE,"", ex);
    }
  }
}
