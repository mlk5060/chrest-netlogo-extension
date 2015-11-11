package Chrest;

import Shared.BaseExtensionVariablesAndMethods;
import java.util.Iterator;
import jchrest.lib.ListPattern;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultCommand;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.LogoList;
import org.nlogo.api.Syntax;

/**
 * Invokes {@link jchrest.architecture.Chrest#reinforceProduction(
 * jchrest.lib.ListPattern, jchrest.lib.ListPattern, java.lang.Double[], int) 
 * for the calling turtle.
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liv.ac.uk>
 */
public class ReinforceProduction extends DefaultCommand {
  
  @Override
  public Syntax getSyntax() {
    return Syntax.commandSyntax(new int[]{
      Syntax.WildcardType(), //The origin of the production.
      Syntax.WildcardType(), //The terminus of the production.
      Syntax.ListType(),  //Variables required by the reinforcement learning 
                          //theory used by the calling turtle's CHREST 
                          //instance to reinforce a production.
      Syntax.NumberType() //The current Netlogo model time (in milliseconds).
    });
  }

  @Override
  public void perform(Argument[] args, Context context) throws ExtensionException, LogoException {
    ListPattern visualListPattern = (ListPattern)args[0].get();
    ListPattern actionListPattern = (ListPattern)args[1].get();
    LogoList variables = args[2].getList(); 
    int time = args[3].getIntValue();

    Iterator variablesPassed = variables.iterator();
    int variableCount = 1;
    while(variablesPassed.hasNext()){
      String variable = variablesPassed.next().toString();
      if(!variable.matches("-?[0-9]+\\.[0-9]+")){
        throw new ExtensionException("Element " + variableCount + "(" + variable + ") in the list passed to this primitive is not a 'Double' object." );
      }
    }
    Double[] variablesToPass = variables.toArray(new Double[variables.size()]);

    BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(context)
      .reinforceProduction(visualListPattern, actionListPattern, variablesToPass, time);
  }
  
}
