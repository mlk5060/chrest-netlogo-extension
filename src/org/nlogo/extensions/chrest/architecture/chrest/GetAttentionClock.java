package org.nlogo.extensions.chrest.architecture.chrest;

import org.nlogo.extensions.chrest.ChrestExtension;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.Syntax;

/**
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class GetAttentionClock extends DefaultReporter{
  
  @Override
  public Syntax getSyntax() {
    return Syntax.reporterSyntax(Syntax.NumberType());
  }
  
  /**
   * 
   * @param args
   * @param context
   * 
   * @return The result of invoking {@link 
   * jchrest.architecture.Chrest#getAttentionClock()} in context of the 
   * calling turtle's {@link jchrest.architecture.Chrest} instance.
   * 
   * @throws ExtensionException 
   */
  @Override
  public Object report(Argument args[], Context context) throws ExtensionException{
    return Double.valueOf(ChrestExtension.getTurtlesChrestInstance(context).getAttentionClock());
  }
}
