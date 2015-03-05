import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import jchrest.architecture.Node;
import jchrest.lib.Modality;
import org.nlogo.api.AgentException;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.LogoListBuilder;
import org.nlogo.api.Syntax;

/**
 * Recognises a pattern, <i>x</i>, and returns patterns, <i>n</i>, of a 
 * specified modality that <i>x</i> is associated with as a Netlogo list.
 * 
 * Three parameters must be passed when the Netlogo extension primitive that 
 * invokes this class is used in a Netlogo model:
 * 
 * Param #      Data Type       Description
 * -------      ---------       -----------
 * 1            String          The modality of pattern to be recognised.
 * 2            String          The type of the pattern to be recognised.
 * 3            String          The pattern to recognise.
 * 4            String          The modality of associated patterns that should 
 *                              be returned.
 * 
 * The Netlogo extension primitive that invokes this class returns the following 
 * when it is used in a Netlogo model:
 * 
 * Data Type Returned     Description
 * ------------------     -----------
 * Netlogo list           All patterns of a specified modality associated with
 *                        the pattern that is attempted to be recognised.
 * 
 * @author Martyn Lloyd-Kelly <mlk5060@liverpool.ac.uk>
 */
public class RecognisePatternAndReturnPatternsOfSpecifiedModality extends DefaultReporter {

  @Override
  public Syntax getSyntax() {
    return Syntax.reporterSyntax(new int[]{Syntax.StringType(), Syntax.StringType(), Syntax.StringType(), Syntax.StringType()}, Syntax.ListType());
  }

  @Override
  public Object report(Argument args[], Context context) throws ExtensionException, LogoException {

    LogoListBuilder list = new LogoListBuilder();

    try {
      if (BaseExtensionVariablesAndMethods.agentHasChrestInstance(context)) {
        
        Node retrievedNode = BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(context).recognise(BaseExtensionVariablesAndMethods.createAndPopulateListPatternWithNetlogoPrimitivePattern(args[0].getString(), args[1].getString(), args[2].getString()));
        String modalitySpecified = args[3].getString();
        if (BaseExtensionVariablesAndMethods.validModality(modalitySpecified)) {
          if (modalitySpecified.equalsIgnoreCase(Modality.ACTION.toString())) {
            HashMap<Node,Double> links = retrievedNode.getActionLinks();
            for(Map.Entry<Node, Double> link : links.entrySet()) {
              LogoListBuilder actionWeightList = new LogoListBuilder();
              String actionPattern = link.getKey().getContents().toString();
              actionWeightList.add(actionPattern + "," + link.getValue());
              list.add(actionWeightList.toLogoList());
            }
          }
        }
      }
    } catch (AgentException ex) {
      Logger.getLogger(RecognisePatternAndReturnPatternsOfSpecifiedModality.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
    }

    return list.toLogoList();
  }
}
