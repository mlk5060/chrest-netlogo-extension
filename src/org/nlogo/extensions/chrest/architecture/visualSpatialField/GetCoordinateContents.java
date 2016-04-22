package org.nlogo.extensions.chrest.architecture.visualSpatialField;

import java.util.List;
import jchrest.architecture.VisualSpatialField;
import jchrest.lib.VisualSpatialFieldObject;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.LogoListBuilder;
import org.nlogo.api.Syntax;

/**
 *
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class GetCoordinateContents extends DefaultReporter {

  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(
      new int[]{
        Syntax.WildcardType(),
        Syntax.NumberType(),
        Syntax.NumberType(),
        Syntax.NumberType(),
        Syntax.BooleanType()
      },
      Syntax.ListType()
    );
  }
  
  /**
   * 
   * @param args First parameter should be a {@link 
   * jchrest.architecture.VisualSpatialField}. For other parameters see {@link 
   * jchrest.architecture.VisualSpatialField#getCoordinateContents(int, int, 
   * int, boolean)}.
   * @param context
   * 
   * @return The result of invoking {@link 
   * jchrest.architecture.VisualSpatialField#getCoordinateContents(int, int, 
   * int, boolean)} in context of the parameters passed to this primitive as a
   * {@link org.nlogo.api.LogoList}.
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    LogoListBuilder coordinateContentsList = new LogoListBuilder();
    List<VisualSpatialFieldObject> coordinateContents = ((VisualSpatialField)args[0].get()).getCoordinateContents(args[1].getIntValue(), args[2].getIntValue(), args[3].getIntValue(), args[4].getBooleanValue());
    if(coordinateContents != null) coordinateContentsList.addAll(coordinateContents);
    return coordinateContentsList.toLogoList();
  }
  
}
