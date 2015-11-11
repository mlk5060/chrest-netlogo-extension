package Scene;

import java.util.ArrayList;
import jchrest.lib.Scene;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.LogoList;
import org.nlogo.api.Syntax;

/**
 * Returns the result of invoking {@link jchrest.lib.Scene#Scene(
 * java.lang.String, int, int, jchrest.architecture.VisualSpatialField)} 
 * for a calling turtle.
 * 
 * The first parameter passed should be what the agent can "see" as a 2D 
 * {@link org.nlogo.api.LogoList}.  First dimension elements should contain four 
 * elements which, when combined, create the information required to encode a 
 * {@link jchrest.lib.SceneObject}:
 * 
 * <ol type="1">
 *  <li>
 *    The xcor of a patch.
 *  </li>
 *  <li>
 *    The ycor of a patch.
 *  </li>
 *  <li>
 *    The {@link org.nlogo.agent.Turtle#id} of the object on the patch specified
 *    by the xcor and ycor above.
 *  </li>
 *  <li>
 *    The class of the object on the patch specified by the xcor and yor above.
 *  </li>
 * </ol>
 * 
 * <b>NOTE:</b> a decision must be made on whether all x/y-cor values will be 
 * relative <i>or</i> to the location of the calling turtle or absolute.  
 * Otherwise, the scene instance generated will produce odd behaviour when used 
 * by CHREST.
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class New extends DefaultReporter {

  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(
      new int[]{
        Syntax.ListType(), //Information to create the Scene
        Syntax.StringType() //The name of the Scene
      }, 
      Syntax.WildcardType()
    );
  }
  
  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    
    LogoList infoAboutObjects = args[0].getList();

    //Since domain-specific and agent relative xcor and ycor coordinates may 
    //be specified for items in a Nelogo environment, analyse the scene passed 
    //to establish the smallest and greatest width and height (x/y-cors, 
    //respectively) so that a jchrest.lib.Scene instance can be correctly 
    //constructed.
    Integer minX = null;
    Integer maxX = null;
    Integer minY = null;
    Integer maxY = null;

    for(int i = 0; i < infoAboutObjects.size(); i++){
      LogoList objectInfo = (LogoList)infoAboutObjects.get(i);
      int xcor = ((Double)objectInfo.get(0)).intValue();
      int ycor = ((Double)objectInfo.get(1)).intValue();

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
    }

    //Create a new scene instance using the max/min x/y values set above.  Add
    //1 to the result since if maxX and minX are 2 and 1 respectively, the 
    //result (1) does not take into account 2 xcors (which it should since the
    //turtle can see two "columns" in the environment, not 1).
    Scene scene = new Scene(
      args[1].getString(), 
      (maxY - minY) + 1, 
      (maxX - minX) + 1,
      null
    );

    //Set the domain-specific to non domain-specific (Scene coordinate) 
    //converters (Scene coordinates start at 0).
    int xcorConverter = 0 - minX;
    int ycorConverter = 0 - minY;

    //Populate the scene using info from the list passed.
    for(int i = 0; i < infoAboutObjects.size(); i++){
      LogoList objectInfo = (LogoList)infoAboutObjects.get(i);
      
      //Due to strange behaviour on behalf of the LogoList class (replaceItem()
      //does not seem to work, for example), construct an ArrayList (that 
      //definately allows over-writing of values) and populate it with the info
      //from the LogoList.
      ArrayList objectDetails = new ArrayList();
      objectDetails.add(((Double)objectInfo.get(0)).intValue() + xcorConverter);
      objectDetails.add(((Double)objectInfo.get(1)).intValue() + ycorConverter);
      objectDetails.add(objectInfo.get(2));
      objectDetails.add((String)objectInfo.get(3));
      
      //Since numbers in Netlogo are actually of datatype Double, they need to
      //be converted into Strings so that the "jchrest.lib.Scene#addItemToSquare()" 
      //method can be used below (ID needs to be a String).  In addition, the ID
      //will have a decimal point and a mantissa; these should be removed to
      //leave only the integer part of the number.
      Object id = objectDetails.get(2);
      if(id instanceof Double){
        String newId = String.valueOf( ((Double)id).intValue() );
        objectDetails.set(2, newId);
      }
      
      scene.addItemToSquare(
        (int)objectDetails.get(0),    //col (xcor)
        (int)objectDetails.get(1),    //row (ycor)
        (String)objectDetails.get(2), //who
        (String)objectDetails.get(3)  //class
      );
    }

    return scene;
  }
}
