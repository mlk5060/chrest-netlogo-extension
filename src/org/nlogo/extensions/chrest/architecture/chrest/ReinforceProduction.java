package org.nlogo.extensions.chrest.architecture.chrest;

import org.nlogo.extensions.chrest.ChrestExtension;
import java.util.Iterator;
import jchrest.architecture.Node;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.LogoList;
import org.nlogo.api.Syntax;

/**
 * @author Martyn Lloyd-Kelly <martynlk@liv.ac.uk>
 */
public class ReinforceProduction extends DefaultReporter {
  
  @Override
  public Syntax getSyntax() {
    return Syntax.reporterSyntax(
      new int[]{
        Syntax.WildcardType(),
        Syntax.WildcardType(),
        Syntax.ListType(),
        Syntax.NumberType()
      },
      Syntax.BooleanType()
    );
  }

  /**
   * Invokes {@link jchrest.architecture.Chrest#reinforceProduction(jchrest.architecture.Node, 
   * jchrest.architecture.Node, java.lang.Double[], int)} in context of the 
   * calling turtle's {@link jchrest.architecture.Chrest} instance.
   * 
   * @param args See parameters for {@link 
   * jchrest.architecture.Chrest#reinforceProduction(jchrest.architecture.Node, 
   * jchrest.architecture.Node, java.lang.Double[], int)}
   * @param context
   * 
   * @ return The result of {@link 
   * jchrest.architecture.Chrest#reinforceProduction(jchrest.architecture.Node, 
   * jchrest.architecture.Node, java.lang.Double[], int) 
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    Node visualNode = (Node)args[0].get();
    Node actionNode = (Node)args[1].get();
    LogoList reinforcementVariables = args[2].getList(); 
    int time = args[3].getIntValue();

    Iterator reinforcementVariablesIterator = reinforcementVariables.iterator();
    int variableCount = 1;
    while(reinforcementVariablesIterator.hasNext()){
      String variable = reinforcementVariablesIterator.next().toString();
      if(!variable.matches("-?[0-9]+\\.[0-9]+")){
        throw new ExtensionException("Element " + variableCount + "(" + variable + ") in the list passed to this primitive is not a 'Double' object." );
      }
    }
    Double[] variablesToPass = reinforcementVariables.toArray(new Double[reinforcementVariables.size()]);

    return ChrestExtension.getTurtlesChrestInstance(context).reinforceProduction(
      visualNode, 
      actionNode, 
      variablesToPass, 
      time
    );
  }
  
}
