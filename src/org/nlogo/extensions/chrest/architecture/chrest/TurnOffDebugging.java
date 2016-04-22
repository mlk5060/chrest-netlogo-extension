package org.nlogo.extensions.chrest.architecture.chrest;

import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultCommand;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.extensions.chrest.ChrestExtension;

/**
 *
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class TurnOffDebugging extends DefaultCommand {

  @Override
  public void perform(Argument[] args, Context context) throws ExtensionException, LogoException {
    ChrestExtension.getTurtlesChrestInstance(context).turnOffDebugging();
  }
  
}
