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
public class GetObjectLocations extends DefaultReporter {
  
  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(
      new int[]{
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
   * @return A {@link org.nlogo.api.LogoList} containing coordinates relative 
   * to the {@link jchrest.architecture.VisualSpatialField} that exists at the 
   * time specified to search at where the {@link 
   * jchrest.lib.VisualSpatialFieldObject} to be searched for is found.
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    LogoListBuilder objectLocations = new LogoListBuilder();
    Map.Entry<Integer, VisualSpatialField> entry = ChrestExtension.getTurtlesChrestInstance(context).getVisualSpatialFields().floorEntry(args[0].getIntValue());
    if(entry != null){
      
      VisualSpatialField visualSpatialField = entry.getValue();
      for(int col = 0; col < visualSpatialField.getWidth(); col++){
        for(int row = 0; row < visualSpatialField.getHeight(); row++){
          List<VisualSpatialFieldObject> coordinateContents = visualSpatialField.getCoordinateContents(col, row, args[0].getIntValue(), false);
          for(VisualSpatialFieldObject objectOnCoordinate : coordinateContents){
            if(args[1].getString().equals( args[2].getBooleanValue() ? objectOnCoordinate.getObjectType() : objectOnCoordinate.getIdentifier() )){
              LogoListBuilder objectLocation = new LogoListBuilder();
              objectLocation.add((double)col);
              objectLocation.add((double)row);
              objectLocations.add(objectLocation.toLogoList());
            }
          }
        }
      }
    }
    
    return objectLocations.toLogoList();
  }
  
}
