
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import jchrest.architecture.MindsEye;
import org.nlogo.api.AgentException;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.LogoListBuilder;
import org.nlogo.api.Syntax;

/**
 * Returns the times at which new {@link jchrest.architecture.MindsEye} 
 * instances have been created in the calling turtle's CHREST model in ascending
 * order as an {@link org.nlogo.api.LogoList} instance.
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class GetMindsEyeHistory extends DefaultReporter{
  
  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(Syntax.ListType());
  }

  @Override
  public Object report(Argument[] argmnts, Context cntxt) throws ExtensionException, LogoException {
    LogoListBuilder mindsEyeHistory = new LogoListBuilder();
    
    try {
      TreeMap<Integer, MindsEye> mindsEyeHistoryFromChrest = BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(cntxt).getMindsEyes();
      for(Entry<Integer, MindsEye> entry : mindsEyeHistoryFromChrest.entrySet()){
        mindsEyeHistory.add((double)entry.getKey());
      }
    } catch (AgentException ex) {
      Logger.getLogger(GetMindsEyeHistory.class.getName()).log(Level.SEVERE, "", ex);
    }
    
    return mindsEyeHistory.toLogoList();
  }
  
}
