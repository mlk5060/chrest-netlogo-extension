package Pattern.ItemSquare;

import jchrest.lib.ItemSquarePattern;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 * Returns the result of invoking {@link jchrest.lib.ItemSquarePattern}.
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liv.ac.uk>
 */
public class New extends DefaultReporter{
    
    @Override
    public Syntax getSyntax(){
      return Syntax.reporterSyntax(
        new int[] {
          Syntax.StringType(), //Item token
          Syntax.NumberType(), //Column coordinate for item
          Syntax.NumberType() //Row coordinate for item
        }, 
        Syntax.WildcardType() 
      );
    }
    
    @Override
    public Object report(Argument[] args, Context context) throws ExtensionException, LogoException{
      return new ItemSquarePattern(args[0].getString(), args[1].getIntValue(), args[2].getIntValue());
    }
}
