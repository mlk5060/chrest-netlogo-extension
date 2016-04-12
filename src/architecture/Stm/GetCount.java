package architecture.Stm;

import Shared.BaseExtensionVariablesAndMethods;
import jchrest.architecture.Stm;
import jchrest.lib.Modality;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 * @author Martyn Lloyd-Kelly <martynlk@liv.ac.uk>
 */
public class GetCount extends DefaultReporter {

  @Override
  public Syntax getSyntax() {
    return Syntax.reporterSyntax(
      new int[]{
        Syntax.WildcardType(),
        Syntax.NumberType()
      },
      Syntax.NumberType());
  }

  /**
   * 
   * @param args First parameter should be the {@link jchrest.lib.Modality} of
   * {@link jchrest.architecture.Stm} that {@link 
   * jchrest.architecture.Stm#getCount(int)} is to be invoked in context of.
   * For other parameters, see parameters for {@link 
   * jchrest.architecture.Stm#getCount(int)}.
   * 
   * @param context
   * 
   * @return The result of invoking {@link jchrest.architecture.Stm#getCount(
   * int)} for the {@link jchrest.architecture.Stm} with {@link 
   * jchrest.lib.Modality} specified in context of the calling turtle's {@link 
   * jchrest.architecture.Chrest} instance.
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Double report(Argument args[], Context context) throws ExtensionException, LogoException {
    return Double.valueOf( BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(context).getStm((Modality)args[0].get()).getCount(args[1].getIntValue()) );
  }
}
