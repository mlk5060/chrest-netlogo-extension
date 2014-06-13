
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jchrest.architecture.Chrest;
import jchrest.architecture.Node;
import jchrest.architecture.Stm;
import jchrest.lib.ListPattern;
import jchrest.lib.Modality;
import org.nlogo.api.AgentException;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultCommand;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 * Enables a calling turtle to reinforce a link from the pattern specified to 
 * the action pattern specified by an amount.
 * 
 * 
 * Three parameters must be passed when the Netlogo extension primitive that 
 * invokes this class is used in a Netlogo model:
 * 
 * Param #      Data Type       Description
 * -------      ---------       -----------
 * 1            String          The modality of the association's parent pattern.
 * 2            String          The type of the association's parent pattern.
 * 3            String          The contents of the parent pattern.
 * 4            String          The type of the association's child pattern.
 * 5            String          The contents of the child pattern.
 * 6            Integer         The amount to reinforce the link by.
 * 
 * @author Martyn Lloyd-Kelly <mlk5060@liverpool.ac.uk>
 */
public class ReinforceActionLink extends DefaultCommand {
  
  @Override
  public Syntax getSyntax() {
    return Syntax.commandSyntax(new int[]{Syntax.StringType(), Syntax.StringType(), Syntax.StringType(), Syntax.StringType(), Syntax.StringType(), Syntax.NumberType()});
  }

  @Override
  public void perform(Argument[] args, Context context) throws ExtensionException, LogoException {
    try {
      org.nlogo.agent.World world = (org.nlogo.agent.World) context.getAgent().world();
      System.out.println("");
      if(world.getObserverVariableByName("TRAINING?").toString().equalsIgnoreCase("true")){
        System.out.println("=== TURTLE " + context.getAgent().id() + " @ SIM. TIME: " + world.getObserverVariableByName("CURRENT-TRAINING-TIME").toString() + " ===");
      }
      else{
        System.out.println("=== TURTLE " + context.getAgent().id() + " @ SIM. TIME: " + world.getObserverVariableByName("CURRENT-GAME-TIME").toString() + " ===");
      }
      
      if(BaseExtensionVariablesAndMethods.agentHasChrestInstance(context) && BaseExtensionVariablesAndMethods.validModality(args[0].getString())){
        Chrest chrestInstance = BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(context);
        ListPattern parentPatternOfAssociation = BaseExtensionVariablesAndMethods.createAndPopulateListPatternWithNetlogoPrimitivePattern(args[0].getString(), args[1].getString(), args[2].getString());
        ListPattern childPatternOfAssociation = BaseExtensionVariablesAndMethods.createAndPopulateListPatternWithNetlogoPrimitivePattern(Modality.ACTION.toString(), args[3].getString(), args[4].getString());
        System.out.println("The 'parentPatternOfAssociation' variable is set to: " + parentPatternOfAssociation.toString());
        System.out.println("The 'childPatternOfAssociation' variable is set to: " + childPatternOfAssociation.toString());
        
        System.out.println("Attempting to recognise " + parentPatternOfAssociation + "...");
        Node parentNode = chrestInstance.recognise(parentPatternOfAssociation);
        System.out.println("The result of attempting to recognise " + parentPatternOfAssociation + " is: " + parentNode.getContents().toString());
        
        System.out.println("Attempting to recognise " + childPatternOfAssociation + "...");
        Node childNode = chrestInstance.recognise(childPatternOfAssociation);
        System.out.println("The result of attempting to recognise " + childPatternOfAssociation + " is: " + childNode.getContents().toString());
        
        System.out.println("Checking to see if: " + parentPatternOfAssociation + " equals " + parentNode.getContents().toString() + " and if " + childPatternOfAssociation + " equals " + childNode.getContents().toString() + "...");
        if( parentNode.getContents().equals(parentPatternOfAssociation) && childNode.getContents().equals(childPatternOfAssociation) ){
          
          System.out.println(parentPatternOfAssociation + " does equal " + parentNode.getContents().toString() + " and " + childPatternOfAssociation + " equals " + childNode.getContents().toString());
          System.out.println("Attempting to reinforce link...");
          parentNode.reinforceActionLink(childNode, args[5].getIntValue());
        }
      }
    } catch (AgentException ex) {
      Logger.getLogger(ReinforceActionLink.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
}
