import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import jchrest.lib.ItemSquarePattern;
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
 * If the mind's eye associated with the calling turtle exists at the domain
 * time specified and the attention of the CHREST model is free at the domain
 * time specified, the entire contents of the calling turtle's mind's eye 
 * is returned as a Netlogo string list.  Otherwise, the string "null" is 
 * returned.
 * 
 * Note that coordinates are not domain-specific so 
 * 
 * One parameter must be passed when the Netlogo extension primitive that 
 * invokes this class is used in a Netlogo model:
 * 
 * Param #      Data Type       Description
 * -------      ---------       -----------
 * 1            Number          The current domain time (in milliseconds).
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class GetMindsEyeScene extends DefaultReporter {
  
  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(new int[]{Syntax.NumberType()}, Syntax.ListType());
  }
  
  @Override
  public Object report(Argument args[], Context context) throws ExtensionException, LogoException{
    LogoListBuilder mindsEyeContentList = new LogoListBuilder();
    
    try {
      if(BaseExtensionVariablesAndMethods.agentHasChrestInstance(context)){
        Scene mindsEyeScene = BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(context).getMindsEyeScene(args[0].getIntValue());
        
        if(mindsEyeScene != null){
          Iterator<PrimitivePattern> mindsEyeIterator = mindsEyeScene.getScene().iterator();
          while(mindsEyeIterator.hasNext()){
            ItemSquarePattern mindsEyeItem = (ItemSquarePattern)mindsEyeIterator.next();
            mindsEyeContentList.add( new ItemSquarePattern(mindsEyeItem.getItem(), mindsEyeItem.getColumn(), mindsEyeItem.getRow()).toString() );
          }
        }
        else{
          mindsEyeContentList.add("null");
        }
      }
    } catch (AgentException ex) {
      Logger.getLogger(GetMindsEyeScene.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
    }
    
    return mindsEyeContentList.toLogoList();
  }
}
