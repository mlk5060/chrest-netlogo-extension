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
 * jchrest.architecture.Chrest#recognise(jchrest.lib.ListPattern, int)} for the
 * calling turtle.
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class Recognise extends DefaultReporter {

  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(
      new int[]{
        Syntax.WildcardType(), //ListPattern
        Syntax.NumberType() //Time to recognise
      },
      Syntax.WildcardType()
    );
  }
  
  @Override
  public Object report(Argument[] args, Context cntxt) throws ExtensionException, LogoException {
    return BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(cntxt).recognise((ListPattern)args[0].get(), args[1].getIntValue());
  }
  
}
