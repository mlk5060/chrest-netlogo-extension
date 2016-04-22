package org.nlogo.extensions.chrest.architecture.perceiver;

import java.util.List;
import jchrest.architecture.Perceiver;
import jchrest.domainSpecifics.Fixation;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.LogoListBuilder;
import org.nlogo.api.Syntax;

/**
 *
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class GetFixationsPerformed extends DefaultReporter{

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
   * @param args First parameter should be a {@link 
   * jchrest.architecture.Perceiver}.  For other parameters see {@link 
   * jchrest.architecture.Perceiver#getFixationsPerformed(int)}.
   * @param context
   * 
   * @return The result of invoking {@link 
   * jchrest.architecture.Perceiver#getFixationsPerformed(int)} in context of
   * the {@link jchrest.architecture.Perceiver} passed as a first parameter to
   * this primitive and packaging it as a {@link org.nlogo.api.LogoList}.
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    LogoListBuilder fixationsPerformedList = new LogoListBuilder();
    List<Fixation> fixationsPerformed = ((Perceiver)args[0].get()).getFixationsPerformed(args[1].getIntValue());
    if(fixationsPerformed != null) fixationsPerformedList.addAll(fixationsPerformed);
    return fixationsPerformedList.toLogoList();
  }
  
}
