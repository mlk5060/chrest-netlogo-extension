package org.nlogo.extensions.chrest.lib.listPattern;

import jchrest.lib.ListPattern;
import jchrest.lib.PrimitivePattern;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.LogoListBuilder;
import org.nlogo.api.Syntax;

/**
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class GetAsNetlogoList extends DefaultReporter {
  
  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(
      new int[]{
        Syntax.WildcardType()
      },
      Syntax.ListType()
    );
  }

  /**
   * 
   * @param args The {@link jchrest.lib.ListPattern} to render as a {@link 
   * org.nlogo.api.LogoList}.
   * @param context
   * 
   * @return A {@link org.nlogo.api.LogoList} containing the {@link 
   * jchrest.lib.PrimitivePattern PrimitivePatterns} constituting the {@link 
   * jchrest.lib.ListPattern} specified as a parameter to this primitive.
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    LogoListBuilder list = new LogoListBuilder();
    ListPattern listPattern = (ListPattern)args[0].get();
    for(PrimitivePattern pattern : listPattern){
      list.add(pattern);
    }
    return list.toLogoList();
  }
  
}
