package Pattern.List;

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
 * Returns the result of invoking {@link 
 * jchrest.lib.ListPattern#ListPattern(jchrest.lib.Modality)}.
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class New extends DefaultReporter {
  
  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(
      new int[]{
        Syntax.StringType(), //Modality of the ListPattern to create.
        Syntax.ListType() //A LogoList of jchrest.lib.Patterns to create the 
                          //ListPattern with.
      }, 
      Syntax.WildcardType()
    );
  }

  @Override
  public Object report(Argument[] args, Context cntxt) throws ExtensionException, LogoException {
    
    ListPattern listPattern = new ListPattern(Modality.valueOf(args[0].getString().toUpperCase()));
    Iterator<Object> patternsToAddToListPattern = args[1].getList().iterator();
    
    while(patternsToAddToListPattern.hasNext()){
      Object patternToAdd = patternsToAddToListPattern.next();
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
        throw new ExtensionException("CreateListPattern doesn't recognise pattern type");
      }
    }
    
    return listPattern;
  }
  
}
