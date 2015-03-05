import java.util.logging.Level;
import java.util.logging.Logger;
import jchrest.architecture.Chrest;
import org.nlogo.api.AgentException;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultCommand;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;

/**
 * Creates a new Chrest object and assigns it to the calling turtle's 
 * "chrest-instance" breed variable.  This breed variable must be owned by the 
 * calling turtle before the extension primitive that calls this class is used.
 * 
 * The breed variable's name <b>must</b> be set to "chrest-instance" to ensure 
 * that this class can assign the Chrest object correctly to the calling turtle
 * and so that it can be accessed correctly in other classes.
 * 
 * No parameters can be passed when the Netlogo extension primitive that 
 * invokes this class is used in a Netlogo model.
 * 
 * @author Martyn Lloyd-Kelly <mlk5060@liverpool.ac.uk>
 */
public class InstantiateChrestInTurtle extends DefaultCommand {

  @Override
  public void perform(Argument args[], Context context) throws ExtensionException, LogoException {
    try {
      BaseExtensionVariablesAndMethods.getAgent(context).setBreedVariable(BaseExtensionVariablesAndMethods.CHREST_INSTANCE_CHREST_AGENT_BREED_VAR_NAME, new Chrest());
    } catch (AgentException ex) {
      Logger.getLogger(InstantiateChrestInTurtle.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
    }
  }
}