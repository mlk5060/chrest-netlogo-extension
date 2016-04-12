package architecture.Chrest;

import Shared.BaseExtensionVariablesAndMethods;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 * @author Martyn Lloyd-Kelly <martynlk@liv.ac.uk>
 */
public class GetLtmSize extends DefaultReporter {

  @Override
  public Syntax getSyntax() {
    return Syntax.reporterSyntax(
      new int[]{
        Syntax.NumberType()
      }, 
      Syntax.NumberType()
    );
  }

  /**
   * 
   * @param args See parameters for {@link 
   * jchrest.architecture.Chrest#getLtmSize(int)}.
   * @param context
   * 
   * @return The result of invoking {@link 
   * jchrest.architecture.Chrest#getLtmSize(int)} in context of the calling 
   * turtle's {@link jchrest.architecture.Chrest} instance. 
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Double report(Argument args[], Context context) throws ExtensionException, LogoException {
    return Double.valueOf(BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(context).getLtmSize(args[0].getIntValue()));
  }
}
