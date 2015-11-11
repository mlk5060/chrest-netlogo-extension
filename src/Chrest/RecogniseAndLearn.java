package Chrest;

import Shared.BaseExtensionVariablesAndMethods;
import jchrest.lib.ListPattern;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 * Returns the result of invoking {@link 
 * jchrest.architecture.Chrest#recogniseAndLearn(jchrest.lib.ListPattern, int)}
 * for the calling turtle.
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liv.ac.uk>
 */
public class RecogniseAndLearn extends DefaultReporter {

  @Override
  public Syntax getSyntax() {
    return Syntax.reporterSyntax(
      new int[]{
        Syntax.WildcardType(), //The ListPattern to learn
        Syntax.NumberType() //The current Netlogo model time (in milliseconds).
      }, 
      Syntax.WildcardType());
  }

  @Override
  public Object report(Argument args[], Context context) throws ExtensionException, LogoException {
    return BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(context).recogniseAndLearn((ListPattern)args[0].get());
  }
}
