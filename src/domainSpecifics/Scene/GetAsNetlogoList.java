package domainSpecifics.Scene;

import jchrest.domainSpecifics.Scene;
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

  /**
   * 
   * @param args The {@link jchrest.lib.Scene} to be parsed as a {@link 
   * org.nlogo.api.LogoList}.
   * @param context
   * 
   * @return A two dimensional {@link org.nlogo.api.LogoList} representing the
   * {@link jchrest.lib.Scene} passed as the first parameter to this primitive.
   * For example, if {@link jchrest.lib.Scene#getWidth()} and {@link 
   * jchrest.lib.Scene#getHeight()} both return 5 for the {@link 
   * jchrest.lib.Scene} passed as the first parameter to this primitive then 
   * invoking {@code item (2) (item (0) (list)))} will return the {@link 
   * jchrest.domainSpecifics.SceneObject} on coordinates (0, 2) in the {@link 
   * jchrest.lib.Scene} passed as the first parameter to this primitive.
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    LogoListBuilder sceneAsLogoList = new LogoListBuilder();
    Scene scene = (Scene)args[0].get();
    
    for(int colIndex = 0; colIndex < scene.getWidth(); colIndex++){
      LogoListBuilder column = new LogoListBuilder();
      
      for(int rowIndex = 0; rowIndex < scene.getHeight(); rowIndex++){
        LogoListBuilder row = new LogoListBuilder();
        row.add(scene.getSquareContents(colIndex, rowIndex));
        column.add(row.toLogoList());
      }
      
      sceneAsLogoList.add(column.toLogoList());
    }
    
    return sceneAsLogoList.toLogoList();
  }
  
}
