package architecture.Chrest;

import classManager.ChrestExtension;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jchrest.gui.ChrestView;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultCommand;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 * @author Martyn Lloyd-Kelly <martynlk@liv.ac.uk>
 */
public class SaveLtmNetworkImage extends DefaultCommand{
  
  @Override
  public Syntax getSyntax(){
    return Syntax.commandSyntax(
      new int[] {
        Syntax.StringType(), 
        Syntax.NumberType()
      }
    );
  }
  
  /**
   * Invokes {@link jchrest.gui.ChrestView#saveLongTermMemory(java.io.File)} in 
   * context of the calling turtle's {@link jchrest.architecture.Chrest} 
   * instance.
   *    
   * @param args The first parameter should be an absolute path to where the 
   * file containing the LTM network image should be stored.  The path should 
   * not include a filename or a file format, i.e. "/home/ltm" not 
   * "/home/ltm/image.png".  Any directories specified that do not exist will be
   * created.
   * <p>
   * The second parameter should be the time that the calling turtle's LTM 
   * is to be visualised at.
   * @param context
   * 
   * @throws ExtensionException If the path specified contains a file format or
   * is not an absolute path.
   * 
   * @throws LogoException 
   */
  @Override
  public void perform(Argument[] args, Context context) throws ExtensionException, LogoException{
    
    //Check to see if the user has attempted to specify a format for the 
    //file that is to hold the calling turtle's CHREST LTM image.  If they 
    //have, throw a Netlogo extension exception to inform the user of this.
    String userSpecifiedFilePath = args[0].getString();
    if(userSpecifiedFilePath.contains(".")){
      throw new ExtensionException("The file path that a turtle's CHREST LTM image should be saved to should not specify a file format.");
    }

    //Check to see if the user has included a directory seperator at the 
    //end of the string passed in.  If so, do not include one when creating
    //the file that is to store the turtle's CHREST LTM image otherwise, do
    //include one.
    if(userSpecifiedFilePath.endsWith(File.separator)){
      userSpecifiedFilePath += "turtle" + context.getAgent().id() + ".png";
    }
    else{
      userSpecifiedFilePath += File.separator + "turtle" + context.getAgent().id() + ".png";
    }

    //Create the new file object.
    File file = new File(userSpecifiedFilePath);

    //Check to see if file path specified is an absolute path name, if not
    //a Netlogo extension exception should be thrown since the user may not
    //know that a non-absolute string will mean that the file is saved 
    //relative to their Netlogo folder.
    if(!file.isAbsolute()){
      throw new ExtensionException("File path specified to save LTM graphic to is not an absolute file path") ;
    }

    //Check to see if the file specified exists, if it doesn't, create the 
    //file and any missing parent directories.
    if(!file.exists()){
      file.getParentFile().mkdirs();

      try {
        file.createNewFile();
      } catch (IOException ex) {
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
      }
    }
        
    //Create and save the LTM graphic to the file specified.
    new ChrestView(ChrestExtension.getTurtlesChrestInstance(context), args[1].getIntValue()).saveLongTermMemory(file);
  }
}
