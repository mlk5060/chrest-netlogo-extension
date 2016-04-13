package org.nlogo.extensions.chrest.architecture.node;

import java.util.HashMap;
import java.util.Map;
import jchrest.architecture.Node;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.LogoListBuilder;
import org.nlogo.api.Syntax;

/**
 * Reports 
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
        Syntax.WildcardType(),
        Syntax.NumberType()
      }, 
      Syntax.ListType());
  }

  /**
   * 
   * @param args The first parameter should be the {@link 
   * jchrest.architecture.Node} that {@link 
   * jchrest.architecture.Node#getImage(int)} will be invoked in context of. For
   * other parameters, see {@link jchrest.architecture.Node#getProductions(int)}.
   * @param context
   * 
   * @return The result of invoking {@link 
   * jchrest.architecture.Node#getProductions()} for the {@link 
   * jchrest.architecture.Node} passed as a parameter to this primitive.
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Object report(Argument args[], Context context) throws ExtensionException, LogoException {
    LogoListBuilder productions = new LogoListBuilder();

    HashMap<Node,Double> links = ((Node)args[0].get()).getProductions(args[1].getIntValue());
    for(Map.Entry<Node, Double> link : links.entrySet()) {
      LogoListBuilder production = new LogoListBuilder();
      production.add(link.getKey());
      production.add(link.getValue());
      productions.add(production.toLogoList());
    }

    return productions.toLogoList();
  }
}
