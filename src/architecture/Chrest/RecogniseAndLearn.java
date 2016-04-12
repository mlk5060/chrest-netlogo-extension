package architecture.Chrest;

import Shared.BaseExtensionVariablesAndMethods;
import jchrest.lib.ListPattern;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultCommand;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 * @author Martyn Lloyd-Kelly <martynlk@liv.ac.uk>
 */
public class RecogniseAndLearn extends DefaultCommand {

  @Override
  public Syntax getSyntax() {
    return Syntax.commandSyntax(
      new int[]{
        Syntax.WildcardType(),
        Syntax.NumberType()
      }
    );
  }

  /**
   * Invokes {@link jchrest.architecture.Chrest#recogniseAndLearn(
   * jchrest.lib.ListPattern, java.lang.Integer)} in context of the calling 
   * turtle's {@link jchrest.architecture.Chrest} instance.
   * 
   * @param args See parameters for {@link 
   * jchrest.architecture.Chrest#recogniseAndLearn(jchrest.lib.ListPattern, 
   * java.lang.Integer)}.
   * @param context
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public void perform(Argument args[], Context context) throws ExtensionException, LogoException {
    BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(context).recogniseAndLearn((ListPattern)args[0].get(), args[1].getIntValue());
  }
}
