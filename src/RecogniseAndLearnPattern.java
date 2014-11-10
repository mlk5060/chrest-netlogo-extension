import java.util.logging.Level;
import java.util.logging.Logger;
import org.nlogo.api.AgentException;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 * Recognises and/or learns a pattern of a specified modality and pattern type 
 * using the calling turtle's CHREST instance.
 * 
 * Four parameters must be passed when the Netlogo extension primitive that 
 * invokes this class is used in a Netlogo model:
 * 
 * Param #      Data Type       Description
 * -------      ---------       -----------
 * 1            String          The modality of the pattern to be recognised 
 *                              and/or learned.
 * 2            String          The type of the pattern to be recognised and/or
 *                              learned.
 * 3            String          The pattern to be recognised and/or learned.
 * 4            Number          The current Netlogo time (in milliseconds).
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liv.ac.uk>
 */
public class RecogniseAndLearnPattern extends DefaultReporter {

  @Override
  public Syntax getSyntax() {
    return Syntax.reporterSyntax(new int[]{Syntax.StringType(), Syntax.StringType(), Syntax.StringType(), Syntax.NumberType()}, Syntax.StringType());
  }

  @Override
  public Object report(Argument args[], Context context) throws ExtensionException {
    
    String recognisedNode = "";
    
    try {
      if (BaseExtensionVariablesAndMethods.agentHasChrestInstance(context)) {
        recognisedNode = BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(context).recogniseAndLearn(BaseExtensionVariablesAndMethods.createAndPopulateListPatternWithNetlogoPrimitivePattern(args[0].getString(), args[1].getString(), args[2].getString()), args[3].getIntValue()).getImage().toString();
      }
    } catch (LogoException | AgentException ex) {
      Logger.getLogger(RecogniseAndLearnPattern.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    return recognisedNode;
  }
}
