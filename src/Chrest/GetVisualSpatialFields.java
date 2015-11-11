package Chrest;

import Shared.BaseExtensionVariablesAndMethods;
import java.util.Map.Entry;
import jchrest.architecture.VisualSpatialField;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.LogoListBuilder;
import org.nlogo.api.Syntax;

/**
 * Returns the result of invoking {@link 
 * jchrest.architecture.Chrest#getVisualSpatialFields()} and converting the
 * result into a 2D {@link org.nlogo.api.LogoList}. 
 * 
 * First dimension elements are {@link org.nlogo.api.LogoList}s whose first 
 * element is the time the second element of the list (a {@link 
 * jchrest.architecture.VisualSpatialField}) was created by the calling turtle.
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class GetVisualSpatialFields extends DefaultReporter{
  
  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(Syntax.ListType());
  }

  @Override
  public Object report(Argument[] argmnts, Context cntxt) throws ExtensionException, LogoException {
    LogoListBuilder visualSpatialHistory = new LogoListBuilder();

    for(Entry<Integer, VisualSpatialField> entry : BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(cntxt).getVisualSpatialFields().entrySet()){
      LogoListBuilder visualSpatialHistoryEntry = new LogoListBuilder();
      visualSpatialHistoryEntry.add((double)entry.getKey());
      visualSpatialHistoryEntry.add(entry.getValue());
      visualSpatialHistory.add(visualSpatialHistoryEntry.toLogoList());
    }
    
    return visualSpatialHistory.toLogoList();
  }
  
}
