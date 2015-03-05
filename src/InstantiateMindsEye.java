import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.nlogo.api.AgentException;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultCommand;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.LogoList;
import org.nlogo.api.Syntax;

/**
 * Instantiates a mind's eye for the calling turtle and returns a boolean value
 * indicating whether the mind's eye has been instantiated for the calling 
 * turtle (true) or not (false).
 * 
 * Six parameters must be passed when the Netlogo extension primitive that 
 * invokes this class is used in a Netlogo model:
 * 
 * Param #      Data Type       Description
 * -------      ---------       -----------
 * 1            List            A list of strings denoting the patches that the
 *                              calling turtle can see and the turtles on them 
 *                              (uniquely identified) in the following format: 
 *                              "turtleId;xcor;ycor".  For patches that contain
 *                              more than 1 turtle, separate their identifiers
 *                              with comments: "turtle1Id,turtle2Id;xcor;ycor".
 *                              For patches that contain no turtles, pass 
 *                              nothing as an identifier: ";xcor;ycor".
 * 2            Number          The amount of time (in milliseconds) that the
 *                              mind's eye should exist for after instantiation 
 *                              or interaction (mind's eye lifespan).
 * 3            Number          The amount of time (in milliseconds) that it
 *                              takes to place a single object in the mind's eye
 *                              during instantiation of the visual-spatial 
 *                              field.
 * 4            Number          The amount of time (in milliseconds) that the 
 *                              calling turtle should take when accessing the
 *                              mind's eye.
 * 5            Number          The amount of time (in milliseconds) that the 
 *                              calling turtle should take when moving an object
 *                              in the mind's eye.
 * 6            Number          The current time in the Netlogo model that the
 *                              call to this extension primitive was made (in 
 *                              milliseconds).
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class InstantiateMindsEye extends DefaultCommand {
  
  @Override
  public Syntax getSyntax(){
    return Syntax.commandSyntax(new int[] { Syntax.ListType(), Syntax.NumberType(), Syntax.NumberType(), Syntax.NumberType(), Syntax.NumberType(), Syntax.NumberType()});
  }
  
  @Override
  public void perform(Argument args[], Context context) throws ExtensionException, LogoException {
    //TODO: this needs a radical overhaul since the extension allows for Scence
    //instances to be created so a copy of the perceiver could be used to 
    //instantiate the Minds Eye.
//    try {
//      
//      //Check that all elements of the list passed are instances of String.  If
//      //not, throw an error, if so, convert the list into a java String array 
//      //and pass it to the relevant CHREST function to instantiate a mind's eye.
//      LogoList vision = args[0].getList();
//      Iterator<Object> visionIterator = vision.iterator();
//      if(visionIterator.hasNext()){
//        Object visualPart = visionIterator.next();
//        if(visualPart instanceof String == false){
//          throw new ExtensionException("Part of the vision specified to instantiate the mind's eye for agent " + BaseExtensionVariablesAndMethods.getAgent(context).id + " is not a string: '" + visualPart.toString() + "'.");
//        }
//      }
//      String[] visionStringArray = {};
//      BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(context).createNewMindsEye(vision.toArray(visionStringArray), args[1].getIntValue(), args[2].getIntValue(), args[3].getIntValue(), args[4].getIntValue(), args[5].getIntValue() );
//    } catch (AgentException ex) {
//      Logger.getLogger(InstantiateMindsEye.class.getName()).log(Level.SEVERE,"", ex);
//    }
  }
}
