package Chrest;

import Shared.BaseExtensionVariablesAndMethods;
import jchrest.lib.Scene;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 * Returns the result of invoking {@link 
 * jchrest.architecture.Chrest#scanScene(jchrest.lib.Scene, int, boolean, int, 
 * boolean) for the calling turtle.
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class ScanScene extends DefaultReporter{
  
  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(
      new int[]{
        Syntax.WildcardType(), //Scene to scan
        Syntax.NumberType(), //Number fixations
        Syntax.BooleanType(), //Clear STM boefore scan? (true for yes)
        Syntax.NumberType(), //Current domain time
        Syntax.BooleanType() //Enable debugging for function? 
      }, 
      Syntax.WildcardType()
    );
  }

  @Override
  public Object report(Argument[] args, Context cntxt) throws ExtensionException, LogoException {
    return BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(cntxt).scanScene(
      (Scene)args[0].get(),
      args[1].getIntValue(),
      args[2].getBoolean(),
      args[3].getIntValue(),
      args[4].getBoolean()
    );
  }
}
