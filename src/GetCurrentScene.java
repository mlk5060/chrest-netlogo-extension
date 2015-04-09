import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import jchrest.lib.ItemSquarePattern;
import jchrest.lib.ListPattern;
import jchrest.lib.PrimitivePattern;
import jchrest.lib.Scene;
import org.nlogo.agent.Agent;
import org.nlogo.api.AgentException;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.LogoListBuilder;
import org.nlogo.api.Syntax;

/**
 * Returns a Netlogo list representation of the calling turtle's "current-scene"
 * turtle variable.
 * 
 * One parameter must be passed when the Netlogo extension primitive that 
 * invokes this class is used in a Netlogo model:
 * 
 * Param #      Data Type       Description
 * -------      ---------       -----------
 * 1            Boolean         Set to true to have object coordinates be 
 *                              relative to the scene creator (if present in
 *                              scene) i.e. make scene egocentric.  Set to false
 *                              to have object coordinates equal the scene
 *                              coordinates even if scene creator is present in
 *                              the scene i.e. make scene allocentric.
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class GetCurrentScene extends DefaultReporter{
  
  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(new int[]{Syntax.BooleanType()}, Syntax.ListType());
  }

  @Override
  public Object report(Argument[] args, Context cntxt) throws ExtensionException, LogoException {
    LogoListBuilder sceneAsList = new LogoListBuilder();
    Agent callingAgent = BaseExtensionVariablesAndMethods.getAgent(cntxt);
    
    try {
      
      //First, attempt to retrieve the contents of the calling agent's 
      //"current-scene" turtle-variable.  If this is an instance of "Scene" then
      //continue by casting the retrieved contents to "Scene".
      Object currentScene = callingAgent.getBreedVariable(BaseExtensionVariablesAndMethods.CURRENT_SCENE_BREED_VAR_NAME);
      if(currentScene instanceof Scene){
        Scene currentSceneAsScene = (Scene)currentScene;
        ListPattern currentSceneListPattern = currentSceneAsScene.getEntireScene(args[0].getBoolean());
        Iterator<PrimitivePattern> currentSceneContents = currentSceneListPattern.iterator();
        while(currentSceneContents.hasNext()){
          
          //Get the scene square, create a new ItemSquarePattern instance, get 
          //its string representation and add it to the Netlogo list to be 
          //returned.  Getting a ListPattern string keeps formatting of CHREST 
          //info so aids understanding for modeller.
          ItemSquarePattern sceneItem = (ItemSquarePattern)currentSceneContents.next();
          sceneAsList.add( new ItemSquarePattern(sceneItem.getItem(), sceneItem.getColumn(), sceneItem.getRow()).toString() );
        }
      }
      else{
        throw new ExtensionException("The contents of turtle " + callingAgent.id + "s 'current-scene' turtle variable is not an instance of 'jchrest.lib.Scene'.");
      }
        
    } catch (AgentException ex) {
      Logger.getLogger(GetCurrentScene.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
    }
    
    return sceneAsList.toLogoList();
  }
  
}

