import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jchrest.gui.ChrestView;
import org.nlogo.api.AgentException;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultCommand;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 * Saves the calling turtle's CHREST LTM image to the absolute file path 
 * specified by the string parameter passed to the extension primitive that 
 * invokes this class.  The file path should not include a file and file format,
 * only the path to the directory where the file should be stored i.e. 
 * "/home/ltm" not "/home/ltm/image.png".
 * 
 * The file path specified <b>must</b> be absolute, if not, the class will throw
 * an exception.  If any directories or the file specified in the file path do 
 * not exist, they are created. 
 * 
 * One parameter must be passed when the Netlogo extension primitive that 
 * invokes this class is used in a Netlogo model:
 * 
 * Param #      Data Type       Description
 * -------      ---------       -----------
 * 1            String          The absolute file path where the calling 
 *                              turtle's LTM CHREST image should be stored.
 * 
 * @author Martyn Lloyd-Kelly <mlk5060@liverpool.ac.uk>
 */
public class SaveLtmNetworkImage extends DefaultCommand{
  
  @Override
  public Syntax getSyntax(){
    return Syntax.commandSyntax(new int[] {Syntax.StringType()});
  }
  
  @Override
  public void perform(Argument[] args, Context context) throws ExtensionException, LogoException{
    try {
      if(BaseExtensionVariablesAndMethods.agentHasChrestInstance(context)){
        
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
            Logger.getLogger(SaveLtmNetworkImage.class.getName()).log(Level.SEVERE, null, ex);
          }
        }
        
        //Create and save the LTM graphic to the file specified.
        try {
          new ChrestView(BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(context)).saveLongTermMemory(file);
        } catch (AgentException ex) {
          Logger.getLogger(SaveLtmNetworkImage.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
      }
    } catch (AgentException ex) {
      Logger.getLogger(SaveLtmNetworkImage.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
    }
  }
}
