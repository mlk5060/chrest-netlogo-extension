package org.nlogo.extensions.chrest.architecture.node;

import jchrest.architecture.Node;
import jchrest.lib.ListPattern;
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
public class GetFilledSlots extends DefaultReporter {
  
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
   * @param args The first parameter should be a {@link 
   * jchrest.architecture.Node}.  For other parameters see {@link 
   * jchrest.architecture.Node#getFilledSlots(int)}.
   * @param context
   * 
   * @return The result of invoking {@link 
   * jchrest.architecture.Node#getFilledSlots(int)} in context of the parameters
   * passed to this primitive as a {@link org.nlogo.api.LogoList} of {@link 
   * jchrest.lib.ItemSquarePattern ItemSquarePatterns}.  If {@link 
   * jchrest.architecture.Node#getFilledSlots(int)} returns {@code null}, an 
   * empty {@link org.nlogo.api.LogoList} will be returned.
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    LogoListBuilder filledSlotsAsList = new LogoListBuilder();
    ListPattern filledSlots = ((Node)args[0].get()).getFilledSlots(args[1].getIntValue());
    if(filledSlots != null) filledSlotsAsList.addAll(filledSlots);
    return filledSlotsAsList.toLogoList();
  }
  
}
