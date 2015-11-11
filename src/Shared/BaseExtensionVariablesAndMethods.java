package Shared;

import jchrest.architecture.Chrest;
import jchrest.lib.Modality;
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
   * @param context
   * @return The result of invoking {@link org.nlogo.api.Context#getAgent()} as
   * an {@link org.nlogo.agent.Agent} for the calling turtle.
   */
  public static org.nlogo.agent.Agent getAgent(Context context) {
    return (org.nlogo.agent.Agent) context.getAgent();
  }

  /**
   * @param context
   * @return The calling turtle's {@link jchrest.architecture.Chrest}.
   * @throws org.nlogo.api.ExtensionException If turtle does not have a {@link
   * jchrest.architecture.Chrest}.
   */
  public static Chrest getTurtlesChrestInstance(Context context) throws ExtensionException {
    Chrest chrest = null;
    
    try {
      chrest = (Chrest)BaseExtensionVariablesAndMethods.getAgent(context).getBreedVariable(BaseExtensionVariablesAndMethods.CHREST_INSTANCE_CHREST_AGENT_BREED_VAR_NAME);
    } catch (AgentException ex) {
      throw new ExtensionException(ex);
    }
    
    return chrest;
  }
}
