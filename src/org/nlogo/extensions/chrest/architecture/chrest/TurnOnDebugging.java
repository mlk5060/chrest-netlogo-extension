package org.nlogo.extensions.chrest.architecture.chrest;

import java.io.PrintStream;
import jchrest.architecture.Chrest;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultCommand;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.extensions.chrest.ChrestExtension;
import org.nlogo.extensions.chrest.ExtensionOutputStream;

/**
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class TurnOnDebugging extends DefaultCommand {
  
  @Override
  public void perform(Argument[] args, Context context) throws ExtensionException, LogoException {
    Chrest chrest = ChrestExtension.getTurtlesChrestInstance(context);
    if (context instanceof org.nlogo.nvm.ExtensionContext) {
      org.nlogo.nvm.ExtensionContext extContext = ((org.nlogo.nvm.ExtensionContext) context);
      ExtensionOutputStream extensionOutputStream = new ExtensionOutputStream(extContext);
      chrest.setDebugPrintStream(new PrintStream(extensionOutputStream));
      ChrestExtension.getTurtlesChrestInstance(context).turnOnDebugging();
    } else {
      throw new ExtensionException("Context is not an instance of org.nlogo.nvm.ExtensionContext");
    }
  }
  
}
