package Chrest;

import Shared.BaseExtensionVariablesAndMethods;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 * Returns the result of invoking {@link 
 * jchrest.architecture.Chrest#getActionLtmAverageDepth()} for the calling 
 * turtle.
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liv.ac.uk>
 */
public class GetActionLtmAvgDepth extends DefaultReporter {

  @Override
  public Syntax getSyntax() {
    return Syntax.reporterSyntax(Syntax.NumberType());
  }

  @Override
  public Double report(Argument args[], Context context) throws ExtensionException, LogoException {
    return BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(context).getActionLtmAverageDepth();
  }
}