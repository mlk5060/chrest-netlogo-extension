package domainSpecifics.Scene;

import jchrest.domainSpecifics.Scene;
import jchrest.domainSpecifics.SceneObject;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.LogoListBuilder;
import org.nlogo.api.Syntax;

/**
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class GetSceneObjectLocations extends DefaultReporter {
  
  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(
      new int[]{
        Syntax.WildcardType(),
        Syntax.BooleanType(),
        Syntax.StringType()
      }, 
      Syntax.ListType()
    );
  }

  /**
   * 
   * @param args The first parameter should be the {@link 
   * jchrest.domainSpecifics.Scene} to search for {@link 
   * jchrest.domainSpecifics.SceneObject SceneObjects} in context of.
   * <p>
   * The second parameter should indicate whether {@link 
   * jchrest.domainSpecifics.SceneObject SceneObjects} are to be searched based
   * upon the result of {@link 
   * jchrest.domainSpecifics.SceneObject#getObjectType()} (pass {@link 
   * java.lang.Boolean#TRUE}) or {@link 
   * jchrest.domainSpecifics.SceneObject#getIdentifier()} (pass {@link 
   * java.lang.Boolean#FALSE}).
   * <p>
   * The third parameter should be the {@link 
   * jchrest.domainSpecifics.SceneObject#_identifier} or {@link 
   * jchrest.domainSpecifics.SceneObject#_objectType} to search for.
   * 
   * @param context
   * 
   * @return The locations of {@link jchrest.lib.SceneObject SceneObjects} 
   * that match the {@link jchrest.domainSpecifics.SceneObject#_identifier} or
   * {@link jchrest.domainSpecifics.SceneObject#_objectType} specified in the 
   * {@link jchrest.domainSpecifics.Scene} passed as a {@link 
   * org.nlogo.api.LogoList}.
   * <p>
   * Locations in the {@link org.nlogo.api.LogoList} are relative to the {@link 
   * jchrest.domainSpecifics.Scene} passed.
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    LogoListBuilder objectLocations = new LogoListBuilder();
    
    Scene scene = (Scene)args[0].get();
    boolean identifyByType = args[1].getBooleanValue();
    String identifierOrType = args[2].getString();
    

    for(int col = 0; col < scene.getWidth(); col++){
      for(int row = 0; row < scene.getHeight(); row++){
        SceneObject object = scene.getSquareContents(col, row);

        if( identifierOrType.equals(identifyByType ? object.getObjectType() : object.getIdentifier()) ){
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
