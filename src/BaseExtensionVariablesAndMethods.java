import jchrest.architecture.Chrest;
import jchrest.lib.ItemSquarePattern;
import jchrest.lib.ListPattern;
import jchrest.lib.Modality;
import jchrest.lib.NumberPattern;
import org.nlogo.api.AgentException;
import org.nlogo.api.Context;
import org.nlogo.api.ExtensionException;

/**
 * Contains constants and methods shared by CHREST Netlogo extension classes.
 * 
 * @author Martyn Lloyd-Kelly
 */
public class BaseExtensionVariablesAndMethods {
  
  public final static String CHREST_INSTANCE_CHREST_AGENT_BREED_VAR_NAME = "CHREST-INSTANCE";
  public final static String CURRENT_SCENE_BREED_VAR_NAME = "CURRENT-SCENE";
  public final static String NUMBER_FIXATIONS_BREED_VAR_NAME = "NUMBER-FIXATIONS";
  public final static String SIGHT_RADIUS_BREED_VAR_NAME = "SIGHT-RADIUS";

  /**
   * Checks to see if the calling turtle has been endowed with a CHREST 
   * architecture object.
   * 
   * @param context Current Netlogo execution environment object.
   * @return Boolean true if the calling turtle has a Chrest instance stored in 
   * its Chrest breed variable, boolean false if not.
   * @throws AgentException
   */
  public static boolean agentHasChrestInstance(Context context) throws AgentException {
    return getAgent(context).getBreedVariable(BaseExtensionVariablesAndMethods.CHREST_INSTANCE_CHREST_AGENT_BREED_VAR_NAME) instanceof Chrest;
  }

  /**
   * Returns an instance of an org.nlogo.agent.Agent object for the calling
   * turtle.
   * 
   * @param context Current Netlogo execution environment object.
   * @return The calling turtle's org.nlogo.agent.Agent object.
   */
  public static org.nlogo.agent.Agent getAgent(Context context) {
    return (org.nlogo.agent.Agent) context.getAgent();
  }

  /**
   * Returns the calling turtle's CHREST object.
   * 
   * @param context Current Netlogo execution environment object.
   * @return The calling turtle's CHREST object.
   * @throws AgentException 
   */
  public static Chrest getTurtlesChrestInstance(Context context) throws AgentException {
    return (Chrest) BaseExtensionVariablesAndMethods.getAgent(context).getBreedVariable(BaseExtensionVariablesAndMethods.CHREST_INSTANCE_CHREST_AGENT_BREED_VAR_NAME);
  }

  /**
   * Creates, populates and returns a new ListPattern instance with the contents 
   * of the pattern specified that is parsed according to the pattern type 
   * specified.  If the pattern passed is empty, the function will throw an 
   * error.
   * 
   * @param patternModality The modality of the list pattern that is to be 
   * created and populated.
   * @param patternType The pattern type of the pattern that is to be used to 
   * create and populate the list pattern.
   * @param pattern The pattern that is to be used to create and populate the 
   * list pattern.
   * @return A new list pattern with the modality specified by the 
   * "patternModality" parameter containing the contents of "pattern".
   * @throws ExtensionException 
   */
  public static ListPattern createAndPopulateListPatternWithNetlogoPrimitivePattern(String patternModality, String patternType, String pattern) throws ExtensionException {

    //Create a new ListPattern object.
    ListPattern listPattern = new ListPattern();
    
    //Check to see if "patternType" is valid and that the pattern passed is not
    //empty. The validity of "patternModality" is not explicitly checked here 
    //since it is done when setting the list pattern modality later.
    if (BaseExtensionVariablesAndMethods.validPatternType(patternType) && !pattern.isEmpty()) {

      //Set the newly created list pattern's modality to the modality specified.
      listPattern = BaseExtensionVariablesAndMethods.setModalityOfListPattern(listPattern, patternModality);

      //Populate the list pattern according to the pattern type specified.
      if (patternType.equalsIgnoreCase(PatternType.ITEM_SQUARE.toString())) {
        String[] majorPatternParts = pattern.replaceAll("\\s*\\[\\s*", "").replaceAll("\\s*\\]\\s*", "]").split("]");

        for (String majorPatternPart : majorPatternParts) {
          String[] minorPatternParts = majorPatternPart.split("\\s");
          if (minorPatternParts.length != 3) {
            throw new ExtensionException("After splitting " + majorPatternPart + " on the occurrence of whitespace, there should be 3 parts but there are " + minorPatternParts.length + ".");
          }
          listPattern.add(new ItemSquarePattern(minorPatternParts[0], Integer.parseInt(minorPatternParts[1]), Integer.parseInt(minorPatternParts[2])));
        }
      }
      
      if (patternType.equalsIgnoreCase(PatternType.NUMBER.toString())){
        listPattern.add(NumberPattern.create(Integer.parseInt(pattern)));
      }
    }
    else{
      throw new ExtensionException("The pattern passed is empty.");
    }

    return listPattern;
  }

  /**
   * Checks whether the list pattern modality specified is valid and, if so, 
   * sets the modality of the list pattern specified to the modality specified.
   * 
   * @param listPattern The list pattern that is to have its modality set.
   * @param modality The modality that the list pattern should be set to.  
   * @return The list pattern whose modality is now set.
   * @throws ExtensionException 
   */
  private static ListPattern setModalityOfListPattern(ListPattern listPattern, String modality) throws ExtensionException {

    if (BaseExtensionVariablesAndMethods.validModality(modality)) {
      for (Modality m : Modality.values()) {
        if (modality.equalsIgnoreCase(m.toString())) {
          listPattern.setModality(m);
        }
      }
    }

    return listPattern;
  }

  /**
   * Checks to see if the modality specifier passed is valid.
   * 
   * @param modality The modality specifier to be checked.
   * @return Boolean true if the modality specifier passed is valid, if not, an 
   * extension exception is thrown informing the user that the modality 
   * specified is not valid before listing all valid modality specifiers.
   * @throws ExtensionException 
   * @see jchrest.lib#Modality
   */
  public static boolean validModality(String modality) throws ExtensionException {
    
    boolean validModality = false;

    for (Modality m : Modality.values()) {
      if (modality.equalsIgnoreCase(m.toString())) {
        validModality = true;
      }
    }

    if (!validModality) {
      String exceptionString = "'" + modality + "' is not a valid modality specifier.  Valid modality specifiers are: ";
      for (Modality m : Modality.values()) {
        exceptionString += "'" + m.toString() + "', ";
      }

      throw new ExtensionException(exceptionString.replaceFirst("\\,\\s$", " (case insensitive)."));
    }

    return true;
  }

  /**
   * Checks to see if the pattern type specified is valid.
   * 
   * @param patternType The pattern type specifier to be checked.
   * @return Boolean true if the pattern type specifier passed is valid, if not, 
   * an extension exception is thrown informing the user that the pattern type 
   * specified is not valid before listing all valid pattern type specifiers.
   * @throws ExtensionException 
   * @see PatternType
   */
  private static boolean validPatternType(String patternType) throws ExtensionException {

    boolean validPatternType = false;

    for (PatternType p : PatternType.values()) {
      if (patternType.equalsIgnoreCase(p.toString())) {
        validPatternType = true;
      }
    }

    if (!validPatternType) {
      String exceptionString = "'" + patternType + "' is not a valid pattern type specifier.  Valid pattern types are: ";
      for (PatternType p : PatternType.values()) {
        exceptionString += "'" + p.toString() + "', ";
      }

      throw new ExtensionException(exceptionString.replaceFirst("\\,\\s$", " (case insensitive)."));
    }

    return true;
  }
}
