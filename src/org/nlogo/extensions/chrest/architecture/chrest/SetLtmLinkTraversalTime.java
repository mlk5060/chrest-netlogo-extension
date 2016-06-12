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
public class SetLtmLinkTraversalTime extends DefaultCommand {

  @Override
  public Syntax getSyntax(){
    return Syntax.commandSyntax(new int[]{Syntax.NumberType()});
  }
  
  /**
   * Invokes {@link jchrest.architecture.Chrest#setLtmLinkTraversalTime(int)} in
   * context of the calling turtle's {@link jchrest.architecture.Chrest} model.
   * 
   * @param args See parameter descriptions for {@link 
   * jchrest.architecture.Chrest#setLtmLinkTraversalTime(int)}.
   * @param context
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public void perform(Argument[] args, Context context) throws ExtensionException, LogoException {
    ChrestExtension.getTurtlesChrestInstance(context).setLtmLinkTraversalTime(args[0].getIntValue());
  }
  
}
