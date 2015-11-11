package Scene;

import jchrest.lib.Scene;
import jchrest.lib.Square;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.LogoListBuilder;
import org.nlogo.api.Syntax;

/**
 * Returns the result of invoking {@link 
 * jchrest.lib.Scene#getLocationOfCreator()} and packaging the result into a
 * {@link org.nlogo.api.LogoList} with two elements: the absolute column and row 
 * coordinates of the creator's location in the {@link jchrest.lib.Scene} 
 * passed.
 * 
 * If the Scene's creator can not be located, an empty {@link 
 * org.nlogo.api.LogoList} will be returned.
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class GetLocationOfCreator extends DefaultReporter {

  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(
      new int[] {
        Syntax.WildcardType() // Scene to find creator in.
      },
      Syntax.ListType()
    );
  }
  
  @Override
  public Object report(Argument[] args, Context cntxt) throws ExtensionException, LogoException {
    Square locationOfCreator = ((Scene)args[0].get()).getLocationOfCreator();
    LogoListBuilder locationOfCreatorList = new LogoListBuilder();
    
    if(locationOfCreator != null){
      locationOfCreatorList.add((double)locationOfCreator.getColumn());
      locationOfCreatorList.add((double)locationOfCreator.getRow());
    }
    
    return locationOfCreatorList.toLogoList();
  }
  
}
