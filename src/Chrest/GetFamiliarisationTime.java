package Chrest;

import Shared.BaseExtensionVariablesAndMethods;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.Syntax;

/**
 * Reports the value of {@link 
 * jchrest.architecture.Chrest#getFamiliarisationTime()} for the calling turtle.
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liv.ac.uk>
 */
public class GetFamiliarisationTime extends DefaultReporter {
  
  @Override
  public Syntax getSyntax() {
    return Syntax.reporterSyntax(Syntax.NumberType());
  }
  
  @Override
  public Object report(Argument args[], Context context) throws ExtensionException{
    return Double.valueOf(BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(context).getFamiliarisationTime());
  }
  
}
