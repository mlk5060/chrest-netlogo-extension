import java.util.logging.Level;
import java.util.logging.Logger;
import org.nlogo.api.AgentException;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultCommand;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 * Enables two patterns of any modality to be associated with each other using 
 * modality-specific association functions.
 * 
 * Seven parameters must be passed when the Netlogo extension primitive that 
 * invokes this class is used in a Netlogo model:
 * 
 * Param #      Data Type       Description
 * -------      ---------       -----------
 * 1            String          The modality of the first pattern. See @see 
 *                              BaseExtensionVariablesAndMethods#setModalityOfListPattern(jchrest.lib.ListPattern, java.lang.String).
 * 2            String          Pattern type of the first pattern. See 
 * 3            String          First pattern.
 * 4            String          Modality of the second pattern.  See @see 
 *                              BaseExtensionVariablesAndMethods#setModalityOfListPattern(jchrest.lib.ListPattern, java.lang.String).
 * 5            String          Pattern type of the second pattern.
 * 6            String          Second pattern.
 * 7            Number          The current Netlogo time (in milliseconds).
 * 
 * @author Martyn Lloyd-Kelly <mlk5060@liverpool.ac.uk>
 */
public class AssociatePatterns extends DefaultCommand {

  @Override
  public Syntax getSyntax() {
    return Syntax.commandSyntax(new int[]{Syntax.StringType(), Syntax.StringType(), Syntax.StringType(), Syntax.StringType(), Syntax.StringType(), Syntax.StringType(), Syntax.NumberType()});
  }

  @Override
  public void perform(Argument args[], Context context) throws ExtensionException, LogoException {
    try {
      if (BaseExtensionVariablesAndMethods.agentHasChrestInstance(context)) {
        
        //Construct Listpattern instances from the patterns passed then use the
        //calling turtle's CHREST instance to associate and learn them.
        BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(context).associateAndLearn(BaseExtensionVariablesAndMethods.createAndPopulateListPatternWithNetlogoPrimitivePattern(args[0].getString(), args[1].getString(), args[2].getString()), BaseExtensionVariablesAndMethods.createAndPopulateListPatternWithNetlogoPrimitivePattern(args[3].getString(), args[4].getString(), args[5].getString()), args[6].getIntValue());
      }
    } catch (AgentException ex) {
      Logger.getLogger(AssociatePatterns.class.getName()).log(Level.SEVERE, ex);
    }
  }
}
