package VisualSpatialField;

import Shared.BaseExtensionVariablesAndMethods;
import java.util.ArrayList;
import jchrest.lib.VisualSpatialFieldObject;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.LogoListBuilder;
import org.nlogo.api.Syntax;

/**
 * Returns the state of the calling turtle's {@link 
 * jchrest.architecture.VisualSpatialField} at the time specified as a {@link 
 * org.nlogo.api.LogoList}.  
 * 
 * The contents of a {@link jchrest.architecture.VisualSpatialField} coordinate 
 * can be returned as either a {@link jchrest.lib.VisualSpatialFieldObject} or 
 * the result of invoking {@link 
 * jchrest.lib.VisualSpatialFieldObject#getIdentifier()} and
 * {@link jchrest.lib.VisualSpatialFieldObject#getObjectClass()} on the 
 * {@link jchrest.lib.VisualSpatialFieldObject} on the coordinates.
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class GetAsNetlogoList extends DefaultReporter {

  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(
      new int[]{
        Syntax.NumberType(), // Time
        Syntax.BooleanType() // Return VisualSpatialFieldObject's?
      }, 
      Syntax.ListType()
    );
  }
  
  @Override
  public Object report(Argument[] args, Context cntxt) throws ExtensionException, LogoException {
    LogoListBuilder visualSpatialFieldList = new LogoListBuilder();
    boolean encodeCompleteVisualSpatialObjects = args[1].getBooleanValue();
    
    ArrayList<ArrayList<ArrayList<VisualSpatialFieldObject>>> visualSpatialField = 
      BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(cntxt)
      .getVisualSpatialFields()
      .lastEntry()
      .getValue()
      .get(args[0].getIntValue());

    for(int col = 0; col < visualSpatialField.size(); col++){
      LogoListBuilder colList = new LogoListBuilder();

      for(int row = 0; row < visualSpatialField.get(col).size(); row++){
        LogoListBuilder rowList = new LogoListBuilder();

        for(VisualSpatialFieldObject object : visualSpatialField.get(col).get(row)){
          if(encodeCompleteVisualSpatialObjects){
            rowList.add(object);
          }
          else{
            LogoListBuilder objectDetails = new LogoListBuilder();
            objectDetails.add(object.getIdentifier());
            objectDetails.add(object.getObjectClass());
            rowList.add(objectDetails.toLogoList());
          }
        }

        colList.add(rowList.toLogoList());
      }

      visualSpatialFieldList.add(colList.toLogoList());
    }
    
    return visualSpatialFieldList.toLogoList();
  }
}
