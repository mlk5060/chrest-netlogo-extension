
import java.util.Arrays;
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
          System.out.println("Constructing array of variables (variablesToPass) from the list passed to this primitive to pass to Node.reinforceActionLink()...");
          
          LogoList variablesList = args[5].getList();
          Iterator variablesPassed = variablesList.iterator();
          
          while(variablesPassed.hasNext()){
            String variable = variablesPassed.next().toString();
            System.out.println("Checking to see if " + variable + " matches the regex pattern '[0-9]+\\.[0-9]+'...");
            if(!variable.matches("[0-9]+\\.[0-9]+")){
              throw new ExtensionException("Element " + variable + " in the list passed to this primitive is not a 'Double' object." );
            }
          }
          
          System.out.println("All variables passed are of the Double data type.  Converting the list of variables to a Double array...");
          Double[] variablesToPass = variablesList.toArray(new Double[variablesList.size()]);
          System.out.println("The result of list to Double array conversion is: " + Arrays.toString(variablesToPass) + "...");
          System.out.println("Passing the Double array to Node.reinforceActionLink()...");
          parentNode.reinforceActionLink(childNode, variablesToPass);
        }
      }
    } catch (AgentException ex) {
      Logger.getLogger(ReinforceActionLink.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
}
