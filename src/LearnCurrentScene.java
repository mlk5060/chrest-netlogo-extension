
import java.util.logging.Level;
import java.util.logging.Logger;
import jchrest.architecture.Chrest;
import jchrest.lib.Scene;
import org.nlogo.agent.Agent;
import org.nlogo.api.AgentException;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultCommand;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;

/**
 *
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class LearnCurrentScene extends DefaultCommand {

  @Override
  public void perform(Argument[] argmnts, Context cntxt) throws ExtensionException, LogoException {
    
    try {
      Agent callingAgent = BaseExtensionVariablesAndMethods.getAgent(cntxt);
      Object currentSceneObject = callingAgent.getBreedVariable("current-scene".toUpperCase());
      Object numberFixationsObject = callingAgent.getBreedVariable("number-fixations".toUpperCase());
      
      if(currentSceneObject instanceof Scene){
        if(numberFixationsObject instanceof Double){
          Scene currentScene = (Scene)currentSceneObject;
          int numberFixations = (int)Math.round( (double)numberFixationsObject );
          Chrest chrest = BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(cntxt);
          chrest.learnScene(currentScene, numberFixations);
        }
        else{
          throw new ExtensionException("The 'number-fixations' variable for turtle with ID " + callingAgent.id + " is not an instance of Double.");
        }
      }
      else{
        throw new ExtensionException("The 'current-scene' variable for turtle with ID " + callingAgent.id + " is not an instance of jchrest.lib.Scene.");
      }
      
    } catch (AgentException ex) {
      Logger.getLogger(LearnCurrentScene.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
    }
    
    
  }
  
}
