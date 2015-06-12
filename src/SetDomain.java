import java.util.logging.Level;
import java.util.logging.Logger;
import jchrest.architecture.Chrest;
import jchrest.lib.ChessDomain;
import jchrest.lib.DomainSpecifics;
import jchrest.lib.GenericDomain;
import jchrest.lib.TileworldDomain;
import org.nlogo.api.AgentException;

import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultCommand;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 * Plugs into the {@link jchrest.architecture.Chrest#setDomain(jchrest.lib.DomainSpecifics)}
 * method.
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class SetDomain extends DefaultCommand{
  
  @Override
  public Syntax getSyntax() {
    return Syntax.commandSyntax(new int[]{ Syntax.WildcardType() });
  }

  @Override
  public void perform(Argument[] args, Context cntxt) throws ExtensionException, LogoException {
    try {
      Class<?> declaredDomain = Class.forName(args[0].getString());
      
      try {
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
          throw new ExtensionException("Declared domain not valid.");
        }

      } catch (AgentException ex) {
        Logger.getLogger(SetDomain.class.getName()).log(Level.SEVERE, "", ex);
      }
      
    } catch (ClassNotFoundException ex) {
      Logger.getLogger(SetDomain.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
