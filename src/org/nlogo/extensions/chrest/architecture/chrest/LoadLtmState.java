package org.nlogo.extensions.chrest.architecture.chrest;

import java.util.logging.Level;
import java.util.logging.Logger;
import jchrest.architecture.Chrest;
import org.nlogo.api.AgentException;
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
public class LoadLtmState extends DefaultCommand {
  
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
    try {
      ChrestExtension.getAgent(context).setBreedVariable(
        ChrestExtension.CHREST_BREED_VARIABLE,
        new Chrest(args[0].getString(), args[1].getIntValue())
      );
    } catch (AgentException ex) {
      Logger.getLogger(LoadLtmState.class.getName()).log(Level.SEVERE, "", ex);
    }
  }
  
}
