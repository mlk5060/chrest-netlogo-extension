package VisualSpatialField;

import Shared.BaseExtensionVariablesAndMethods;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 * Returns the result of invoking {@link 
 * jchrest.architecture.VisualSpatialField#getAsScene(int, boolean)} for the 
 * calling turtle's most recently created 
 * {@link jchrest.architecture.VisualSpatialField}.
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class GetAsScene extends DefaultReporter {
  
  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(new int[]{
      Syntax.NumberType(), // Time to return state of visual-spatial field at
      Syntax.BooleanType() // Include ghost objects?
    }, 
    Syntax.ListType()
    );
  }
  
  @Override
  public Object report(Argument args[], Context context) throws ExtensionException, LogoException{
    return BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(context)
        .getVisualSpatialFields()
        .lastEntry()
        .getValue()
        .getAsScene(args[0].getIntValue(), args[1].getBooleanValue());
  }
}
