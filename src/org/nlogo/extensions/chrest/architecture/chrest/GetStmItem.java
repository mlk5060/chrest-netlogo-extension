package org.nlogo.extensions.chrest.architecture.chrest;

import jchrest.architecture.Node;
import jchrest.lib.Modality;
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
public class GetStmItem extends DefaultReporter {
  
  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(
      new int[]{
        Syntax.WildcardType(),
        Syntax.NumberType(),
        Syntax.NumberType()
      },
      Syntax.WildcardType()
    );
  }

  /**
   * 
   * @param args See parameter descriptions for {@link 
   * jchrest.architecture.Chrest#getStmItem(jchrest.lib.Modality, int, int)}.
   * @param context
   * 
   * @return The result of {@link 
   * jchrest.architecture.Chrest#getStmItem(jchrest.lib.Modality, int, int)}. If
   * this is {@code null} an empty {@link java.lang.String} will be returned so
   * that an easy "isEmpty?" check can be performed in a Netlogo model that 
   * makes use of this extension primitive.
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    Node stmItem = ChrestExtension.getTurtlesChrestInstance(context).getStmItem(
      (Modality)args[0].get(), 
      args[1].getIntValue(), 
      args[2].getIntValue()
    );
    
    return stmItem == null ? "" : stmItem;
  }
  
}
