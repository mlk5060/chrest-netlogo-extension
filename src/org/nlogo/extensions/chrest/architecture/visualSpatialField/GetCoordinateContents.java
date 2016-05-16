package org.nlogo.extensions.chrest.architecture.visualSpatialField;

import java.util.List;
import java.util.Map;
import jchrest.architecture.VisualSpatialField;
import jchrest.lib.VisualSpatialFieldObject;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.LogoListBuilder;
import org.nlogo.api.Syntax;
import org.nlogo.extensions.chrest.ChrestExtension;

/**
 *
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class GetCoordinateContents extends DefaultReporter {

  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(
      new int[]{
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
   * @param args See {@link 
   * jchrest.architecture.VisualSpatialField#getCoordinateContents(int, int, 
   * int, boolean)}.
   * @param context
   * 
   * @return The result of invoking {@link 
   * jchrest.architecture.VisualSpatialField#getCoordinateContents(int, int, 
   * int, boolean)} as a {@link org.nlogo.api.LogoList} in context of the {@link 
   * jchrest.architecture.VisualSpatialField} associated with the calling {@link 
   * org.nlogo.agent.Turtle Turtle's} {@link jchrest.architecture.Chrest} model
   * at the time specified as a parameter to this primitive.
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    LogoListBuilder coordinateContentsList = new LogoListBuilder();
    
    Map.Entry<Integer, VisualSpatialField> entry = ChrestExtension.getTurtlesChrestInstance(context).getVisualSpatialFields().floorEntry(args[2].getIntValue());
    if( entry!= null ){
      List<VisualSpatialFieldObject> coordinateContents = entry.getValue().getCoordinateContents(args[0].getIntValue(), args[1].getIntValue(), args[2].getIntValue(), args[3].getBooleanValue());
      if(coordinateContents != null) coordinateContentsList.addAll(coordinateContents);
    }
    
    return coordinateContentsList.toLogoList();
  }
  
}
