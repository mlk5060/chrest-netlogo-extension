package architecture.Chrest;

import classManager.ChrestExtension;
import java.util.TreeMap;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.LogoList;
import org.nlogo.api.Syntax;

/**
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class GetVisualSpatialFieldAsScene extends DefaultReporter {
  
  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(new int[]{
      Syntax.NumberType(), 
      Syntax.ListType()
    }, 
    Syntax.WildcardType()
    );
  }
  
  /**
   * 
   * @param args See parameters for {@link 
   * jchrest.architecture.VisualSpatialField#getAsScene(int, java.util.TreeMap)}.
   * <p>
   * The {@link java.util.TreeMap} specifying the probabilities of placing 
   * various {@link jchrest.lib.VisualSpatialFieldObject 
   * VisualSpatialFieldObjects} on {@link 
   * jchrest.architecture.VisualSpatialField} coordinates whose {@link 
   * jchrest.lib.VisualSpatialFieldObject} status is unknown at the time 
   * specified as a parameter to this primitive should be formatted as a {@link 
   * org.nlogo.api.LogoList} of {@link org.nlogo.api.LogoList LogoLists}. Each
   * inner {@link org.nlogo.api.LogoList} should contain 2 elements , and a {@link 
   * java.lang.String}) in the following order:
   * <ol type="1">
   *  <li>
   *    The probability of putting a {@link 
   *    jchrest.lib.VisualSpatialFieldObject}
   *    with the type specified by the second element of this {@link 
   *    org.nlogo.api.LogoList} on a {@link 
   *    jchrest.architecture.VisualSpatialField} coordinate whose {@link 
   *    jchrest.lib.VisualSpatialFieldObject} status is unknown at the time 
   *    specified.  Should be specified as a {@link java.lang.Double}, i.e. a 
   *    standard Netlogo number.
   *  </li>
   *  <li>
   *    The type of {@link jchrest.lib.VisualSpatialFieldObject} that should be 
   *    put on a {@link jchrest.architecture.VisualSpatialField} coordinate 
   *    whose {@link jchrest.lib.VisualSpatialFieldObject} status is unknown at 
   *    the time specified if its associated probability (the first element of 
   *    this {@link org.nlogo.api.LogoList}) is selected by {@link 
   *    jchrest.architecture.VisualSpatialField#getAsScene(int, 
   *    java.util.TreeMap)}. Should be specified as a {@link java.lang.String}.
   *  </li>
   * </ol>
   * 
   * @param context
   * 
   * @return The result of invoking {@link 
   * jchrest.architecture.VisualSpatialField#getAsScene(int, java.util.TreeMap)}
   * in context of the {@link jchrest.architecture.VisualSpatialField} present 
   * in the calling turtle's {@link jchrest.architecture.Chrest} instance at the
   * time specified as the first parameter passed to this primitive.
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Object report(Argument args[], Context context) throws ExtensionException, LogoException{
    
    //Convert the LogoList that should specify the probabiity of placing various 
    //VisualSpatialFieldObjects on VisualSpatialField coordinates whose 
    //VisualSpatialFieldObject status is unknown at the time this function is 
    //invoked to a TreeMap.
    LogoList unknownProbabilitiesSpecified = args[1].getList();
    TreeMap<Double, String> unknownProbabilities = new TreeMap();
    for(Object unknownProbabilitySpecified : unknownProbabilitiesSpecified){
      LogoList unknownProbabilitySpecifiedAsList = (LogoList)unknownProbabilitySpecified;
      unknownProbabilities.put(
        (Double)unknownProbabilitySpecifiedAsList.get(0), 
        (String)unknownProbabilitySpecifiedAsList.get(1)
      );
    }
    
    //Invoke the CHREST function.
    int time = args[0].getIntValue();
    return ChrestExtension.getTurtlesChrestInstance(context)
      .getVisualSpatialFields().floorEntry(time).getValue().getAsScene(time, unknownProbabilities);
        
  }
}
