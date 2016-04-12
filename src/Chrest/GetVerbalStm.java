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
 * jchrest.architecture.Chrest#getVerbalStm()} for the calling turtle.
 *
 * @author Martyn Lloyd-Kelly <martynlk@liv.ac.uk>
 */
public class GetVerbalStm extends DefaultReporter {

  @Override
  public Syntax getSyntax() {
    return Syntax.reporterSyntax(Syntax.ListType());
  }

  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    LogoListBuilder verbalStmList = new LogoListBuilder();
    Iterator<Node> verbalStm = BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(context).getVerbalStm().iterator();

    while (verbalStm.hasNext()) {
      verbalStmList.add(verbalStm.next());
    }

    return verbalStmList.toLogoList().reverse();
  }
}