package org.nlogo.extensions.chrest.architecture.chrest;

import java.util.Iterator;
import jchrest.architecture.Node;
import jchrest.lib.Modality;
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
public class GetStm extends DefaultReporter {
  
  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(
      new int[]{
        Syntax.WildcardType()
      },
      Syntax.WildcardType()
    );
  }

  /**
   * 
   * @param args See parameter descriptions for {@link 
   * jchrest.architecture.Chrest#getStm(jchrest.lib.Modality)} (a {@link 
   * jchrest.lib.Modality} can be retrieved using {@link 
   * org.nlogo.extensions.chrest.lib.modality.Values#report(
   * org.nlogo.api.Argument[], org.nlogo.api.Context)}).
   * @param context
   * 
   * @return The result of invoking {@link jchrest.architecture.Chrest#getStm(
   * jchrest.lib.Modality)} in context of the calling {@link 
   * org.nlogo.agent.Agent Agent's} {@link jchrest.architecture.Chrest} 
   * instance.
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    return ChrestExtension.getTurtlesChrestInstance(context).getStm((Modality)args[0].get());
  }
  
}
