package architecture.Chrest;

import classManager.ChrestExtension;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 * Returns the result of invoking {@link 
 * jchrest.architecture.Chrest#getProductionCount()} for the calling turtle.
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liv.ac.uk>
 */
public class GetProductionCount extends DefaultReporter{
  
  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(
      new int[]{
        Syntax.NumberType()
      }, 
      Syntax.NumberType()
    );
  }
  
  /**
   * 
   * @param args See parameters for {@link 
   * jchrest.architecture.Chrest#getProductionCount(int)}.
   * @param context
   * 
   * @return The result of invoking {@link 
   * jchrest.architecture.Chrest#getProductionCount(int)} in context of the 
   * calling turtle's {@link jchrest.architecture.Chrest} instance.
   * 
   * @throws ExtensionException 
   */
  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException{
    return (double)ChrestExtension.getTurtlesChrestInstance(context).getProductionCount(args[0].getIntValue());
  }
}
