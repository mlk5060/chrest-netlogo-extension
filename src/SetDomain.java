import java.util.logging.Level;
import java.util.logging.Logger;
import jchrest.lib.DomainSpecifics;
import org.nlogo.api.AgentException;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultCommand;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 * Sets the current domain of the CHREST model associated with the calling
 * turtle.  One parameter must be passed when the Netlogo extension primitive 
 * that invokes this class is used in a Netlogo model:
 * 
 * Param #      Data Type       Description
 * -------      ---------       -----------
 * 1            String          The name of the domain to set as the calling 
 *                              turtle's CHREST model's domain.  Note that the
 *                              domain requested must be specified in CHREST's
 *                              jchrest.lib folder as a class called 
 *                              "stringpassedDomain.java" and the class must
 *                              implement the "jchrest.lib.DomainSpecifics" 
 *                              interface.
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class SetDomain extends DefaultCommand{

  @Override
  public Syntax getSyntax(){
    return Syntax.commandSyntax(new int[] {Syntax.StringType()});
  }
  
  @Override
  public void perform(Argument[] args, Context cntxt) throws ExtensionException, LogoException {
    try {
      String domainName = args[0].getString().substring(0, 1).toUpperCase() + args[0].getString().substring(1).toLowerCase();
      BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(cntxt).setDomain((DomainSpecifics)Class.forName("jchrest.lib." + domainName + "Domain").newInstance());
    } catch (AgentException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
      Logger.getLogger(SetDomain.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
    }
  }
  
}
