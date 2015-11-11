package Chrest;

import Shared.BaseExtensionVariablesAndMethods;
import java.util.Iterator;
import jchrest.architecture.Node;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.LogoListBuilder;
import org.nlogo.api.Syntax;

/**
 * Returns the result of invoking {@link 
 * jchrest.architecture.Chrest#getActionStm()} for the calling turtle.
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liv.ac.uk>
 */
public class GetActionStm extends DefaultReporter{
  @Override
  public Syntax getSyntax() {
    return Syntax.reporterSyntax(Syntax.ListType());
  }

  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    LogoListBuilder actionStmList = new LogoListBuilder();
    Iterator<Node> actionStm = BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(context).getActionStm().iterator();
    
    while(actionStm.hasNext()){
      actionStmList.add(actionStm.next());
    }
    
    return actionStmList.toLogoList().reverse();
  }
}
