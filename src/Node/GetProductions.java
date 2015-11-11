package Node;

import Shared.BaseExtensionVariablesAndMethods;
import java.util.HashMap;
import java.util.Map;
import jchrest.architecture.Node;
import jchrest.lib.ListPattern;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.LogoListBuilder;
import org.nlogo.api.Syntax;

/**
 * Reports the result of invoking {@link 
 * jchrest.architecture.Node#getProductions()}.
 * 
 * If the {@link jchrest.architecture.Node} passed has no productions, an empty 
 * {@link org.nlogo.api.LogoList} is reported.
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liv.ac.uk>
 */
public class GetProductions extends DefaultReporter {

  @Override
  public Syntax getSyntax() {
    return Syntax.reporterSyntax(
      new int[]{
        Syntax.WildcardType() //Node
      }, 
      Syntax.ListType());
  }

  @Override
  public Object report(Argument args[], Context context) throws ExtensionException, LogoException {
    LogoListBuilder productions = new LogoListBuilder();

    HashMap<Node,Double> links = ((Node)args[0].get()).getProductions();
    for(Map.Entry<Node, Double> link : links.entrySet()) {
      LogoListBuilder production = new LogoListBuilder();
      production.add(link.getKey());
      production.add(link.getValue());
      productions.add(production.toLogoList());
    }

    return productions.toLogoList();
  }
}
