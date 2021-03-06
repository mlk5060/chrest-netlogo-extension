package org.nlogo.extensions.chrest.architecture.chrest;

import org.nlogo.extensions.chrest.ChrestExtension;
import jchrest.domainSpecifics.Scene;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 *
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class ScheduleOrMakeNextFixation extends DefaultReporter {

  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(
      new int[]{
        Syntax.WildcardType(),
        Syntax.BooleanType(),
        Syntax.BooleanType(),
        Syntax.NumberType()
      },
      Syntax.BooleanType()
    );
  }
  
  /**
   * 
   * @param args See parameters for {@link 
   * jchrest.architecture.Chrest#scheduleOrMakeNextFixation(
   * jchrest.domainSpecifics.Scene, boolean, boolean, int)}.
   * @param context
   * 
   * @return The result of invoking {@link 
   * jchrest.architecture.Chrest#scheduleOrMakeNextFixation(
   * jchrest.domainSpecifics.Scene, boolean, boolean, int)} in context of the 
   * calling turtle's {@link jchrest.architecture.Chrest} model.
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    return ChrestExtension.getTurtlesChrestInstance(context).scheduleOrMakeNextFixation(
      (Scene)args[0].get(), 
      args[1].getBooleanValue(), 
      args[2].getBooleanValue(),
      args[3].getIntValue()
    );
  }
  
}
