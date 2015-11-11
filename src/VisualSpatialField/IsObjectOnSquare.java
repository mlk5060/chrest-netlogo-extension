package VisualSpatialField;

import Shared.BaseExtensionVariablesAndMethods;
import jchrest.architecture.VisualSpatialField;
import jchrest.lib.VisualSpatialFieldObject;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 * Returns {@link java.lang.Boolean#TRUE} if the {@link 
 * jchrest.lib.VisualSpatialFieldObject} specified is on the coordinates 
 * specified in the calling turtle's {@link 
 * jchrest.architecture.VisualSpatialField} at the time specified and {@link 
 * java.lang.Boolean#FALSE} if not.
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class IsObjectOnSquare extends DefaultReporter {

  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(
      new int[]{
        Syntax.NumberType(), // The time that the visual-spatial field state should be searched.
        Syntax.StringType(), // The object to search for (ID or class)
        Syntax.NumberType(), // The x-coordinate to search
        Syntax.NumberType(), // The y-coordinate to search
        Syntax.BooleanType() // Set to true to search by ID, otherwise, search by class
      }, 
      Syntax.BooleanType()
    );
  }
  
  @Override
  public Object report(Argument[] args, Context cntxt) throws ExtensionException, LogoException {
    int time = args[0].getIntValue();
    String objectToFind = args[1].getString();
    boolean identifyByObjectClass = args[4].getBooleanValue();

    VisualSpatialField visualSpatialField = BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(cntxt)
      .getVisualSpatialFields().floorEntry(time).getValue();

    for(VisualSpatialFieldObject object : visualSpatialField.getSquareContents(args[2].getIntValue(), args[3].getIntValue(), time)){
      if( objectToFind.equals(identifyByObjectClass ? object.getIdentifier() : object.getObjectClass()) ){
        return true;
      }
    }
    
    return false;
  }
  
}
