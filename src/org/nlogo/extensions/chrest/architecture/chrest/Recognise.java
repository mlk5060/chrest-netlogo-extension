package org.nlogo.extensions.chrest.architecture.chrest;

import jchrest.architecture.Node;
import jchrest.lib.ListPattern;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;
import org.nlogo.extensions.chrest.ChrestExtension;

/**
 *
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class Recognise extends DefaultReporter {

  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(
      new int[]{
        Syntax.WildcardType(),
        Syntax.NumberType(),
        Syntax.BooleanType()
      },
      Syntax.WildcardType()
    );
  }
  
  /**
   * 
   * @param args See parameters for {@link 
   * jchrest.architecture.Chrest#recognise(jchrest.lib.ListPattern, 
   * java.lang.Integer, java.lang.Boolean)}.
   * @param context
   * 
   * @return The result of invoking {@link 
   * jchrest.architecture.Chrest#recognise(jchrest.lib.ListPattern, 
   * java.lang.Integer, java.lang.Boolean)} with the parameters passed to this
   * primitive.
   * <p>
   * If {@link jchrest.architecture.Chrest#recognise(jchrest.lib.ListPattern, 
   * java.lang.Integer, java.lang.Boolean)} returns {@code null}, an empty
   * {@link java.lang.String} is returned.
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    Node recognitionResult = ChrestExtension.getTurtlesChrestInstance(context).recognise((ListPattern)args[0].get(), args[1].getIntValue(), args[2].getBooleanValue());
    return (recognitionResult == null ? "" : recognitionResult);
  }
  
}
