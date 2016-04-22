package org.nlogo.extensions.chrest.architecture.stm;

import java.util.List;
import jchrest.architecture.Node;
import jchrest.architecture.Stm;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.LogoListBuilder;
import org.nlogo.api.Syntax;

/**
 *
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class GetContents extends DefaultReporter{
  
  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(
      new int[]{
        Syntax.WildcardType(), 
        Syntax.NumberType()
      }, 
      Syntax.ListType()
    );
  }
  
  /**
   * 
   * @param args The first primitive should be the {@link 
   * jchrest.architecture.Stm} that {@link 
   * jchrest.architecture.Stm#getContents(int)} is to be invoked in context of.
   * For other parameters see {@link jchrest.architecture.Stm#getContents(int)}.
   * @param context
   * 
   * @return The result of invoking {@link 
   * jchrest.architecture.Stm#getContents(int)} with the parameters passed to 
   * this primitive.
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    LogoListBuilder stmAsList = new LogoListBuilder();
    List<Node> contents = ((Stm)args[0].get()).getContents(args[1].getIntValue());
    for(Node content : contents){
      stmAsList.add(content);
    }
    return stmAsList.toLogoList();
  }
  
}
