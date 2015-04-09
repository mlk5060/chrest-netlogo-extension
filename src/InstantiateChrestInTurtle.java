import java.util.logging.Level;
import java.util.logging.Logger;
import jchrest.architecture.Chrest;
import jchrest.lib.DomainSpecifics;
import org.nlogo.api.AgentException;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultCommand;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 * Creates a new Chrest object and assigns it to the calling turtle's 
 * "chrest-instance" breed variable.  This breed variable must be owned by the 
 * calling turtle before the extension primitive that calls this class is used.
 * 
 * The breed variable's name <b>must</b> be set to "chrest-instance" to ensure 
 * that this class can assign the Chrest object correctly to the calling turtle
 * and so that it can be accessed correctly in other classes.
 * 
 * One parameter must be passed when the Netlogo extension primitive 
 * that invokes this class is used in a Netlogo model:
 * 
 * Param #      Data Type       Description
 * -------      ---------       -----------
 * 1            String          The name of the domain to set as the calling 
 *                              turtle's CHREST model's domain.  Note that the
 *                              domain requested must be specified in CHREST's
 *                              jchrest.lib folder as a class called 
 *                              "stringpassedDomain.java" and the class must
 *                              implement the jchrest.lib.DomainSpecifics
 *                              interface.
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liv.ac.uk>
 */
public class InstantiateChrestInTurtle extends DefaultCommand {
  
  @Override
  public Syntax getSyntax(){
    return Syntax.commandSyntax(new int[]{Syntax.StringType()});
  }

  @Override
  public void perform(Argument args[], Context context) throws ExtensionException, LogoException {
    try {
      //Instantiate a CHREST model for the calling turtle.
      BaseExtensionVariablesAndMethods.getAgent(context).setBreedVariable(BaseExtensionVariablesAndMethods.CHREST_INSTANCE_CHREST_AGENT_BREED_VAR_NAME, new Chrest());
      
      //Capitalise the domain name specified as per jchrest.lib Domain class 
      //name conventions and set this as the CHREST model's domain that was just
      //instantiated.
      String domainName = args[0].getString().substring(0, 1).toUpperCase() + args[0].getString().substring(1).toLowerCase();
      BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(context).setDomain((DomainSpecifics)Class.forName("jchrest.lib." + domainName + "Domain").newInstance());
    } catch (AgentException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
      Logger.getLogger(InstantiateChrestInTurtle.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
    }
  }
}