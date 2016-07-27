package org.nlogo.extensions.chrest.lib.listPattern;

import jchrest.lib.ListPattern;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class RemoveBlindEmptyAndUnknownPatches extends DefaultReporter {

  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(
      new int[]{
        Syntax.WildcardType()
      },
      Syntax.WildcardType()
    );
  }
  
  /**
   * 
   * @param args The {@link jchrest.lib.ListPattern} to invoke {@link 
   * jchrest.lib.ListPattern#removeBlindEmptyAndUnknownItems()} in context of.
   * @param context
   * 
   * @return See {@link 
   * jchrest.lib.ListPattern#removeBlindEmptyAndUnknownItems()}.
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    return ((ListPattern)args[0].get()).removeBlindEmptyAndUnknownItems();
  }
  
}
