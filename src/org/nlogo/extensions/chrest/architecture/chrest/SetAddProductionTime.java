package org.nlogo.extensions.chrest.architecture.chrest;

import org.nlogo.extensions.chrest.ChrestExtension;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultCommand;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 * @author Martyn Lloyd-Kelly <martynlk@liv.ac.uk>
 */
public class SetAddProductionTime extends DefaultCommand {
  
  @Override
  public Syntax getSyntax(){
    return Syntax.commandSyntax(
      new int[] {
        Syntax.NumberType()
      }
    );
  }
  
  /**
   * Invokes {@link jchrest.architecture.Chrest#setAddLinkTime(int)} in context 
   * of the calling turtle's {@link jchrest.architecture.Chrest} instance.
   * 
   * @param args See parameters for {@link 
   * jchrest.architecture.Chrest#setAddLinkTime(int)}.
   * @param context
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public void perform(Argument args[], Context context) throws ExtensionException, LogoException{
    ChrestExtension.getTurtlesChrestInstance(context).setAddProductionTime(args[0].getIntValue());
  }
}
