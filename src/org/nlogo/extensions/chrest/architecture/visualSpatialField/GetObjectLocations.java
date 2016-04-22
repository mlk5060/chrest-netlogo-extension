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
public class GetObjectLocations extends DefaultReporter {
  
  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(
      new int[]{
        Syntax.WildcardType(),
        Syntax.NumberType(),
        Syntax.StringType(),
        Syntax.BooleanType()
      },
      Syntax.ListType()
    );
  }

  /**
   * 
   * @param args 
   * <ol type="1">
   *    <li>
   *      The {@link jchrest.architecture.VisualSpatialField} that should be 
   *      searched.
   *    </li>
   *    <li>
   *      The time the {@link jchrest.architecture.VisualSpatialField} should be 
   *      searched at.
   *    </li>
   *    <li>
   *      The type or identifier of the {@link 
   *      jchrest.lib.VisualSpatialFieldObject} to be searched for.
   *    </li>
   *    <li>
   *      Whether {@link jchrest.lib.VisualSpatialFieldObject 
   *      VisualSpatialFieldObjects} should be searched on the result of {@link 
   *      jchrest.lib.VisualSpatialFieldObject#getObjectType()} (pass {@link 
   *      java.lang.Boolean#TRUE}) or {@link 
   *      jchrest.lib.VisualSpatialFieldObject#getIdentifier()} (pass {@link 
   *      java.lang.Boolean#FALSE}).
   *    </li>
   * </ol>
   * @param context
   * 
   * @return A {@link org.nlogo.api.LogoList} containing each location
   * on the {@link jchrest.architecture.VisualSpatialField} where the {@link 
   * jchrest.lib.VisualSpatialFieldObject} to be searched for is found.
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    LogoListBuilder objectLocations = new LogoListBuilder();
    VisualSpatialField visualSpatialField = (VisualSpatialField)args[0].get();
    
    for(int col = 0; col < visualSpatialField.getWidth(); col++){
      for(int row = 0; row < visualSpatialField.getHeight(); row++){
        List<VisualSpatialFieldObject> coordinateContents = visualSpatialField.getCoordinateContents(col, row, args[1].getIntValue(), false);
        for(VisualSpatialFieldObject objectOnCoordinate : coordinateContents){
          if(args[2].equals( args[3].getBooleanValue() ? objectOnCoordinate.getObjectType() : objectOnCoordinate.getIdentifier() )){
            LogoListBuilder objectLocation = new LogoListBuilder();
            objectLocation.add(col);
            objectLocation.add(row);
            objectLocations.add(objectLocation);
          }
        }
      }
    }
    
    return objectLocations.toLogoList();
  }
  
}
