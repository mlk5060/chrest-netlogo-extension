package org.nlogo.extensions.chrest.architecture.chrest;

import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultCommand;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;
import org.nlogo.extensions.chrest.ChrestExtension;

/**
 *
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class SaveLtmState extends DefaultCommand {
  
  @Override
  public Syntax getSyntax(){
    return Syntax.commandSyntax(
      new int[]{
        Syntax.StringType(),
        Syntax.NumberType()
      }
    );
  }

  /**
   * 
   * @param args
   * @param context
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public void perform(Argument[] args, Context context) throws ExtensionException, LogoException {
    ChrestExtension.getTurtlesChrestInstance(context).saveLtmState(args[0].getString(), args[1].getIntValue());
  }
  
}
