package VisualSpatialField;

import Shared.BaseExtensionVariablesAndMethods;
import jchrest.lib.Scene;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultCommand;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 * Invokes {@link jchrest.architecture.Chrest#createNewVisualSpatialField(
 * jchrest.lib.Scene, int, int, int, int, int, int, int, int, boolean, boolean)}
 * for the calling turtle at the time specified.              
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class New extends DefaultCommand {
  
  @Override
  public Syntax getSyntax(){
    return Syntax.commandSyntax(new int[]{ 
      Syntax.WildcardType(),  //Scene to encode
      Syntax.NumberType(),    //Time to encode objects
      Syntax.NumberType(),    //Time to encode empty squares
      Syntax.NumberType(),    //Time taken to access visual-spatial field
      Syntax.NumberType(),    //Time taken to move an object on visual-spatial 
                              //field
      Syntax.NumberType(),    //Lifespan for recognised objects
      Syntax.NumberType(),    //Lifespan for unrecognised objects
      Syntax.NumberType(),    //Number of fixations to make when scanning scene 
                              //to encode
      Syntax.NumberType(),    //Current time in the Netlogo model
      Syntax.BooleanType(),   //Encode ghost objects?
      Syntax.BooleanType()    //Turn on debugging from VisualSpatialField 
                              //constructor?
    });
  }
  
  @Override
  public void perform(Argument args[], Context context) throws ExtensionException, LogoException {
    BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(context).createNewVisualSpatialField(
      (Scene)args[0].get(), 
      args[1].getIntValue(),
      args[2].getIntValue(),
      args[3].getIntValue(),
      args[4].getIntValue(),
      args[5].getIntValue(),
      args[6].getIntValue(),
      args[7].getIntValue(),
      args[8].getIntValue(),
      args[9].getBooleanValue(),
      args[10].getBooleanValue()
    );
  }
}
