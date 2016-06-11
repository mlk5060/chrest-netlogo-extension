package org.nlogo.extensions.chrest.architecture.chrest;

import java.util.List;
import jchrest.domainSpecifics.Fixation;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.LogoListBuilder;
import org.nlogo.api.Syntax;
import org.nlogo.extensions.chrest.ChrestExtension;

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
        Syntax.NumberType()
      },
      Syntax.ListType()
    );
  }

  /**
   * 
   * @param args See {@link 
   * jchrest.architecture.Perceiver#getFixations(int)}.
   * @param context
   * 
   * @return The result of invoking {@link 
   * jchrest.architecture.Perceiver#getFixations(int)} as a {@link 
   * org.nlogo.api.LogoList} in context of the {@link 
   * jchrest.architecture.Perceiver} associated with the calling {@link 
   * org.nlogo.agent.Turtle Turtle's} {@link jchrest.architecture.Chrest} model.
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    LogoListBuilder fixationsAsList = new LogoListBuilder();
    List<Fixation> fixations = ChrestExtension.getTurtlesChrestInstance(context).getPerceiver().getFixations(args[0].getIntValue());
    if(fixations != null) fixationsAsList.addAll(fixations);
    return fixationsAsList.toLogoList();
  }
  
}
