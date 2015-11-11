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
 * Returns the result of packaging the results of invoking {@link 
 * jchrest.lib.SceneObject#getIdentifier()} and {@link 
 * jchrest.lib.SceneObject#getObjectClass()} on the result of invoking
 * {@link jchrest.lib.Scene#getSquareContents(int, int)} as a {@link 
 * org.nlogo.api.LogoList}.
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class GetSquareContentsAsNetlogoList extends DefaultReporter {
  
  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(
      new int[]{
        Syntax.WildcardType(), // Scene
        Syntax.NumberType(), // Col
        Syntax.NumberType() // Row
      }, 
      Syntax.ListType()
    );
  }

  @Override
  public Object report(Argument[] args, Context cntxt) throws ExtensionException, LogoException {
    SceneObject squareContents = ((Scene)args[0].get()).getSquareContents(args[1].getIntValue(), args[2].getIntValue());
    LogoListBuilder squareContentsList = new LogoListBuilder();
    
    if(squareContents != null){
      squareContentsList.add(squareContents.getIdentifier());
      squareContentsList.add(squareContents.getObjectClass());
    }
    
    return squareContentsList.toLogoList();
  }
  
}
