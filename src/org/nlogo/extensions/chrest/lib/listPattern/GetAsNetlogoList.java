package org.nlogo.extensions.chrest.lib.listPattern;

import jchrest.lib.ItemSquarePattern;
import jchrest.lib.ListPattern;
import jchrest.lib.NumberPattern;
import jchrest.lib.PrimitivePattern;
import jchrest.lib.StringPattern;
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
   * jchrest.lib.ListPattern} specified as a parameter to this primitive.  Note
   * that the {@link jchrest.lib.PrimitivePattern PrimitivePatterns} will be 
   * cast to their appropriate type before addition, i.e. {@link 
   * jchrest.lib.ItemSquarePattern}, {@link jchrest.lib.NumberPattern} or {@link 
   * jchrest.lib.StringPattern}.
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    LogoListBuilder list = new LogoListBuilder();
    ListPattern listPattern = (ListPattern)args[0].get();
    for(PrimitivePattern pattern : listPattern){
      
      if(pattern instanceof ItemSquarePattern){
        list.add((ItemSquarePattern)pattern);
      }
      
      if(pattern instanceof NumberPattern){
        list.add((NumberPattern)pattern);
      }
      
      if(pattern instanceof StringPattern){
        list.add((StringPattern)pattern);
      }
    }
    
    return list.toLogoList();
  }
  
}
