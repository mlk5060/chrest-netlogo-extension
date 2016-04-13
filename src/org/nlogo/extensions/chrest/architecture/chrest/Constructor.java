package org.nlogo.extensions.chrest.architecture.chrest;

import org.nlogo.extensions.chrest.ChrestExtension;
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

/**
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class Constructor extends DefaultCommand {
  
  @Override
  public Syntax getSyntax(){
    return Syntax.commandSyntax(
      new int[]{
        Syntax.NumberType(),
        Syntax.BooleanType()
      }
    );
  }

  /**
   * Sets the result of {@link jchrest.architecture.Chrest#Chrest(int, boolean)}
   * to the relevant breed variable of the calling turtle (see {@link 
   * Shared.BaseExtensionVariablesAndMethods#CHREST_BREED_VARIABLE_NAME).
   * 
   * @param args See parameters for {@link jchrest.architecture.Chrest#Chrest(
   * int, boolean)}.
   * @param context
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public void perform(Argument[] args, Context context) throws ExtensionException, LogoException {
    try {
      ChrestExtension.getAgent(context).setBreedVariable(
        ChrestExtension.CHREST_BREED_VARIABLE,
        new Chrest(args[0].getIntValue(), args[1].getBooleanValue())
      );
    } catch (AgentException ex) {
      Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
    }
  }
}
