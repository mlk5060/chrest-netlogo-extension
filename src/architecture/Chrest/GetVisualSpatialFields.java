package architecture.Chrest;

import classManager.ChrestExtension;
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
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class GetVisualSpatialFields extends DefaultReporter{
  
  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(Syntax.ListType());
  }

  /**
   * 
   * @param args
   * @param cntxt
   * 
   * @return the result of invoking {@link 
   * jchrest.architecture.Chrest#getVisualSpatialFields()} in context of the 
   * calling turtle's {@link jchrest.architecture.Chrest} instance and 
   * converting the result into a two-dimensional {@link 
   * org.nlogo.api.LogoList}.
   * <p>
   * First dimension elements are {@link org.nlogo.api.LogoList}s whose first 
   * element is the time the second element of the list (a {@link 
   * jchrest.architecture.VisualSpatialField}) was created by the calling 
   * turtle.
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Object report(Argument[] args, Context cntxt) throws ExtensionException, LogoException {
    LogoListBuilder visualSpatialFieldHistory = new LogoListBuilder();

    for(Entry<Integer, VisualSpatialField> entry : ChrestExtension.getTurtlesChrestInstance(cntxt).getVisualSpatialFields().entrySet()){
      LogoListBuilder visualSpatialHistoryEntry = new LogoListBuilder();
      visualSpatialHistoryEntry.add((double)entry.getKey());
      visualSpatialHistoryEntry.add(entry.getValue());
      visualSpatialFieldHistory.add(visualSpatialHistoryEntry.toLogoList());
    }
    
    return visualSpatialFieldHistory.toLogoList();
  }
  
}
