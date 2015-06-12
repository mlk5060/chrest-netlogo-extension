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
 * Creates a new {@link jchrest.architecture.Chrest} instance, sets its 
 * domain to the class indicated by the String passed to this class and assigns 
 * the {@link jchrest.architecture.Chrest} instance to the calling turtle's 
 * "chrest-instance" breed variable.  This breed variable must be owned by the 
 * calling turtle before the extension primitive that calls this class is used.
 * 
 * The breed variable's name <b>must</b> be set to "chrest-instance" to ensure 
 * that this class can assign the Chrest object correctly to the calling turtle
 * and so that it can be accessed correctly by other classes in this extension.
 * 
 * One parameter must be passed when the Netlogo extension primitive 
 * that invokes this class is used in a Netlogo model:
 * 
 * Param #      Data Type       Description
 * -------      ---------       -----------
 * 1            String          The name of the domain class to set as the 
 *                              calling turtle's CHREST model domain.  Note that 
 *                              the string must be a full path specification to
 *                              the domain class as though {@link 
 *                              java.lang.Class#getName()} were called on it.
 *                              To reliably retrieve such a name, pass the 
 *                              relevant item from the list returned by the 
 *                              {@link GetDeclaredDomains#report(org.nlogo.api.Argument[], 
 *                              org.nlogo.api.Context) method.    
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
      BaseExtensionVariablesAndMethods.getAgent(context).setBreedVariable(BaseExtensionVariablesAndMethods.CHREST_INSTANCE_CHREST_AGENT_BREED_VAR_NAME, new Chrest());
      new SetDomain().perform(args, context);
    } catch (AgentException ex) {
      Logger.getLogger(InstantiateChrestInTurtle.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
    }
  }
}