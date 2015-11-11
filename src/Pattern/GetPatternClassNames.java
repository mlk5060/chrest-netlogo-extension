package Pattern;

import java.util.Set;
import jchrest.lib.PrimitivePattern;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.LogoListBuilder;
import org.nlogo.api.Syntax;
import org.reflections.Reflections;

/**
 * Gets classes in the {@link jchrest.lib} package that extend {@link 
 * jchrest.lib.PrimitivePattern} and returns the result of invoking {@link 
 * java.lang.Class#getSimpleName()} on these classes and packaging the results
 * into a {@link org.nlogo.api.LogoList}.
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class GetPatternClassNames extends DefaultReporter {
  
  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(Syntax.ListType());
  }

  @Override
  public Object report(Argument[] argmnts, Context cntxt) throws ExtensionException, LogoException {
    LogoListBuilder classesThatExtendPrimitivePatternList = new LogoListBuilder();
    
    Set<Class<? extends PrimitivePattern>> classesThatExtendPrimitivePattern = new Reflections("jchrest.lib").getSubTypesOf(PrimitivePattern.class);
    for(Class<? extends PrimitivePattern> classThatExtendsPrimitivePattern : classesThatExtendPrimitivePattern){
      classesThatExtendPrimitivePatternList.add(classThatExtendsPrimitivePattern.getSimpleName());
    }
    
    return classesThatExtendPrimitivePatternList.toLogoList();
  }
  
}
