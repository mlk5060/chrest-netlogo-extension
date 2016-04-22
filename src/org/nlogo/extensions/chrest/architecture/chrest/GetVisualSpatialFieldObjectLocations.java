package org.nlogo.extensions.chrest.architecture.chrest;

import org.nlogo.extensions.chrest.ChrestExtension;
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
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class GetVisualSpatialFieldObjectLocations extends DefaultReporter {
  
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
   * @param args The first parameter should be the time that the search is to be
   * carried out, this will determine the {@link 
   * jchrest.architecture.VisualSpatialField} that will be searched and its 
   * {@link jchrest.lib.VisualSpatialFieldObject} state.
   * <p>
   * The second parameter should specify either an {@link 
   * jchrest.lib.VisualSpatialFieldObject#_identifier} or an {@link 
   * jchrest.lib.VisualSpatialFieldObject#_objectType} to search for as a {@link 
   * java.lang.String}.
   * <p>
   * The third parameter should specify if this primitive should search for 
   * {@link jchrest.lib.VisualSpatialFieldObject VisualSpatialFieldObjects} 
   * based on {@link jchrest.lib.VisualSpatialFieldObject#_identifier} or {@link 
   * jchrest.lib.VisualSpatialFieldObject#_objectType}.  Set to {@link 
   * java.lang.Boolean#FALSE} to search by {@link 
   * jchrest.lib.VisualSpatialFieldObject#_identifier}, set to {@link 
   * java.lang.Boolean#TRUE} to search by {@link 
   * jchrest.lib.VisualSpatialFieldObject#_objectType}.
   * 
   * @param context
   * 
   * @return A {@link org.nlogo.api.LogoList} of {@link org.nlogo.api.LogoList 
   * LogoLists} specifying the {@link jchrest.architecture.VisualSpatialField} 
   * coordinates (column/row) that {@link jchrest.lib.VisualSpatialFieldObject 
   * VisualSpatialFieldObjects} with the identifier/type specified as a 
   * parameter to this primitive are currently located on at the time specified 
   * as a parameter to this function.
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    LogoListBuilder objectLocations = new LogoListBuilder();
    
    int time = args[0].getIntValue();
    String objectToFind = args[1].getString();
    boolean identifyByObjectType = args[2].getBooleanValue();

    VisualSpatialField visualSpatialField = ChrestExtension.getTurtlesChrestInstance(context)
      .getVisualSpatialFields().floorEntry(time).getValue();

    for(int col = 0; col < visualSpatialField.getWidth(); col++){
      for(int row = 0; row < visualSpatialField.getHeight(); row++){
        for(VisualSpatialFieldObject object : visualSpatialField.getCoordinateContents(col, row, time, false)){

          if( objectToFind.equals(identifyByObjectType ? object.getObjectType() : object.getIdentifier()) ){
            LogoListBuilder objectLocation = new LogoListBuilder();
            objectLocation.add((double)col);
            objectLocation.add((double)row);
            objectLocations.add(objectLocation.toLogoList());
          }
        }
      }
    }
    
    return objectLocations.toLogoList();
  }
  
}
