package lib.Pattern;

import jchrest.lib.NumberPattern;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 * @author Martyn Lloyd-Kelly <martynlk@liv.ac.uk>
 */
public class CreateNumberPattern extends DefaultReporter{
    
  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(
      new int[] {
        Syntax.NumberType() //The number to use to create the NumberPattern
      }, 
      Syntax.StringType() 
    );
  }

  /**
   * 
   * @param args See parameters for {@link 
   * jchrest.lib.NumberPattern#create(int)}.
   * @param context
   * 
   * @return The result of {@link jchrest.lib.NumberPattern#create(int)}.
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException{
    return NumberPattern.create(args[0].getIntValue());
  }
}
