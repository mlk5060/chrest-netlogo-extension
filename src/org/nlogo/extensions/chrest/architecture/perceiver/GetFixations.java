package org.nlogo.extensions.chrest.architecture.perceiver;

import jchrest.architecture.Perceiver;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.LogoListBuilder;
import org.nlogo.api.Syntax;

/**
 * Returns  with duplicate {@link jchrest.lib.Fixation}s 
 * removed.
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class GetFixations extends DefaultReporter {
  
  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(
      new int[]{
        Syntax.WildcardType(),
        Syntax.NumberType()
      },
      Syntax.ListType()
    );
  }

  /**
   * 
   * @param args First parameter should be the {@link 
   * jchrest.architecture.Perceiver} that {@link 
   * jchrest.architecture.Perceiver#getFixations(int)} is to be invoked in 
   * context of.  For other parameters see {@link 
   * jchrest.architecture.Perceiver#getFixations(int)}.
   * @param context
   * 
   * @return The result of invoking {@link 
   * jchrest.architecture.Perceiver#getFixations(int)} as a a {@link 
   * org.nlogo.api.LogoList} in context of the {@link 
   * jchrest.architecture.Perceiver} specified as a parameter passed to this 
   * primitive.
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    LogoListBuilder fixationsLogoList = new LogoListBuilder();
    fixationsLogoList.addAll(((Perceiver)args[0].get()).getFixations(args[1].getIntValue()));
    return fixationsLogoList.toLogoList();
  }
  
}
