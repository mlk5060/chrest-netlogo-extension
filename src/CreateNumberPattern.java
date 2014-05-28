import jchrest.lib.NumberPattern;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 * Enables a turtle to create a "NumberPattern" object and return this object's
 * string representation so that it can be used in a Netlogo model.
 * 
 * One parameter must be passed when the Netlogo extension primitive that 
 * invokes this class is used in a Netlogo model:
 * 
 * Param #      Data Type       Description
 * -------      ---------       -----------
 * 1            Integer         The number to be used to create the 
 *                              NumberPattern.
 * 
 * The Netlogo extension primitive that invokes this class returns the following 
 * when it is used in a Netlogo model:
 * 
 * Data Type Returned     Description
 * ------------------     -----------
 * String                 The string version of the NumberPattern generated.
 * 
 * @author Martyn Lloyd-Kelly <mlk5060@liverpool.ac.uk>
 */
public class CreateNumberPattern extends DefaultReporter{
    
    @Override
    public Syntax getSyntax(){
      return Syntax.reporterSyntax( new int[] {Syntax.NumberType()}, Syntax.StringType() );
    }
    
    @Override
    public Object report(Argument[] args, Context context) throws ExtensionException, LogoException{
      return NumberPattern.create(args[0].getIntValue()).toString();
    }
}
