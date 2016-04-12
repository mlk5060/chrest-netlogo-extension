package DomainSpecifics;

import Shared.BaseExtensionVariablesAndMethods;
import jchrest.lib.ListPattern;
import jchrest.lib.Scene;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 * Reports the result of invoking {@link jchrest.lib.DomainSpecifics#
 * convertDomainSpecificCoordinatesToSceneSpecificCoordinates(
 * jchrest.lib.ListPattern, jchrest.lib.Scene)} for the calling turtle.
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class ConvertFromSceneToDomainSpecificCoordinates extends DefaultReporter {
  
  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(
      new int[]{
        Syntax.WildcardType(),
        Syntax.WildcardType()
      },
      Syntax.WildcardType()
    );
  }

  @Override
  public Object report(Argument[] args, Context cntxt) throws ExtensionException, LogoException {
    return BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(cntxt)
      .getDomainSpecifics()
      .convertSceneSpecificCoordinatesToDomainSpecificCoordinates(
        (ListPattern)args[0].get(), 
        (Scene)args[1].get()
      );
  }
  
}
