package org.nlogo.extensions.chrest.architecture.perceiver;

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
public class GetFixationFieldOfView extends DefaultReporter {

  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(Syntax.NumberType());
  }
  
  /**
   * 
   * @param args 
   * @param context
   * 
   * @return The result of invoking {@link 
   * jchrest.architecture.Perceiver#getFixationFieldOfView()} in context of the
   * {@link jchrest.architecture.Perceiver} associated with the calling {@link 
   * org.nlogo.agent.Turtle Turtle's} {@link jchrest.architecture.Chrest} model.
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    return (double)(ChrestExtension.getTurtlesChrestInstance(context).getPerceiver().getFixationFieldOfView());
  }
  
}
