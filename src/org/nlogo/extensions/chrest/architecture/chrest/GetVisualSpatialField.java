package org.nlogo.extensions.chrest.architecture.chrest;

import org.nlogo.extensions.chrest.ChrestExtension;
import java.util.Map.Entry;
import jchrest.architecture.VisualSpatialField;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class GetVisualSpatialField extends DefaultReporter{
  
  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(
      new int[]{
        Syntax.NumberType(),
      }, 
      Syntax.WildcardType()
    );
  }

  /**
   * 
   * @param args The time to retrieve a {@link 
   * jchrest.architecture.VisualSpatialField} associated with
   * the calling turtle's {@link jchrest.architecture.Chrest} instance at.
   * @param cntxt
   * 
   * @return The {@link jchrest.architecture.VisualSpatialField} associated with
   * the calling turtle's {@link jchrest.architecture.Chrest} instance at the 
   * time specified as a parameter passed to this function or {@code null} if 
   * there is no {@link jchrest.architecture.VisualSpatialField} associated with
   * the calling turtle's {@link jchrest.architecture.Chrest} instance at the 
   * time specified.
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Object report(Argument[] args, Context cntxt) throws ExtensionException, LogoException {
    Entry<Integer, VisualSpatialField> entry = ChrestExtension.getTurtlesChrestInstance(cntxt).getVisualSpatialFields().floorEntry(args[0].getIntValue());
    return (entry == null ? null : entry.getValue());
  }
  
}
