package DomainSpecifics;

import Shared.BaseExtensionVariablesAndMethods;
import java.util.Set;
import jchrest.architecture.Chrest;
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
 * Returns the result of invoking {@link jchrest.lib.DomainSpecifics#
 * proposeSalientSquareFixations(jchrest.lib.Scene, 
 * jchrest.architecture.Chrest)} and packaging the results into a {@link 
 * org.nlogo.api.LogoList}.
 * 
 * The {@link org.nlogo.api.LogoList} returned will be two-dimensional.  The
 * first dimension {@link org.nlogo.api.LogoList} contains {@link 
 * org.nlogo.api.LogoList}s that contain the absolute (non-calling turtle 
 * relative) column and row coordinates of salient patches, i.e.
 * 
 * [
 *  [salientPatch1column salientPatch1row]
 *  [salientPatch2column salientPatch2row]
 *  [salientPatch3column salientPatch3row]  
 * ]
 * 
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class GetSalientPatches extends DefaultReporter {
  
  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(
      new int[]{
        Syntax.WildcardType() // The Scene that salient patches should be 
                              // retrieved in context of.
      }, 
      Syntax.ListType());
  }

  @Override
  public Object report(Argument[] args, Context cntxt) throws ExtensionException, LogoException {
    LogoListBuilder salientPatchesList = new LogoListBuilder();
    Chrest turtlesChrestInstance = BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(cntxt);
    Set<Square> salientPatches = turtlesChrestInstance.getDomainSpecifics().proposeSalientSquareFixations((Scene)args[0].get(), turtlesChrestInstance);
    for(Square salientPatch : salientPatches){
      LogoListBuilder salientPatchDetails = new LogoListBuilder();
      salientPatchDetails.add(salientPatch.getColumn());
      salientPatchDetails.add(salientPatch.getRow());
      salientPatchesList.add(salientPatchDetails.toLogoList());
    }
    return salientPatchesList.toLogoList();
  }
}
