/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nlogo.extensions.chrest.ModelTests.Tileworld.ReinforceProductions;

import jchrest.lib.ListPattern;
import jchrest.lib.Modality;
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
public class ReinforceLowProductionMatchTest extends DefaultReporter {
  
  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(
      new int[]{
        Syntax.WildcardType(),
        Syntax.WildcardType()
      },
      Syntax.WildcardType()
    );
  }

  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    ListPattern visual = new ListPattern(Modality.VISUAL);
    visual.add( ((ListPattern)args[0].get()).getItem(0) );
    
    ListPattern action = new ListPattern(Modality.ACTION);
    action.add( ((ListPattern)args[1].get()).getItem(0) );
    
    return ReinforceProductionMatchSetup.invoke(
      ChrestExtension.getTurtlesChrestInstance(context),
      visual,
      action
    );
  }
}
