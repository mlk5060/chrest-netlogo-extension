
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
 * Learns the scene that is currently set to the calling turtle's breed variable 
 * that contains the current scene using CHREST perception methods.
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class LearnCurrentScene extends DefaultCommand {

  @Override
  public void perform(Argument[] argmnts, Context cntxt) throws ExtensionException, LogoException {
    
    try {
      Agent callingAgent = BaseExtensionVariablesAndMethods.getAgent(cntxt);
      Object currentSceneObject = callingAgent.getBreedVariable(BaseExtensionVariablesAndMethods.CURRENT_SCENE_BREED_VAR_NAME);
      Object numberFixationsObject = callingAgent.getBreedVariable(BaseExtensionVariablesAndMethods.NUMBER_FIXATIONS_BREED_VAR_NAME);
      
      if(currentSceneObject instanceof Scene){
        if(numberFixationsObject instanceof Double){
          int numberFixations = (int)Math.round( (double)numberFixationsObject );
          if(numberFixations > 0){
            Scene scene = (Scene)currentSceneObject;
            Chrest turtlesChrestInstance = BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(cntxt);
            
            //Set the field of view in the scene to the maximum value when the
            //current scene's width and height is compared.  This value should
            //then have 1 subtracted (this is equivalent to the x/y cor that the
            //calling agent is located on, giving an even number) and then 
            //divide by 2.  This allows for unequal width and heights to be 
            //dealt with easily.
            turtlesChrestInstance.getPerceiver().setFieldOfView( (Math.max(scene.getHeight(), scene.getWidth()) - 1) / 2);
            turtlesChrestInstance.learnScene(scene, numberFixations);
          }
          else{
            throw new ExtensionException("The '" + BaseExtensionVariablesAndMethods.NUMBER_FIXATIONS_BREED_VAR_NAME + "' variable for turtle with ID " + callingAgent.id + " is less than or equal to 0.");
          }
        }
        else{
          throw new ExtensionException("The '" + BaseExtensionVariablesAndMethods.NUMBER_FIXATIONS_BREED_VAR_NAME + "' variable for turtle with ID " + callingAgent.id + " is not an instance of Double.");
        }
      }
      else{
        throw new ExtensionException("The '" + BaseExtensionVariablesAndMethods.CURRENT_SCENE_BREED_VAR_NAME + "' variable for turtle with ID " + callingAgent.id + " is not an instance of jchrest.lib.Scene.");
      }
      
    } catch (AgentException ex) {
      Logger.getLogger(LearnCurrentScene.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
    }
  }
}
