package Chrest;

import Shared.BaseExtensionVariablesAndMethods;
import jchrest.architecture.Chrest;
import jchrest.lib.Scene;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultCommand;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 * Returns the result of invoking {@link 
 * jchrest.architecture.Chrest#learnScene(jchrest.lib.Scene, int, int)} for the
 * calling turtle.
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class LearnScene extends DefaultCommand {
  
  @Override
  public Syntax getSyntax(){
    return Syntax.commandSyntax(
      new int[]{
        Syntax.WildcardType(), //Scene to learn from.
        Syntax.NumberType(),  //Number of fixations to make on Scene.
        Syntax.NumberType() //The current Netlogo model time (in milliseconds).
      }
    );
  }

  @Override
  public void perform(Argument[] args, Context cntxt) throws ExtensionException, LogoException {
    int numberFixations = args[1].getIntValue();
    if(numberFixations > 0){
      Scene scene = (Scene)args[0].get();
      Chrest turtlesChrestInstance = BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(cntxt);

      //Set the field of view in the scene to the maximum value when the
      //current scene's width and height is compared.  This value should
      //then have 1 subtracted (this is equivalent to the x/y cor that the
      //calling agent is located on, giving an even number) and then 
      //divide by 2.  This allows for unequal width and heights to be 
      //dealt with easily.
      turtlesChrestInstance.getPerceiver().setFieldOfView( (Math.max(scene.getHeight(), scene.getWidth()) - 1) / 2);
      turtlesChrestInstance.learnScene(scene, numberFixations, args[2].getIntValue());
    }
    else{
      throw new ExtensionException("The number of fixations specified for turtle with who " 
        + BaseExtensionVariablesAndMethods.getAgent(cntxt).id + " is less than or equal to 0.");
    }
  }
}
