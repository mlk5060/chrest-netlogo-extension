package org.nlogo.extensions.chrest.architecture.chrest;

import org.nlogo.extensions.chrest.ChrestExtension;
import jchrest.lib.Modality;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 * @author Martyn Lloyd-Kelly <martynlk@liv.ac.uk>
 */
public class GetLtmAverageDepth extends DefaultReporter {

  @Override
  public Syntax getSyntax() {
    return Syntax.reporterSyntax(
      new int[]{
        Syntax.WildcardType(), 
        Syntax.NumberType()
      }, 
      Syntax.NumberType()
    );
  }

  /**
   * 
   * @param args See parameters for {@link 
   * jchrest.architecture.Chrest#getLtmAverageDepth(jchrest.lib.Modality, int)}.  
   * First parameter can be obtained using {@link
   * lib.modality.GetModalities#report(org.nlogo.api.Argument[], 
   * org.nlogo.api.Context)}.
   * @param context
   * 
   * @return The result of invoking {@link 
   * jchrest.architecture.Chrest#getActionLtmAverageDepth()} in context of the 
   * calling turtle's {@link jchrest.architecture.Chrest} instance.
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Double report(Argument args[], Context context) throws ExtensionException, LogoException {
    return ChrestExtension.getTurtlesChrestInstance(context).getLtmAverageDepth((Modality)args[0].get(), args[1].getIntValue());
  }
}
