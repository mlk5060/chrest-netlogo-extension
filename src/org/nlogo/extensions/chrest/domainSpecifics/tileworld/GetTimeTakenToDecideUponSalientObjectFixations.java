package org.nlogo.extensions.chrest.domainSpecifics.tileworld;

import jchrest.domainSpecifics.tileworld.TileworldDomain;
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
public class GetTimeTakenToDecideUponSalientObjectFixations extends DefaultReporter {
  
  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(Syntax.NumberType());
  }
  
  /**
   * 
   * @param args
   * @param context
   * 
   * @return The result of invoking {@link 
   * jchrest.domainSpecifics.tileworld.TileworldDomain#getTimeTakenToDecideUponSalientObjectFixations()}
   * in context of invoking {@link jchrest.architecture.Chrest#getDomainSpecifics()}
   * for the calling turtle's {@link jchrest.architecture.Chrest} model.
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    return ((TileworldDomain)ChrestExtension.getTurtlesChrestInstance(context).getDomainSpecifics()).getTimeTakenToDecideUponSalientObjectFixations();
  }
  
}
