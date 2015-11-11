package VisualSpatialField;

import Shared.BaseExtensionVariablesAndMethods;
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
 * Returns the locations of {@link jchrest.lib.VisualSpatialFieldObject}s in the
 * calling turtle's {@link jchrest.lib.VisualSpatialField} at the time 
 * specified.
 * 
 * The {@link jchrest.lib.VisualSpatialFieldObject}s to search for can be 
 * specified using either a {@link 
 * jchrest.lib.VisualSpatialFieldObject#_identifier} or an {@link 
 * jchrest.lib.VisualSpatialFieldObject#_objectClass}.
 * 
 * The locations returned are packaged in a {@link org.nlogo.api.LogoList}
 * that contains {@link org.nlogo.api.LogoList}s of distinct locations of the 
 * {@link jchrest.lib.VisualSpatialFieldObject} specified.  The locations of the 
 * {@link jchrest.lib.VisualSpatialFieldObject} are absolute (non-creator 
 * relative) coordinates and in column/row order.
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class GetObjectLocations extends DefaultReporter {
  
  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(
      new int[]{
        Syntax.NumberType(), // The time that the visual-spatial field state should be searched.
        Syntax.StringType(), // The object to search for (ID or class)
        Syntax.BooleanType() // Set to true to search by ID, otherwise, search by class
      }, 
      Syntax.ListType()
    );
  }

  @Override
  public Object report(Argument[] args, Context cntxt) throws ExtensionException, LogoException {
    LogoListBuilder objectLocations = new LogoListBuilder();
    
    int time = args[0].getIntValue();
    String objectToFind = args[1].getString();
    boolean identifyByObjectClass = args[2].getBooleanValue();

    VisualSpatialField visualSpatialField = BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(cntxt)
      .getVisualSpatialFields().floorEntry(time).getValue();

    for(int col = 0; col < visualSpatialField.getWidth(); col++){
      for(int row = 0; row < visualSpatialField.getHeight(); row++){
        for(VisualSpatialFieldObject object : visualSpatialField.getSquareContents(col, row, time)){

          if( objectToFind.equals(identifyByObjectClass ? object.getIdentifier() : object.getObjectClass()) ){
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
