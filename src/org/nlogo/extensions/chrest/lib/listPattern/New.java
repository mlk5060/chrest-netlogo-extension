package org.nlogo.extensions.chrest.lib.listPattern;

import java.util.Iterator;
import jchrest.lib.ItemSquarePattern;
import jchrest.lib.ListPattern;
import jchrest.lib.Modality;
import jchrest.lib.NumberPattern;
import jchrest.lib.StringPattern;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class New extends DefaultReporter {
  
  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(
      new int[]{
        Syntax.ListType(),
        Syntax.WildcardType()
      }, 
      Syntax.WildcardType()
    );
  }

  /**
   * 
   * @param args The first parameter should be a {@link org.nlogo.api.LogoList}
   * containing {@link jchrest.lib.Pattern Patterns} that should constitute the
   * {@link jchrest.lib.ListPattern} to be constructed.
   * <p>
   * For other parameters, see {@link jchrest.lib.ListPattern#ListPattern(
   * jchrest.lib.Modality)}.
   * @param context
   * 
   * @return The result of invoking {@link jchrest.lib.ListPattern#add(
   * jchrest.lib.PrimitivePattern)} on the result of {@link 
   * jchrest.lib.ListPattern#ListPattern(jchrest.lib.Modality)} for each {@link 
   * jchrest.lib.Pattern} specified in the {@link org.nlogo.api.LogoList} passed 
   * as a parameter to this primitive.
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    
    Iterator<Object> patternsToAdd = args[0].getList().iterator();
    ListPattern listPattern = new ListPattern((Modality)args[1].get());
    
    while(patternsToAdd.hasNext()){
      Object patternToAdd = patternsToAdd.next();
      if(patternToAdd instanceof ItemSquarePattern){
        listPattern.add( (ItemSquarePattern)patternToAdd );
      } 
      else if(patternToAdd instanceof StringPattern){
        listPattern.add( (StringPattern)patternToAdd );
      }
      else if(patternToAdd instanceof NumberPattern){
        listPattern.add( (NumberPattern)patternToAdd );
      }
      else{
        throw new ExtensionException(
          "Pattern type of " + patternToAdd.toString() + "(" + 
          patternToAdd.getClass().getCanonicalName() + ") is not supported"
        );
      }
    }
    
    return listPattern;
  }
  
}
