
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import jchrest.lib.Scene;
import org.nlogo.api.AgentException;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.LogoList;
import org.nlogo.api.Syntax;

/**
 * Creates and reports a new {@link jchrest.lib.Scene} instance.
 * 
 * Two parameters must be passed when the Netlogo extension primitive that 
 * invokes this class is used in a Netlogo model:
 * 
 * Param #      Data Type       Description
 * -------      ---------       -----------
 * 1            List            What the agent can see on patches as a list of 
 *                              strings formatted as 
 *                              {@link jchrest.lib.ItemSquarePattern#toString()} 
 *                              instances.  The most reliable way to pass 
 *                              correctly formatted information here is to call
 *                              the primitive from this extension that invokes 
 *                              the {@link CreateItemSquarePattern} class.
 *                              <b>NOTE</b> a decision must be made on whether 
 *                              all x/y-cor offsets will be relative OR absolute
 *                              to the location of the turtle that generates 
 *                              this scene.  Otherwise, the scene instance 
 *                              generated will produce odd behaviour when used
 *                              by CHREST.
 * 2            String          The name of the scene (first parameter used by 
 *                              {@link jchrest.lib.Scene#Scene(java.lang.String, int, int)}).
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class CreateScene extends DefaultReporter {

  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(new int[]{Syntax.ListType(), Syntax.StringType()}, Syntax.WildcardType());
  }
  
  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    
    LogoList input = args[0].getList();
    ArrayList<String[]> dataForScene = new ArrayList();

    //Since domain-specific and agent relative xcor and ycor coordinates may 
    //be specified for items in a Nelogo environment, analyse the scene passed 
    //to establish the smallest and greatest width and height (x/y-cors, 
    //respectively) so that a jchrest.lib.Scene instance can be correctly 
    //constructed.
    Integer minX = null;
    Integer maxX = null;
    Integer minY = null;
    Integer maxY = null;

    for(int i = 0; i < input.size(); i++){
      Object item = input.get(i);
      if(item instanceof String){
        String itemString = (String)item;
        
        //The itemString variable will look like: "[object-identifier xcor ycor]"
        //so the angled brackets need to be removed otherwise errors will be
        //thrown since the ycor variable below will contain "ycor]", i.e. it 
        //won't actually be a pure number.
        String[] itemDetails = itemString.replaceAll("\\[|\\]", "").split("\\s+");
        if(itemDetails.length == 3){
          Integer xcor = Integer.valueOf(itemDetails[1]);
          Integer ycor = Integer.valueOf(itemDetails[2]);

          //Set the minX value accordingly.
          if(minX == null){
            minX = xcor;
          }
          else if(xcor < minX){
            minX = xcor;
          }

          //Set the minY value accordingly.
          if(minY == null){
            minY = ycor;
          }
          else if(ycor < minY){
            minY = ycor;
          }

          //Set the maxX value accordingly.
          if(maxX == null){
            maxX = xcor;
          }
          else if(xcor > maxX){
            maxX = xcor;
          }

          //Set the maxY value accordingly.
          if(maxY == null){
            maxY = ycor;
          }
          else if(ycor > maxY){
            maxY = ycor;
          }
          
          dataForScene.add(itemDetails);
        }
        else{
          throw new ExtensionException("The information for item " + item + " in the first parameter passed to "
            + "the 'create-scene' CHREST extension by turtle " + BaseExtensionVariablesAndMethods.getAgent(context).id 
            + "contains " + itemDetails.length + " pieces of information rather than 3 (object identifier, x-cor, y-cor). "
            + "If you aren't already, pass the result of the CHREST Netlogo extension primitive that returns the "
            + "string representation of a CHREST-compatible ItemSquarePattern instance (see extension documentation), "
            + "as the first parameter to this extension primitive to reliably create a Scene instance."
          );
        }
      }
      else{
        throw new ExtensionException("The information for item " + i + " in the first parameter passed to "
          + "the 'create-scene' CHREST extension by turtle " + BaseExtensionVariablesAndMethods.getAgent(context).id
          + "is not a String.  Please rectify.");
      }
    }

    //Create a new scene instance using the max/min x/y values set above.  Add
    //1 to the result since if maxX and minX are 2 and 1 respectively, the 
    //result (1) does not take into account 2 xcors (which it should since the
    //turtle can see two "columns" in the environment, not 1).
    Scene scene = new Scene(args[1].getString(), (maxY - minY) + 1, (maxX - minX) + 1);

    //Set the domain-specific to non domain-specific (Scene coordinate) 
    //converters (Scene coordinates start at 0).
    int xcorConverter = 0 - minX;
    int ycorConverter = 0 - minY;

    //Populate the scene using info from the list passed.
    for(String[] data : dataForScene){
      scene.addItemToSquare(
        Integer.valueOf(data[1]) + xcorConverter, //col (xcor)
        Integer.valueOf(data[2]) + ycorConverter, //row (ycor)
        data[0] //item identifier
      );
    }

    return scene;
  }
}
