package org.nlogo.extensions.chrest;

import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.nlogo.api.File;
import org.nlogo.api.LogoException;
import org.nlogo.nvm.EngineException;
import org.nlogo.nvm.ExtensionContext;
import org.nlogo.nvm.FileManager;
import org.nlogo.nvm.Workspace;
import org.nlogo.nvm.Workspace.OutputDestination;

/**
 *
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class ExtensionOutputStream extends OutputStream {
  private final ExtensionContext _context;
  private final Workspace _workspace;
  private final File _modelFile;
  private final FileManager _fileManager;
  
  public ExtensionOutputStream(ExtensionContext context){
    this._context = context;
    this._workspace = this._context.workspace();
    this._fileManager = this._workspace.fileManager();
    this._modelFile = this._fileManager.currentFile();
  }

  @Override
  public void write(int b) throws IOException {
    try {
      if(this._modelFile != null){
        try{
          this._fileManager.ensureMode(org.nlogo.api.FileModeJ.APPEND());
        }
        catch(IOException ex){
          throw new EngineException(this._context.nvmContext(), ex.getMessage());
        }
      }
      this._workspace.outputObject(String.valueOf(
        (char)b), //Output
        null, //Owner
        false, //Append new line
        false, //Enclose output with double quotes?
        (this._modelFile == null ? //Output destination
          OutputDestination.NORMAL : //Command center
          OutputDestination.FILE //File that's "open" in context of current model
        )
      );
      
    } catch (LogoException ex) {
      Logger.getLogger(ExtensionOutputStream.class.getName()).log(Level.SEVERE, "", ex);
    }
  }
  
}
