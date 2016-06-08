package org.nlogo.extensions.chrest.architecture.chrest;

import jchrest.architecture.Node;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.LogoListBuilder;
import org.nlogo.api.Syntax;
import org.nlogo.extensions.chrest.ChrestExtension;

/**
 *
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class GenerateActionUsingVisualPatternRecognition extends DefaultReporter{

  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(new int[]{Syntax.NumberType()}, Syntax.ListType());
  }
  
  /**
   * 
   * @param argmnts See parameter declarations for {@link 
   * jchrest.architecture.Chrest#generateActionUsingVisualPatternRecognition(int)}.
   * @param context
   * 
   * @return The result of {@link 
   * jchrest.architecture.Chrest#generateActionUsingVisualPatternRecognition(int)}.
   * Unless both elements of the {@link java.util.Arrays Array} returned are 
   * not {@code null}, an empty {@link org.nlogo.api.LogoList} is returned.  
   * Otherwise, the {@link org.nlogo.api.LogoList} returned will map the 
   * contents of the {@link java.util.Arrays Array} returned to the elements of
   * the {@link org.nlogo.api.LogoList} returned.
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    Node[] production = ChrestExtension.getTurtlesChrestInstance(context).generateActionUsingVisualPatternRecognition(args[0].getIntValue());
    LogoListBuilder productionAsList = new LogoListBuilder();
    
    if(production[0] != null && production[1] != null){
      productionAsList.add(production[0]);
      productionAsList.add(production[1]);
    }
    
    return productionAsList.toLogoList();
  }
  
}
