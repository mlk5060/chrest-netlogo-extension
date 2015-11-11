package Scene;

import jchrest.lib.Scene;
import jchrest.lib.SceneObject;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.LogoListBuilder;
import org.nlogo.api.Syntax;

/**
 * Returns the locations of {@link jchrest.lib.SceneObject}s specified using 
 * either a {@link jchrest.lib.SceneObject#_identifier} or an {@link 
 * jchrest.lib.SceneObject#_objectClass} in a {@link jchrest.lib.Scene}.
 * 
 * The locations returned are packaged in a {@link org.nlogo.api.LogoList}
 * that contains {@link org.nlogo.api.LogoList}s of distinct locations of the 
 * {@link jchrest.lib.SceneObject} specified.  The locations of the {@link 
 * jchrest.lib.SceneObject} are absolute (non-creator relative) coordinates and 
 * in column/row order.
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class GetObjectLocations extends DefaultReporter {
  
  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(
      new int[]{
        Syntax.WildcardType(), //The Scene to search for the object in
        Syntax.StringType(), // The object to search for (ID or class)
        Syntax.BooleanType() // Set to true to search by ID, otherwise, search by class
      }, 
      Syntax.ListType()
    );
  }

  @Override
  public Object report(Argument[] args, Context cntxt) throws ExtensionException, LogoException {
    LogoListBuilder objectLocations = new LogoListBuilder();
    
    Scene scene = (Scene)args[0].get();
    String objectToFind = args[1].getString();
    boolean identifyByObjectClass = args[2].getBooleanValue();

    for(int col = 0; col < scene.getWidth(); col++){
      for(int row = 0; row < scene.getHeight(); row++){
        SceneObject object = scene.getSquareContents(col, row);

        if( objectToFind.equals(identifyByObjectClass ? object.getIdentifier() : object.getObjectClass()) ){
          LogoListBuilder objectLocation = new LogoListBuilder();
          objectLocation.add((double)col);
          objectLocation.add((double)row);
          objectLocations.add(objectLocation.toLogoList());
        }
      }
    }
    
    return objectLocations.toLogoList();
  }
  
}
