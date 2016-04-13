package lib.ListPattern;

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
public class Remove extends DefaultReporter {

  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(
      new int[]{
        Syntax.WildcardType(),
        Syntax.WildcardType()
      },
      Syntax.WildcardType()
    );
  }
  
  /**
   * 
   * @param args The first parameter should be the {@link 
   * jchrest.lib.ListPattern} to remove the {@link jchrest.lib.ListPattern} 
   * specified as the second parameter from.
   * @param context
   * 
   * @return The result of invoking {@link jchrest.lib.ListPattern#remove(
   * jchrest.lib.ListPattern)}.
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    return ((ListPattern)args[0].get()).remove((ListPattern)args[1].get());
  }
  
}
