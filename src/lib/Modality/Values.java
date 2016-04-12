package lib.Modality;

import java.util.Map;
import java.util.TreeMap;
import jchrest.lib.Modality;
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
public class Values extends DefaultReporter {
  
  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(Syntax.ListType());
  }

  /**
   * 
   * @param args
   * @param context
   * 
   * @return A {@link org.nlogo.api.LogoList} containing the result of {@link 
   * jchrest.lib.Modality#values()} in alphabetical order based upon invoking 
   * {@link jchrest.lib.Modality#toString()} on each {@link 
   * jchrest.lib.Modality} returned by {@link jchrest.lib.Modality#values()}.
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    LogoListBuilder modalityList = new LogoListBuilder();
    
    //Store results of invoking toString() on each Modality returned in 
    //Modality.values() as keys in a TreeMap.  When the TreeMap is iterated 
    //through using TreeMap.entrySet(), the order will be alphabetical (from 
    //a -> z).
    TreeMap<String, Modality> modalities = new TreeMap();
    for(Modality modality : Modality.values()){
      modalities.put(modality.toString(), modality);
    }

    for(Map.Entry<String, Modality> modalityEntry : modalities.entrySet()){
      modalityList.add(modalityEntry.getValue());
    }

    return modalityList.toLogoList();
  }
}
