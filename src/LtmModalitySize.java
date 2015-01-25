import java.util.logging.Level;
import java.util.logging.Logger;
import jchrest.lib.Modality;
import org.nlogo.api.AgentException;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 * Returns the number of nodes contained in the LTM modality specified for the 
 * calling turtle.
 * 
 * One parameter must be passed when the Netlogo extension primitive that 
 * invokes this class is used in a Netlogo model:
 * 
 * Param #      Data Type       Description
 * -------      ---------       -----------
 * 1            String          The name of the LTM modality that is to have its
 *                              size calculated.
 * 
 * The Netlogo extension primitive that invokes this class returns the following 
 * when it is used in a Netlogo model:
 * 
 * Data Type Returned     Description
 * ------------------     -----------
 * Double                 The number of nodes in the LTM modality specified for
 *                        the calling turtle.
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liv.ac.uk>
 */
public class LtmModalitySize extends DefaultReporter {

  @Override
  public Syntax getSyntax() {
    return Syntax.reporterSyntax(new int[]{Syntax.StringType()}, Syntax.NumberType());
  }

  @Override
  public Double report(Argument args[], Context context) throws ExtensionException, LogoException {
    Double ltmSize = 0.00;

    try {
      String modality = args[0].getString();
      if (BaseExtensionVariablesAndMethods.validModality(modality)) {
        
        if (modality.equalsIgnoreCase(Modality.ACTION.toString())) {
          ltmSize = Double.valueOf(BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(context).ltmActionSize());
        }
        if (modality.equalsIgnoreCase(Modality.VERBAL.toString())) {
          ltmSize = Double.valueOf(BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(context).ltmVerbalSize());
        }
        if (modality.equalsIgnoreCase(Modality.VISUAL.toString())) {
          ltmSize = Double.valueOf(BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(context).ltmVisualSize());
        }
        
      }
    } catch (AgentException ex) {
      Logger.getLogger(LtmModalitySize.class.getName()).log(Level.SEVERE,"", ex);
    }

    return ltmSize;
  }
}
