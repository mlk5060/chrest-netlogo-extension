
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import jchrest.architecture.Chrest;
import jchrest.architecture.Node;
import jchrest.lib.ListPattern;
import org.nlogo.api.AgentException;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.LogoListBuilder;
import org.nlogo.api.Syntax;


/**
 * Reports the action node patterns that the specified pattern is linked to 
 * along with the strength of their reinforcements as strings in a Netlogo list.
 * 
 * Three parameters must be passed when the Netlogo extension primitive that 
 * invokes this class is used in a Netlogo model:
 * 
 * Param #      Data Type       Description
 * -------      ---------       -----------
 * 1            String          The modality of the parent pattern.
 * 2            String          The pattern type of the parent pattern.
 * 3            String          The contents of the parent pattern.
 * 
 * @author Martyn Lloyd-Kelly <mlk5060@liverpool.ac.uk>
 */
public class GetActionLinks extends DefaultReporter {
  
  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(new int[]{Syntax.StringType(), Syntax.StringType(), Syntax.StringType()}, Syntax.ListType());
  }

  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    LogoListBuilder actionLinks = new LogoListBuilder();
    
    try {
      if(BaseExtensionVariablesAndMethods.agentHasChrestInstance(context)){
        Chrest chrestInstance = BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(context);
        ListPattern parentPattern = BaseExtensionVariablesAndMethods.createAndPopulateListPatternWithNetlogoPrimitivePattern(args[0].getString(), args[1].getString(), args[2].getString());
        Node parentNode = chrestInstance.recognise(parentPattern);
        HashMap<Node,Double> links = parentNode.getActionLinks();
        for(Map.Entry<Node, Double> link : links.entrySet()) {
          actionLinks.add(link.getKey().getContents().toString() + ", " + link.getValue().toString());
        }
      }
    } catch (AgentException ex) {
      Logger.getLogger(GetActionLinks.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    return actionLinks.toLogoList();
  }
  
}
