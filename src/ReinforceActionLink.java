import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import jchrest.architecture.Chrest;
import jchrest.architecture.Node;
import jchrest.lib.ListPattern;
import jchrest.lib.Modality;
import org.nlogo.api.AgentException;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultCommand;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.LogoList;
import org.nlogo.api.Syntax;

/**
 * Enables a calling turtle to reinforce a link from the pattern specified to 
 * the action pattern specified by an amount.
 *
 * Six parameters must be passed when the Netlogo extension primitive that 
 * invokes this class is used in a Netlogo model:
 * 
 * Param #      Data Type       Description
 * -------      ---------       -----------
 * 1            String          The modality of the association's parent pattern.
 * 2            String          The type of the association's parent pattern.
 * 3            String          The contents of the parent pattern.
 * 4            String          The type of the association's child pattern.
 * 5            String          The contents of the child pattern.
 * 6            List            The variables required by the CHREST turtle's 
 *                              reinforcement learning theory to calculate how
 *                              much the link should be reinforced by.
 * 
 * @author Martyn Lloyd-Kelly <mlk5060@liverpool.ac.uk>
 */
public class ReinforceActionLink extends DefaultCommand {
  
  @Override
  public Syntax getSyntax() {
    return Syntax.commandSyntax(new int[]{Syntax.StringType(), Syntax.StringType(), Syntax.StringType(), Syntax.StringType(), Syntax.StringType(), Syntax.ListType()});
  }

  @Override
  public void perform(Argument[] args, Context context) throws ExtensionException, LogoException {
    try {
      if(BaseExtensionVariablesAndMethods.agentHasChrestInstance(context) && BaseExtensionVariablesAndMethods.validModality(args[0].getString())){
        Chrest chrestInstance = BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(context);
        ListPattern parentPatternOfAssociation = BaseExtensionVariablesAndMethods.createAndPopulateListPatternWithNetlogoPrimitivePattern(args[0].getString(), args[1].getString(), args[2].getString());
        ListPattern childPatternOfAssociation = BaseExtensionVariablesAndMethods.createAndPopulateListPatternWithNetlogoPrimitivePattern(Modality.ACTION.toString(), args[3].getString(), args[4].getString());
        
        Node parentNode = chrestInstance.recognise(parentPatternOfAssociation);
        Node childNode = chrestInstance.recognise(childPatternOfAssociation);
        
        if( parentNode.getContents().equals(parentPatternOfAssociation) && childNode.getContents().equals(childPatternOfAssociation) ){
          LogoList variablesList = args[5].getList();
          Iterator variablesPassed = variablesList.iterator();
          
          while(variablesPassed.hasNext()){
            String variable = variablesPassed.next().toString();
            if(!variable.matches("[0-9]+\\.[0-9]+")){
              throw new ExtensionException("Element " + variable + " in the list passed to this primitive is not a 'Double' object." );
            }
          }
          
          Double[] variablesToPass = variablesList.toArray(new Double[variablesList.size()]);
          parentNode.reinforceActionLink(childNode, variablesToPass);
        }
      }
    } catch (AgentException ex) {
      Logger.getLogger(ReinforceActionLink.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
}
