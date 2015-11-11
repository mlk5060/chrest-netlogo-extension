package DomainSpecifics;

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
 * jchrest.lib.DomainSpecifics#normalise(jchrest.lib.ListPattern)}
 * for the calling turtle.
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class NormaliseListPattern extends DefaultReporter {

  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(
      new int[]{
        Syntax.WildcardType() //The ListPattern to normalise.
      }, 
      Syntax.WildcardType()
    );
  }
  
  @Override
  public Object report(Argument[] args, Context cntxt) throws ExtensionException, LogoException {
    return BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(cntxt)
      .getDomainSpecifics()
      .normalise((ListPattern)args[0].get());
  }
  
}
