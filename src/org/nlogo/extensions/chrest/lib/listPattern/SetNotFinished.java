package org.nlogo.extensions.chrest.lib.listPattern;

import jchrest.lib.ListPattern;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultCommand;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 *
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class SetNotFinished extends DefaultCommand {

  @Override
  public Syntax getSyntax(){
    return Syntax.commandSyntax(new int[]{Syntax.WildcardType()});
  }
  
  /**
   * Invokes {@link jchrest.lib.ListPattern#setFinished()} on the {@link 
   * jchrest.lib.ListPattern} passed as a parameter.
   * 
   * @param args A {@link jchrest.lib.ListPattern}.
   * @param context
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public void perform(Argument[] args, Context context) throws ExtensionException, LogoException {
    ((ListPattern)args[0].get()).setNotFinished();
  }
  
}
