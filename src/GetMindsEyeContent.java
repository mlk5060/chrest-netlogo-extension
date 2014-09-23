
import java.util.logging.Level;
import java.util.logging.Logger;
import org.nlogo.api.AgentException;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.LogoListBuilder;
import org.nlogo.api.Syntax;

/**
 * Returns the calling turtle's mind's eye content with domain-specific 
 * coordinates as a Netlogo list.
 * 
 * @author Martyn Lloyd-Kelly <mlk5060@liverpool.ac.uk>
 */
public class GetMindsEyeContent extends DefaultReporter {
  
  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(new int[]{}, Syntax.ListType());
  }
  
  @Override
  public Object report(Argument args[], Context context){
    LogoListBuilder mindsEyeContentList = new LogoListBuilder();
    
    try {
      if(BaseExtensionVariablesAndMethods.agentHasChrestInstance(context)){
        mindsEyeContentList.addAll(BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(context).getMindsEyeContentSpecificToDomain());
      }
    } catch (AgentException ex) {
      Logger.getLogger(GetMindsEyeContent.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    return mindsEyeContentList.toLogoList();
  }
}
