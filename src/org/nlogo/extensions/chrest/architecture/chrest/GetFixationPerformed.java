package org.nlogo.extensions.chrest.architecture.chrest;

import jchrest.domainSpecifics.Fixation;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;
import org.nlogo.extensions.chrest.ChrestExtension;

/**
 *
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class GetFixationPerformed extends DefaultReporter {

  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(
      new int[]{
        Syntax.NumberType(),
        Syntax.NumberType()
      },
      Syntax.WildcardType()
    );
  }

  /**
   * 
   * @param args See parameter descriptions for {@link 
   * jchrest.architecture.Chrest#getFixationPerformed(int, int)}.
   * @param context
   * 
   * @return The result of {@link 
   * jchrest.architecture.Chrest#getFixationPerformed(int, int)}. If this is 
   * {@code null} an empty {@link java.lang.String} will be returned so that an 
   * easy "isEmpty?" check can be performed in a Netlogo model that makes use of 
   * this extension primitive.
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    Fixation fixation = ChrestExtension.getTurtlesChrestInstance(context).getFixationPerformed(
      args[0].getIntValue(), 
      args[1].getIntValue()
    );
    
    return fixation == null ? "" : fixation;
  }
  
}
