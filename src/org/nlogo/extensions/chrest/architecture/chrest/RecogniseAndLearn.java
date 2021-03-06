package org.nlogo.extensions.chrest.architecture.chrest;

import org.nlogo.extensions.chrest.ChrestExtension;
import jchrest.lib.ListPattern;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 * @author Martyn Lloyd-Kelly <martynlk@liv.ac.uk>
 */
public class RecogniseAndLearn extends DefaultReporter {

  @Override
  public Syntax getSyntax() {
    return Syntax.reporterSyntax(
      new int[]{
        Syntax.WildcardType(),
        Syntax.NumberType()
      },
      Syntax.WildcardType()
    );
  }

  /**
   * Invokes {@link jchrest.architecture.Chrest#recogniseAndLearn(
   * jchrest.lib.ListPattern, java.lang.Integer)} in context of the calling 
   * turtle's {@link jchrest.architecture.Chrest} instance.
   * 
   * @param args See parameters for {@link 
   * jchrest.architecture.Chrest#recogniseAndLearn(jchrest.lib.ListPattern, 
   * java.lang.Integer)}.
   * @param context
   * 
   * @return See {@link 
   * jchrest.architecture.Chrest#recogniseAndLearn(jchrest.lib.ListPattern, 
   * int}.
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Object report(Argument args[], Context context) throws ExtensionException, LogoException {
    return ChrestExtension.getTurtlesChrestInstance(context).recogniseAndLearn((ListPattern)args[0].get(), args[1].getIntValue());
  }
}
