import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import jchrest.lib.ItemSquarePattern;
import jchrest.lib.ListPattern;
import jchrest.lib.PrimitivePattern;
import jchrest.lib.Scene;
import org.nlogo.api.AgentException;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.LogoListBuilder;
import org.nlogo.api.Syntax;

/**
 * Returns the current contents of the calling turtle's mind's eye at the domain
 * time specified.
 * 
 * Two parameters must be passed when the Netlogo extension primitive that 
 * invokes this class is used in a Netlogo model:
 * 
 * Param #      Data Type       Description
 * -------      ---------       -----------
 * 1            Number          The current domain time (in milliseconds).
 * 2            Boolean         Set to true to force the function to return 
 *                              non-self-relative visual-spatial field 
 *                              coordinates for objects in the 
 *                              {@link jchrest.lib.Scene} instance generated 
 *                              even when the calling turtle is identified in 
 *                              the {@link jchrest.lib.Scene} instance.
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class GetMindsEyeScene extends DefaultReporter {
  
  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(new int[]{Syntax.NumberType(), Syntax.BooleanType()}, Syntax.ListType());
  }
  
  @Override
  public Object report(Argument args[], Context context) throws ExtensionException, LogoException{
    LogoListBuilder mindsEyeContentList = new LogoListBuilder();
    
    try {
      Scene visualSpatialFieldAsScene = BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(context).getMindsEyes().lastEntry().getValue().getVisualSpatialFieldAsScene(args[0].getIntValue());
      ListPattern entireScene = visualSpatialFieldAsScene.getEntireScene(args[1].getBooleanValue());
      Iterator<PrimitivePattern> scene = entireScene.iterator();
      while(scene.hasNext()){
        PrimitivePattern scenePattern = scene.next();
        if(scenePattern instanceof ItemSquarePattern){
          mindsEyeContentList.add( ((ItemSquarePattern)scenePattern).toString() );
        }
      }
    } catch (AgentException ex) {
      Logger.getLogger(GetMindsEyeScene.class.getName()).log(Level.SEVERE, "", ex);
    }
    
    return mindsEyeContentList.toLogoList();
  }
}
