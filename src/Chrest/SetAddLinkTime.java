package Chrest;

import Shared.BaseExtensionVariablesAndMethods;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultCommand;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 * Invokes {@link jchrest.architecture.Chrest#setAddLinkTime(int)} for the 
 * calling turtle.
 *
 * @author Martyn Lloyd-Kelly <martynlk@liv.ac.uk>
 */
public class SetAddLinkTime extends DefaultCommand {
  
  @Override
  public Syntax getSyntax(){
    return Syntax.commandSyntax(
      new int[] {
        Syntax.NumberType() //The new add link time value (in milliseconds).
      }
    );
  }
  
  @Override
  public void perform(Argument args[], Context context) throws ExtensionException, LogoException{
    BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(context).setAddLinkTime(args[0].getIntValue());
  }
}
