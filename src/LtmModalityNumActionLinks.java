import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import jchrest.architecture.Link;
import jchrest.architecture.Node;
import jchrest.lib.Modality;
import org.nlogo.api.AgentException;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 * Returns the cumulative number of action links in the LTM modality specified
 * for the calling turtle.
 * 
 * One parameter must be passed when the Netlogo extension primitive that 
 * invokes this class is used in a Netlogo model:
 * 
 * Param #      Data Type       Description
 * -------      ---------       -----------
 * 1            String          The LTM modality that is to have its cumulative 
 *                              number of action links calculated.
 * 
 * The Netlogo extension primitive that invokes this class returns the following 
 * when it is used in a Netlogo model:
 * 
 * Data Type Returned     Description
 * ------------------     -----------
 * Double                 The cumulative number of action links in the LTM 
 *                        modality specified for the calling turtle.
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liv.ac.uk>
 */
public class LtmModalityNumActionLinks extends DefaultReporter{
  
  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax( new int[] {Syntax.StringType()}, Syntax.NumberType() );
  }
  
  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException{
    Double numActionLinks = 0.00;
    Node ltm = null;
    
    try {
      if( BaseExtensionVariablesAndMethods.agentHasChrestInstance(context) ){
        String modality = args[0].getString();
        if (BaseExtensionVariablesAndMethods.validModality(modality)) {
        
          if(modality.equalsIgnoreCase(Modality.ACTION.toString())){
              ltm = BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(context).getActionLtm();
          }
          else if(modality.equalsIgnoreCase(Modality.VERBAL.toString())){
              ltm = BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(context).getVerbalLtm();
          }
          else if(modality.equalsIgnoreCase(Modality.VISUAL.toString())){
              ltm = BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(context).getVisualLtm();
          }
        }
        
        if(ltm != null){
          Iterator<Link> ltmNodes = ltm.getChildren().iterator();
          while(ltmNodes.hasNext()){
            numActionLinks += ltmNodes.next().getChildNode().getActionLinks().size();
          }
        }
      }
    }
    catch (AgentException ex) {
      Logger.getLogger(LtmModalityNumActionLinks.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    return numActionLinks;
  }
}
