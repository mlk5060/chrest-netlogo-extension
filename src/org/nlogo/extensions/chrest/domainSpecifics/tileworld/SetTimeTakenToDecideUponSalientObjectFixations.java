package org.nlogo.extensions.chrest.domainSpecifics.tileworld;

import jchrest.domainSpecifics.tileworld.TileworldDomain;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultCommand;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;
import org.nlogo.extensions.chrest.ChrestExtension;

/**
 *
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class SetTimeTakenToDecideUponSalientObjectFixations extends DefaultCommand {
 
  @Override
  public Syntax getSyntax(){
    return Syntax.commandSyntax(new int[]{Syntax.NumberType()});
  }
  
  /**
   * Invokes {@link 
   * jchrest.domainSpecifics.tileworld.TileworldDomain#setTimeTakenToDecideUponSalientObjectFixations(int)}
   * in context of invoking {@link jchrest.architecture.Chrest#getDomainSpecifics()}
   * for the calling turtle's {@link jchrest.architecture.Chrest} model.
   * 
   * @param args See parameter descriptions for {@link 
   * jchrest.domainSpecifics.tileworld.TileworldDomain#setTimeTakenToDecideUponSalientObjectFixations(int)}
   * @param context
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public void perform(Argument[] args, Context context) throws ExtensionException, LogoException {
    ((TileworldDomain)ChrestExtension.getTurtlesChrestInstance(context).getDomainSpecifics()).setTimeTakenToDecideUponSalientObjectFixations(args[0].getIntValue());
  }
  
}
