package lib.ItemSquarePattern;

import jchrest.lib.ItemSquarePattern;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 * @author Martyn Lloyd-Kelly <martynlk@liv.ac.uk>
 */
public class New extends DefaultReporter{
    
  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(
      new int[] {
        Syntax.StringType(),
        Syntax.NumberType(),
        Syntax.NumberType()
      }, 
      Syntax.WildcardType() 
    );
  }

  /**
   * 
   * @param args See parameters for {@link 
   * jchrest.lib.ItemSquarePattern#ItemSquarePattern(java.lang.String, int, 
   * int)}.
   * @param context
   * 
   * @return The result of {@link 
   * jchrest.lib.ItemSquarePattern#ItemSquarePattern(java.lang.String, int, 
   * int)}.
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException{
    return new ItemSquarePattern(args[0].getString(), args[1].getIntValue(), args[2].getIntValue());
  }
}
