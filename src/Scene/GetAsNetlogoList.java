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
 * Reports a {@link jchrest.lib.Scene} as a 3D {@link org.nlogo.api.LogoList}
 * whose first dimension {@link org.nlogo.api.LogoList} represents columns of 
 * the {@link jchrest.lib.Scene}, second dimension {@link 
 * org.nlogo.api.LogoList} represents rows of the {@link jchrest.lib.Scene} and 
 * third dimension {@link org.nlogo.api.LogoList} contains two elements: the 
 * result of invoking {@link 
 * jchrest.lib.SceneObject#getIdentifier()} and {@link 
 * jchrest.lib.SceneObject#getObjectClass()} on the {@link 
 * jchrest.lib.SceneObject} on the column and row specified in the 
 * {@link jchrest.lib.Scene} passed.
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class GetAsNetlogoList extends DefaultReporter {
  
  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(
      new int[]{
        Syntax.WildcardType()
      }, 
      Syntax.ListType()
    );
  }

  @Override
  public Object report(Argument[] args, Context cntxt) throws ExtensionException, LogoException {
    LogoListBuilder sceneAsLogoList = new LogoListBuilder();
    Scene scene = (Scene)args[0].get();
    
    for(int colIndex = 0; colIndex < scene.getWidth(); colIndex++){
      LogoListBuilder column = new LogoListBuilder();
      
      for(int rowIndex = 0; rowIndex < scene.getHeight(); rowIndex++){
        LogoListBuilder row = new LogoListBuilder();
        
        SceneObject squareContents = scene.getSquareContents(colIndex, rowIndex);
        row.add(squareContents.getIdentifier());
        row.add(squareContents.getObjectClass());
        column.add(row.toLogoList());
      }
      
      sceneAsLogoList.add(column.toLogoList());
    }
    
    return sceneAsLogoList.toLogoList();
  }
  
}
