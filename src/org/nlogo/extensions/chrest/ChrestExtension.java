package org.nlogo.extensions.chrest;

import jchrest.architecture.Chrest;
import org.nlogo.agent.Agent;
import org.nlogo.api.AgentException;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultClassManager;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.PrimitiveManager;

/**
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
//TODO: Check if the primitive name appears in ExtensionExceptions that are
//thrown.  If not, try to prepend the error message with the primitive name for
//better debugging.
public class ChrestExtension extends DefaultClassManager {
  
  public final static String CHREST_BREED_VARIABLE = "CHREST";

  public static void main(String[] argv) throws Exception {

    Class cls = ChrestExtension.class;
    String name = cls.getName(); 
    System.out.println(name);

  }
  
  /**
   * @param context
   * @return The result of casting {@link org.nlogo.api.Context#getAgent()} in
   * context of the {@code context} specified to {@link org.nlogo.agent.Agent}.
   */
  public static Agent getAgent(Context context) {
    return (Agent) context.getAgent();
  }
  
  /**
   * @param context
   * 
   * @return The {@link jchrest.architecture.Chrest} instance for the calling 
   * {@link org.nlogo.agent.Agent}.
   * 
   * @throws org.nlogo.api.ExtensionException If turtle does not have a {@link
   * jchrest.architecture.Chrest}.
   */
  public static Chrest getTurtlesChrestInstance(Context context) throws ExtensionException {
    Chrest chrest = null;
    try {
      chrest = (Chrest)ChrestExtension.getAgent(context).getBreedVariable(ChrestExtension.CHREST_BREED_VARIABLE);
    } catch (AgentException ex) {
      throw new ExtensionException(ex);
    }
    return chrest;
  }

  @Override
  public void load(PrimitiveManager primitiveManager) {
    
    //Chrest primitives
    primitiveManager.addPrimitive("get-add-production-time", new org.nlogo.extensions.chrest.architecture.chrest.GetAddProductionTime());
    primitiveManager.addPrimitive("get-attention-clock", new org.nlogo.extensions.chrest.architecture.chrest.GetAttentionClock());
    primitiveManager.addPrimitive("get-cognition-clock", new org.nlogo.extensions.chrest.architecture.chrest.GetCognitionClock());
    primitiveManager.addPrimitive("get-discrimination-time", new org.nlogo.extensions.chrest.architecture.chrest.GetDiscriminationTime());
    primitiveManager.addPrimitive("get-familiarisation-time", new org.nlogo.extensions.chrest.architecture.chrest.GetFamiliarisationTime());
    primitiveManager.addPrimitive("get-ltm-avg-depth", new org.nlogo.extensions.chrest.architecture.chrest.GetLtmAverageDepth());
    primitiveManager.addPrimitive("get-ltm-modality-size", new org.nlogo.extensions.chrest.architecture.chrest.GetLtmModalitySize());
    primitiveManager.addPrimitive("get-ltm-size", new org.nlogo.extensions.chrest.architecture.chrest.GetLtmSize());
    primitiveManager.addPrimitive("get-perceiver", new org.nlogo.extensions.chrest.architecture.chrest.GetPerceiver());
    primitiveManager.addPrimitive("get-production-count", new org.nlogo.extensions.chrest.architecture.chrest.GetProductionCount());
    primitiveManager.addPrimitive("get-reinforcement-learning-theory", new org.nlogo.extensions.chrest.architecture.chrest.GetReinforcementLearningTheory());
    primitiveManager.addPrimitive("get-stm-node-count", new org.nlogo.extensions.chrest.architecture.stm.GetCount());
    primitiveManager.addPrimitive("get-visual-spatial-fields", new org.nlogo.extensions.chrest.architecture.chrest.GetVisualSpatialFields());
    primitiveManager.addPrimitive("get-visual-spatial-field-as-scene", new org.nlogo.extensions.chrest.architecture.chrest.GetVisualSpatialFieldAsScene());
    primitiveManager.addPrimitive("get-visual-spatial-field-object-locations", new org.nlogo.extensions.chrest.architecture.chrest.GetVisualSpatialFieldObjectLocations());
    primitiveManager.addPrimitive("learn-production", new org.nlogo.extensions.chrest.architecture.chrest.LearnProduction());
    primitiveManager.addPrimitive("move-visual-spatial-field-objects", new org.nlogo.extensions.chrest.architecture.chrest.MoveObjectsInVisualSpatialField());
    primitiveManager.addPrimitive("recognise-and-learn", new org.nlogo.extensions.chrest.architecture.chrest.RecogniseAndLearn());
    primitiveManager.addPrimitive("reinforce-production", new org.nlogo.extensions.chrest.architecture.chrest.ReinforceProduction());
    primitiveManager.addPrimitive("save-ltm-network-image", new org.nlogo.extensions.chrest.architecture.chrest.SaveLtmNetworkImage());
    primitiveManager.addPrimitive("schedule-or-make-next-fixation", new org.nlogo.extensions.chrest.architecture.chrest.ScheduleOrMakeNextFixation());
    primitiveManager.addPrimitive("set-add-production-time", new org.nlogo.extensions.chrest.architecture.chrest.SetAddProductionTime());
    primitiveManager.addPrimitive("set-discrimination-time", new org.nlogo.extensions.chrest.architecture.chrest.SetDiscriminationTime());
    primitiveManager.addPrimitive("set-domain", new org.nlogo.extensions.chrest.architecture.chrest.SetDomain());
    primitiveManager.addPrimitive("set-familiarisation-time", new org.nlogo.extensions.chrest.architecture.chrest.SetFamiliarisationTime());
    primitiveManager.addPrimitive("set-reinforcement-learning-theory", new org.nlogo.extensions.chrest.architecture.chrest.SetReinforcementLearningTheory());
    
    //DomainSpecifics.TileworldDomain primitives
    primitiveManager.addPrimitive("TileworldDomain.get-hole-identifier", new org.nlogo.extensions.chrest.domainSpecifics.tileworld.GetHoleIdentifier());
    primitiveManager.addPrimitive("TileworldDomain.get-opponent-identifier", new org.nlogo.extensions.chrest.domainSpecifics.tileworld.GetOpponentIdentifier());
    primitiveManager.addPrimitive("TileworldDomain.get-tile-identifier", new org.nlogo.extensions.chrest.domainSpecifics.tileworld.GetTileIdentifier());
  
    //Modality primitives
    primitiveManager.addPrimitive("Modality.get-modalities", new org.nlogo.extensions.chrest.lib.modality.Values());
    
    //Node primitives
    primitiveManager.addPrimitive("Node.get-image", new org.nlogo.extensions.chrest.architecture.node.GetImage());
    primitiveManager.addPrimitive("Node.get-productions", new org.nlogo.extensions.chrest.architecture.node.GetProductions());
    
    //Pattern primitives
    primitiveManager.addPrimitive("Pattern.create-number-pattern", new org.nlogo.extensions.chrest.lib.pattern.CreateNumberPattern());
    primitiveManager.addPrimitive("Pattern.get-subclasses", new org.nlogo.extensions.chrest.lib.pattern.GetSubclasses());
    
    //ItemSquarePattern primitives
    primitiveManager.addPrimitive("ItemSquarePattern.new", new org.nlogo.extensions.chrest.lib.itemSquarePattern.New());
    primitiveManager.addPrimitive("ItemSquarePattern.get-column", new org.nlogo.extensions.chrest.lib.itemSquarePattern.GetColumn());
    primitiveManager.addPrimitive("ItemSquarePattern.get-item", new org.nlogo.extensions.chrest.lib.itemSquarePattern.GetItem());
    primitiveManager.addPrimitive("ItemSquarePattern.get-as-string", new org.nlogo.extensions.chrest.lib.itemSquarePattern.GetAsString());
    primitiveManager.addPrimitive("ItemSquarePattern.get-row", new org.nlogo.extensions.chrest.lib.itemSquarePattern.GetRow());
    
    //ListPattern primitives
    primitiveManager.addPrimitive("ListPattern.new", new org.nlogo.extensions.chrest.lib.listPattern.New());
    primitiveManager.addPrimitive("ListPattern.get-as-netlogo-list", new org.nlogo.extensions.chrest.lib.listPattern.GetAsNetlogoList());
    primitiveManager.addPrimitive("ListPattern.get-as-string", new org.nlogo.extensions.chrest.lib.listPattern.GetAsString());
    primitiveManager.addPrimitive("ListPattern.empty?", new org.nlogo.extensions.chrest.lib.listPattern.IsEmpty());
    primitiveManager.addPrimitive("ListPattern.remove", new org.nlogo.extensions.chrest.lib.listPattern.Remove());
    primitiveManager.addPrimitive("ListPattern.remove-blind-empty-and-unknown-patches", new org.nlogo.extensions.chrest.lib.listPattern.RemoveBlindEmptyAndUnknownPatches());
    
    //Perceiver primitives.
    primitiveManager.addPrimitive("Perceiver.get-fixations", new org.nlogo.extensions.chrest.architecture.perceiver.GetFixations());
    
    //Reinforcement primitives
    primitiveManager.addPrimitive("ReinforcementLearning.get-theory-names", new org.nlogo.extensions.chrest.lib.reinforcementLearning.GetTheoryNames());
    primitiveManager.addPrimitive("ReinforcementLearning.values", new org.nlogo.extensions.chrest.lib.reinforcementLearning.Values());
    
    //Scene primitives.
    primitiveManager.addPrimitive("Scene.new", new org.nlogo.extensions.chrest.domainSpecifics.scene.New());
    primitiveManager.addPrimitive("Scene.get-as-list-pattern", new org.nlogo.extensions.chrest.domainSpecifics.scene.GetAsListPattern());
    primitiveManager.addPrimitive("Scene.get-as-netlogo-list", new org.nlogo.extensions.chrest.domainSpecifics.scene.GetAsNetlogoList());
    primitiveManager.addPrimitive("Scene.get-blind-square-token", new org.nlogo.extensions.chrest.domainSpecifics.scene.GetBlindSquareToken());
    primitiveManager.addPrimitive("Scene.get-creator-token", new org.nlogo.extensions.chrest.domainSpecifics.scene.GetCreatorToken());
    primitiveManager.addPrimitive("Scene.get-empty-square-token", new org.nlogo.extensions.chrest.domainSpecifics.scene.GetEmptySquareToken());
    primitiveManager.addPrimitive("Scene.get-location-of-creator", new org.nlogo.extensions.chrest.domainSpecifics.scene.GetLocationOfCreator());
    primitiveManager.addPrimitive("Scene.get-scene-object-locations", new org.nlogo.extensions.chrest.domainSpecifics.scene.GetSceneObjectLocations());
    primitiveManager.addPrimitive("Scene.get-creator-token", new org.nlogo.extensions.chrest.domainSpecifics.scene.GetCreatorToken());
    primitiveManager.addPrimitive("Scene.get-square-contents", new org.nlogo.extensions.chrest.domainSpecifics.scene.GetSquareContents());
    
    //VisualSpatialFieldObject primitives
    primitiveManager.addPrimitive("VisualSpatialFieldObject.get-identifier", new org.nlogo.extensions.chrest.lib.visualSpatialFieldObject.GetIdentifier());
    primitiveManager.addPrimitive("VisualSpatialFieldObject.get-object-type", new org.nlogo.extensions.chrest.lib.visualSpatialFieldObject.GetObjectType());
    primitiveManager.addPrimitive("VisualSpatialFieldObject.get-unknown-square-token", new org.nlogo.extensions.chrest.lib.visualSpatialFieldObject.GetUnknownSquareToken());
  }
}