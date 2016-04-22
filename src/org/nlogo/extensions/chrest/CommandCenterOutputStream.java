/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nlogo.extensions.chrest;

import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.nlogo.api.LogoException;
import org.nlogo.nvm.ExtensionContext;
import org.nlogo.nvm.Workspace;

/**
 *
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class CommandCenterOutputStream extends OutputStream {
  private final ExtensionContext _context;
  private final Workspace _workspace;
  
  public CommandCenterOutputStream(ExtensionContext context){
    this._context = context;
    this._workspace = this._context.workspace();
  }

  @Override
  public void write(int b) throws IOException {
    try {
      this._workspace.outputObject(String.valueOf((char)b), null, false, false, Workspace.OutputDestination.NORMAL);
    } catch (LogoException ex) {
      Logger.getLogger(CommandCenterOutputStream.class.getName()).log(Level.SEVERE, "", ex);
    }
  }
  
}
