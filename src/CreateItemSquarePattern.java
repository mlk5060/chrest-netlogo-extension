import jchrest.lib.ItemSquarePattern;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 * Enables a turtle to create an "ItemOnSquare" object and return this object's
 * string representation so that it can be used in a Netlogo model.
 * 
 * Three parameters must be passed when the Netlogo extension primitive that 
 * invokes this class is used in a Netlogo model:
 * 
 * Param #      Data Type       Description
 * -------      ---------       -----------
 * 1            String          A token representing the item that the 
 *                              "ItemSquarePattern" to be constructed and 
 *                              returned is concerned with  e.g. the token "T" 
 *                              may represent a turtle.
 * 2            Integer         The x-coordinate where the item can be found.  
 *                              This value may be either an absolute 
 *                              x-coordinate or the number of patches east/west
 *                              relative to the calling turtle of the item.
 * 3            Integer         The y-coordinate where the item can be found.  
 *                              This value may be either an absolute 
 *                              y-coordinate or the number of patches 
 *                              north/south relative to the calling turtle of 
 *                              the item.
 * 
 * The Netlogo extension primitive that invokes this class returns the following 
 * when it is used in a Netlogo model:
 * 
 * Data Type Returned     Description
 * ------------------     -----------
 * String                 The string version of the ItemSquarePattern generated.
 * 
 * @author Martyn Lloyd-Kelly <mlk5060@liverpool.ac.uk>
 */
public class CreateItemSquarePattern extends DefaultReporter{
    
    @Override
    public Syntax getSyntax(){
        return Syntax.reporterSyntax( new int[] {Syntax.StringType(), Syntax.NumberType(), Syntax.NumberType()}, Syntax.StringType() );
    }
    
    @Override
    public Object report(Argument[] args, Context context) throws ExtensionException, LogoException{
        return new ItemSquarePattern(args[0].getString(), args[1].getIntValue(), args[2].getIntValue()).toString();
    }
}
