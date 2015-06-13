import java.util.logging.Level;
import java.util.logging.Logger;
import jchrest.architecture.Chrest;
import jchrest.lib.ChessDomain;
import jchrest.lib.GenericDomain;
import jchrest.lib.TileworldDomain;
import org.nlogo.api.AgentException;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultCommand;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.LogoList;
import org.nlogo.api.Syntax;

/**
 * Plugs into the {@link jchrest.architecture.Chrest#setDomain(jchrest.lib.DomainSpecifics)}
 * method.
 * 
 * One parameter must be passed when the Netlogo extension primitive that 
 * invokes this class is used in a Netlogo model:
 * 
 * Param #      Data Type       Description
 * -------      ---------       -----------
 * 1            String          The fully qualified class name for the domain
 *                              class that the calling turtle's CHREST model 
 *                              domain should be set to.
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class SetDomain extends DefaultCommand{
  
  @Override
  public Syntax getSyntax() {
    return Syntax.commandSyntax(new int[]{ Syntax.StringType() });
  }

  @Override
  public void perform(Argument[] args, Context cntxt) throws ExtensionException, LogoException {
    String domainSpecified = args[0].getString();
    
    try {
      Class<?> declaredDomain = Class.forName(domainSpecified);
      Chrest turtlesChrestInstance = BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(cntxt);

      if( declaredDomain.equals(jchrest.lib.ChessDomain.class) ){
        turtlesChrestInstance.setDomain(new ChessDomain(turtlesChrestInstance));
      }
      else if( declaredDomain.equals(jchrest.lib.GenericDomain.class) ){
        turtlesChrestInstance.setDomain(new GenericDomain(turtlesChrestInstance));
      }
      else if( declaredDomain.equals(jchrest.lib.TileworldDomain.class) ){
        turtlesChrestInstance.setDomain(new TileworldDomain(turtlesChrestInstance));
      }
      else {
        throw new ClassNotFoundException();
      } 
    } catch (ClassNotFoundException ex) {
      Logger.getLogger(SetDomain.class.getName()).log(Level.SEVERE, "", ex);
      
      String supportedDomainsString = "";
      LogoList supportedDomains = (LogoList)new GetDeclaredDomains().report(args, cntxt);
      for(Object supportedDomain : supportedDomains){
        supportedDomainsString += "'" + supportedDomain.toString() + "', ";
      }
      supportedDomainsString = supportedDomainsString.replaceFirst(", $", "");
      
      throw new ExtensionException("Domain specified (" + domainSpecified + ") by turtle "
        + BaseExtensionVariablesAndMethods.getAgent(cntxt).id + " is not supported by this "
        + "turtle's CHREST model. Domains supported (with fully qualified paths) by this turtle are: "
        + supportedDomainsString + ".");
    } catch (AgentException ex) {
      Logger.getLogger(SetDomain.class.getName()).log(Level.SEVERE, "", ex);
    }
  }
}
