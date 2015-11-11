package Pattern.List;

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
 * Returns the result of packaging the {@link jchrest.lib.Pattern}s in a
 * {@link jchrest.lib.ListPattern} as a {@link org.nlogo.api.LogoList}.
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class GetAsNetlogoList extends DefaultReporter {
  
  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(
      new int[]{
        Syntax.WildcardType() //ListPattern
      },
      Syntax.ListType()
    );
  }

  @Override
  public Object report(Argument[] args, Context cntxt) throws ExtensionException, LogoException {
    LogoListBuilder list = new LogoListBuilder();
    ListPattern listPattern = (ListPattern)args[0].get();
    for(PrimitivePattern pattern : listPattern){
      list.add(pattern);
    }
    return list.toLogoList();
  }
  
}
