package Chrest;

import Shared.BaseExtensionVariablesAndMethods;
import jchrest.lib.ListPattern;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultCommand;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 * Associates two {@link jchrest.lib.ListPattern} instances together.
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liv.ac.uk>
 */
public class AssociateListPatterns extends DefaultCommand {

  @Override
  public Syntax getSyntax() {
    return Syntax.commandSyntax(
      new int[]{
        Syntax.WildcardType(), //Origin of association
        Syntax.WildcardType(), //Terminus of association
        Syntax.NumberType() //The current Netlogo model time (in milliseconds).
      }
    );
  }

  @Override
  public void perform(Argument args[], Context context) throws ExtensionException, LogoException {
    BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(context)
      .associateAndLearn(
        (ListPattern)args[0].get(), 
        (ListPattern)args[1].get(), 
        args[2].getIntValue()
      );
  }
}
