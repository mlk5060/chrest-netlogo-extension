import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import jchrest.architecture.Node;
import jchrest.architecture.Stm;
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
 * Returns the contents of a STM modality.  The STM modality to return can be
 * specified.
 * 
 * One parameter must be passed when the Netlogo extension primitive that 
 * invokes this class is used in a Netlogo model:
 * 
 * Param #      Data Type       Description
 * -------      ---------       -----------
 * 1            String          The modality of STM to be returned.
 * 
 * The Netlogo extension primitive that invokes this class returns the following 
 * when it is used in a Netlogo model:
 * 
 * Data Type Returned     Description
 * ------------------     -----------
 * Netlogo list           The contents of the specific STM modality.  Note that
 *                        the order of items in this list is reversed before it
 *                        is returned so that the order of items in it matches
 *                        the order of items in the STM modality i.e. the most 
 *                        recent pattern added to the STM modality is the first 
 *                        item in the list returned by this class.
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liv.ac.uk>
 */
public class GetStmContentsByModality extends DefaultReporter{
  @Override
  public Syntax getSyntax() {
    return Syntax.reporterSyntax(new int[]{Syntax.StringType()}, Syntax.ListType());
  }

  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    LogoListBuilder stmListRepresentation = new LogoListBuilder();
    String stmModalitySpecified = args[0].getString();
    Stm stm = null;
    
    try {  
      if(BaseExtensionVariablesAndMethods.agentHasChrestInstance(context) && BaseExtensionVariablesAndMethods.validModality(stmModalitySpecified)){
        if(stmModalitySpecified.equalsIgnoreCase(Modality.ACTION.toString())){
          stm = BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(context).getActionStm();
        }
        
        if(stmModalitySpecified.equalsIgnoreCase(Modality.VERBAL.toString())){
          stm = BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(context).getVerbalStm();
        }
        
        if(stmModalitySpecified.equalsIgnoreCase(Modality.VISUAL.toString())){
          stm = BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(context).getVisualStm();
        }
        
        if(stm != null){
          Iterator<Node> stmIterator = stm.iterator();
          while(stmIterator.hasNext()){
            stmListRepresentation.add(stmIterator.next().getImage().toString());
          }
        }
      }
    } catch (AgentException ex) {
      Logger.getLogger(GetStmContentsByModality.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
    }
    
    return stmListRepresentation.toLogoList().reverse();
  }
}
