package org.nlogo.extensions.chrest.lib.reinforcementLearning;

import java.util.Map;
import java.util.TreeMap;
import jchrest.lib.ReinforcementLearning.ReinforcementLearningTheories;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.LogoListBuilder;

/**
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class Values extends DefaultReporter{

  /**
   * 
   * @param args
   * @param cntxt
   * 
   * @return A {@link org.nlogo.api.LogoList} containing the result of {@link 
   * jchrest.lib.ReinforcementLearning.ReinforcementLearningTheories#values()} 
   * in alphabetical order based upon invoking {@link 
   * jchrest.lib.ReinforcementLearning.ReinforcementLearningTheories#toString()} 
   * on each {@link 
   * jchrest.lib.ReinforcementLearning.ReinforcementLearningTheories} returned 
   * by {@link 
   * jchrest.lib.ReinforcementLearning.ReinforcementLearningTheories#values()}.
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Object report(Argument[] args, Context cntxt) throws ExtensionException, LogoException {
    LogoListBuilder reinforcementLearningTheoriesList = new LogoListBuilder();
    
    //Store results of invoking toString() on each ReinforcementLearningTheories
    //returned by ReinforcementLearningTheories.values() as keys in a TreeMap. 
    //When the TreeMap is iterated through using TreeMap.entrySet(), the order 
    //will be alphabetical (from a -> z).
    TreeMap<String, ReinforcementLearningTheories> reinforcementLearningTheories = new TreeMap();
    for(ReinforcementLearningTheories rlt : ReinforcementLearningTheories.values()){
      reinforcementLearningTheories.put(rlt.toString(), rlt);
    }

    for(Map.Entry<String, ReinforcementLearningTheories> rltEntry : reinforcementLearningTheories.entrySet()){
      reinforcementLearningTheoriesList.add(rltEntry.getValue());
    }

    return reinforcementLearningTheoriesList.toLogoList();
  }
  
}
