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
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class GetCurrentScene extends DefaultReporter{
  
  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(new int[]{}, Syntax.ListType());
  }

  @Override
  public Object report(Argument[] argmnts, Context cntxt) throws ExtensionException, LogoException {
    LogoListBuilder sceneAsList = new LogoListBuilder();
    Agent callingAgent = BaseExtensionVariablesAndMethods.getAgent(cntxt);
    
    try {
      
      //First, attempt to retrieve the contents of the calling agent's 
      //"current-scene" turtle-variable.  If this is an instance of "Scene" then
      //continue by casting the retrieved contents to "Scene".
      Object currentScene = callingAgent.getBreedVariable(BaseExtensionVariablesAndMethods.CURRENT_SCENE_BREED_VAR_NAME);
      if(currentScene instanceof Scene){
        Scene currentSceneAsScene = (Scene)currentScene;
        
        //TODO: remove this conversion to domain-specific coordinates since this
        //should be done on a per-model basis.
        
        //Now, since it may be the case that the calling agent's sight radius 
        //has changed since the current scene was created, conversion from 
        //non-domain specific scene coordinates to scene coordinates needs to
        //occur. Thus, the returned scene contents make sense and can be used
        //directly by the calling agent rather than the Netlogo modeller having
        //to convert the non-domain specific scene coordinates into 
        //domain-specific coordinates.  To do this, get the middle value for the
        //current scene's height and width and round down to the nearest integer.
        //The reason for rounding down is because the sight-radius declares how
        //many squares north/south/east/west from the agent's *current* location
        //can be seen.  For example, if an agent has a sight radius of 2 when it
        //generated the current scene, its current scene will be 5 wide/tall 
        //(2 squares in each direction including the agent's current row and 
        //col).  Therefore, diving 5 by 2 gives 2.5 so rounding down gives the
        //correct sight radius of 2.
        //
        //Converters are doubles since scene dimensions will be
        //int's and are likely odd numbers so the int needs to be converted to
        //a double to produce a decimal value that can then be rounded down.  
        //Consequently, the result of the rounding will be a double too so the
        //result must also be a double.
        double heightConverter = Math.floor( (double)currentSceneAsScene.getHeight()/2 );
        double widthConverter = Math.floor( (double)currentSceneAsScene.getWidth()/2 );
        
        //Now, get the contents of the scene and iterate through them, 
        //converting non-domain-specific coordinates into domain-specific 
        //coordinates.
        ListPattern currentSceneListPattern = currentSceneAsScene.getScene();
        Iterator<PrimitivePattern> currentSceneContents = currentSceneListPattern.iterator();
        while(currentSceneContents.hasNext()){
          
          //Get the scene square, convert non-domain specific coordinates to 
          //domain-specific coordinates, create a new ItemSquarePattern 
          //instance using domain-specific info, get its string representation
          //and add it to the scene Netlogo list.  Getting a ListPattern string
          //keeps formatting of CHREST info so aids understanding for modeller.
          ItemSquarePattern sceneItem = (ItemSquarePattern)currentSceneContents.next();
          int domainSpecificXCor = (int) (sceneItem.getColumn() - widthConverter);
          int domainSpecificYCor = (int) (sceneItem.getRow() - heightConverter);
          sceneAsList.add( new ItemSquarePattern(sceneItem.getItem(), domainSpecificXCor, domainSpecificYCor).toString() );
        }
      }
      else{
        throw new ExtensionException("The contents of turtle " + callingAgent.id + "s 'current-scene' turtle variable is not an instance of 'ListPattern'.");
      }
        
    } catch (AgentException ex) {
      Logger.getLogger(GetCurrentScene.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
    }
    
    return sceneAsList.toLogoList();
  }
  
}

