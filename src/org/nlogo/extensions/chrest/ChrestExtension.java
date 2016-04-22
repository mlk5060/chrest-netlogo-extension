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
    
    //jchrest.architecture.Chrest primitives
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
    primitiveManager.addPrimitive("get-stm", new org.nlogo.extensions.chrest.architecture.chrest.GetStm());
    primitiveManager.addPrimitive("get-visual-spatial-field", new org.nlogo.extensions.chrest.architecture.chrest.GetVisualSpatialField());
    primitiveManager.addPrimitive("get-visual-spatial-field-as-scene", new org.nlogo.extensions.chrest.architecture.chrest.GetVisualSpatialFieldAsScene());
    primitiveManager.addPrimitive("get-visual-spatial-field-object-locations", new org.nlogo.extensions.chrest.architecture.chrest.GetVisualSpatialFieldObjectLocations());
    primitiveManager.addPrimitive("is-attention-free?", new org.nlogo.extensions.chrest.architecture.chrest.IsAttentionFree());
    primitiveManager.addPrimitive("move-visual-spatial-field-objects", new org.nlogo.extensions.chrest.architecture.chrest.MoveObjectsInVisualSpatialField());
    primitiveManager.addPrimitive("new", new org.nlogo.extensions.chrest.architecture.chrest.New());
    primitiveManager.addPrimitive("recognise-and-learn", new org.nlogo.extensions.chrest.architecture.chrest.RecogniseAndLearn());
    primitiveManager.addPrimitive("reinforce-production", new org.nlogo.extensions.chrest.architecture.chrest.ReinforceProduction());
    primitiveManager.addPrimitive("save-ltm-network-image", new org.nlogo.extensions.chrest.architecture.chrest.SaveLtmNetworkImage());
    primitiveManager.addPrimitive("schedule-or-make-next-fixation", new org.nlogo.extensions.chrest.architecture.chrest.ScheduleOrMakeNextFixation());
    primitiveManager.addPrimitive("set-add-production-time", new org.nlogo.extensions.chrest.architecture.chrest.SetAddProductionTime());
    primitiveManager.addPrimitive("set-discrimination-time", new org.nlogo.extensions.chrest.architecture.chrest.SetDiscriminationTime());
    primitiveManager.addPrimitive("set-domain", new org.nlogo.extensions.chrest.architecture.chrest.SetDomain());
    primitiveManager.addPrimitive("set-familiarisation-time", new org.nlogo.extensions.chrest.architecture.chrest.SetFamiliarisationTime());
    primitiveManager.addPrimitive("set-recognised-visual-spatial-field-object-lifespan", new org.nlogo.extensions.chrest.architecture.chrest.SetRecognisedVisualSpatialFieldObjectLifespan());
    primitiveManager.addPrimitive("set-reinforcement-learning-theory", new org.nlogo.extensions.chrest.architecture.chrest.SetReinforcementLearningTheory());
    primitiveManager.addPrimitive("set-time-to-access-visual-spatial-field", new org.nlogo.extensions.chrest.architecture.chrest.SetTimeToAccessVisualSpatialField());
    primitiveManager.addPrimitive("set-time-to-encode-recognised-scene-object-as-visual-spatial-field-object", new org.nlogo.extensions.chrest.architecture.chrest.SetTimeToEncodeRecognisedSceneObjectAsVisualSpatialFieldObject());
    primitiveManager.addPrimitive("set-time-to-encode-unrecognised-empty-square-scene-object-as-visual-spatial-field-object", new org.nlogo.extensions.chrest.architecture.chrest.SetTimeToEncodeUnrecognisedEmptySquareSceneObjectAsVisualSpatialFieldObject());
    primitiveManager.addPrimitive("set-time-to-encode-unrecognised-non-empty-square-scene-object-as-visual-spatial-field-object", new org.nlogo.extensions.chrest.architecture.chrest.SetTimeToEncodeUnrecognisedNonEmptySquareSceneObjectAsVisualSpatialFieldObject());
    primitiveManager.addPrimitive("set-time-to-move-visual-spatial-field-object", new org.nlogo.extensions.chrest.architecture.chrest.SetTimeToMoveVisualSpatialFieldObject());
    primitiveManager.addPrimitive("set-time-to-process-unrecognised-scene-object-during-visual-spatial-field-construction", new org.nlogo.extensions.chrest.architecture.chrest.SetTimeToProcessUnrecognisedSceneObjectDuringVisualSpatialFieldConstruction());
    primitiveManager.addPrimitive("set-unrecognised-visual-spatial-field-object-lifespan", new org.nlogo.extensions.chrest.architecture.chrest.SetUnrecognisedVisualSpatialFieldObjectLifespan());
    primitiveManager.addPrimitive("turn-off-debugging", new org.nlogo.extensions.chrest.architecture.chrest.TurnOffDebugging());
    primitiveManager.addPrimitive("turn-on-debugging", new org.nlogo.extensions.chrest.architecture.chrest.TurnOnDebugging());
    
    //jchrest.architecture.Node primitives
    primitiveManager.addPrimitive("Node.get-contents", new org.nlogo.extensions.chrest.architecture.node.GetContents());
    primitiveManager.addPrimitive("Node.get-filled-slots", new org.nlogo.extensions.chrest.architecture.node.GetFilledSlots());
    primitiveManager.addPrimitive("Node.get-image", new org.nlogo.extensions.chrest.architecture.node.GetImage());
    primitiveManager.addPrimitive("Node.get-productions", new org.nlogo.extensions.chrest.architecture.node.GetProductions());
    primitiveManager.addPrimitive("Node.get-reference", new org.nlogo.extensions.chrest.architecture.node.GetReference());
    primitiveManager.addPrimitive("Node.is-root-node?", new org.nlogo.extensions.chrest.architecture.node.IsRootNode());
    primitiveManager.addPrimitive("Node.is-template?", new org.nlogo.extensions.chrest.architecture.node.IsTemplate());
    
    //jchrest.architecture.Perceiver primitives.
    primitiveManager.addPrimitive("Perceiver.get-fixation-field-of-view", new org.nlogo.extensions.chrest.architecture.perceiver.GetFixationFieldOfView());
    primitiveManager.addPrimitive("Perceiver.get-fixations", new org.nlogo.extensions.chrest.architecture.perceiver.GetFixations());
    primitiveManager.addPrimitive("Perceiver.get-fixations-performed", new org.nlogo.extensions.chrest.architecture.perceiver.GetFixationsPerformed());
    
    //jchrest.architecture.Stm primitives
    primitiveManager.addPrimitive("Stm.get-count", new org.nlogo.extensions.chrest.architecture.stm.GetCount());
    primitiveManager.addPrimitive("Stm.get-contents", new org.nlogo.extensions.chrest.architecture.stm.GetContents());
    
    //jchrest.architecture.VisualSpatialField primitives
    primitiveManager.addPrimitive("VisualSpatialField.get-coordinate-contents", new org.nlogo.extensions.chrest.architecture.visualSpatialField.GetCoordinateContents());
    primitiveManager.addPrimitive("VisualSpatialField.get-object-locations", new org.nlogo.extensions.chrest.architecture.visualSpatialField.GetObjectLocations());
    
    //jchrest.domainSpecifics.Fixation primitives
    primitiveManager.addPrimitive("Fixation.get-column-fixated-on", new org.nlogo.extensions.chrest.domainSpecifics.fixation.GetColumnFixatedOn());
    primitiveManager.addPrimitive("Fixation.get-object-seen", new org.nlogo.extensions.chrest.domainSpecifics.fixation.GetObjectSeen());
    primitiveManager.addPrimitive("Fixation.get-row-fixated-on", new org.nlogo.extensions.chrest.domainSpecifics.fixation.GetRowFixatedOn());
    primitiveManager.addPrimitive("Fixation.get-scene", new org.nlogo.extensions.chrest.domainSpecifics.fixation.GetScene());
    primitiveManager.addPrimitive("Fixation.has-been-performed?", new org.nlogo.extensions.chrest.domainSpecifics.fixation.HasBeenPerformed());
    
    //jchrest.lib.domainSpecifics.Scene primitives.
    primitiveManager.addPrimitive("Scene.new", new org.nlogo.extensions.chrest.domainSpecifics.scene.New());
    primitiveManager.addPrimitive("Scene.get-as-list-pattern", new org.nlogo.extensions.chrest.domainSpecifics.scene.GetAsListPattern());
    primitiveManager.addPrimitive("Scene.get-as-netlogo-list", new org.nlogo.extensions.chrest.domainSpecifics.scene.GetAsNetlogoList());
    primitiveManager.addPrimitive("Scene.get-blind-square-token", new org.nlogo.extensions.chrest.domainSpecifics.scene.GetBlindSquareToken());
    primitiveManager.addPrimitive("Scene.get-creator-token", new org.nlogo.extensions.chrest.domainSpecifics.scene.GetCreatorToken());
    primitiveManager.addPrimitive("Scene.get-domain-specific-col-from-scene-specific-col", new org.nlogo.extensions.chrest.domainSpecifics.scene.GetDomainSpecificColFromSceneSpecificCol());
    primitiveManager.addPrimitive("Scene.get-domain-specific-row-from-scene-specific-row", new org.nlogo.extensions.chrest.domainSpecifics.scene.GetDomainSpecificRowFromSceneSpecificRow());
    primitiveManager.addPrimitive("Scene.get-empty-square-token", new org.nlogo.extensions.chrest.domainSpecifics.scene.GetEmptySquareToken());
    primitiveManager.addPrimitive("Scene.get-location-of-creator", new org.nlogo.extensions.chrest.domainSpecifics.scene.GetLocationOfCreator());
    primitiveManager.addPrimitive("Scene.get-scene-object-locations", new org.nlogo.extensions.chrest.domainSpecifics.scene.GetSceneObjectLocations());
    primitiveManager.addPrimitive("Scene.get-scene-specific-col-from-domain-specific-col", new org.nlogo.extensions.chrest.domainSpecifics.scene.GetSceneSpecificColFromDomainSpecificCol());
    primitiveManager.addPrimitive("Scene.get-scene-specific-row-from-domain-specific-row", new org.nlogo.extensions.chrest.domainSpecifics.scene.GetSceneSpecificRowFromDomainSpecificRow());
    primitiveManager.addPrimitive("Scene.get-creator-token", new org.nlogo.extensions.chrest.domainSpecifics.scene.GetCreatorToken());
    primitiveManager.addPrimitive("Scene.get-square-contents", new org.nlogo.extensions.chrest.domainSpecifics.scene.GetSquareContents());
    
    //jchrest.domainSpecifics.SceneObject primitives.
    primitiveManager.addPrimitive("SceneObject.get-identifier", new org.nlogo.extensions.chrest.domainSpecifics.sceneObject.GetIdentifier());
    primitiveManager.addPrimitive("SceneObject.get-object-type", new org.nlogo.extensions.chrest.domainSpecifics.sceneObject.GetObjectType());
    
    //jchrest.domainSpecifics.tileworld.TileworldDomain primitives
    primitiveManager.addPrimitive("TileworldDomain.get-hole-token", new org.nlogo.extensions.chrest.domainSpecifics.tileworld.GetHoleSceneObjectTypeToken());
    primitiveManager.addPrimitive("TileworldDomain.get-opponent-token", new org.nlogo.extensions.chrest.domainSpecifics.tileworld.GetOpponentSceneObjectTypeToken());
    primitiveManager.addPrimitive("TileworldDomain.get-tile-token", new org.nlogo.extensions.chrest.domainSpecifics.tileworld.GetTileSceneObjectTypeToken());
    
    //jchrest.lib.ItemSquarePattern primitives
    primitiveManager.addPrimitive("ItemSquarePattern.new", new org.nlogo.extensions.chrest.lib.itemSquarePattern.New());
    primitiveManager.addPrimitive("ItemSquarePattern.get-column", new org.nlogo.extensions.chrest.lib.itemSquarePattern.GetColumn());
    primitiveManager.addPrimitive("ItemSquarePattern.get-item", new org.nlogo.extensions.chrest.lib.itemSquarePattern.GetItem());
    primitiveManager.addPrimitive("ItemSquarePattern.get-as-string", new org.nlogo.extensions.chrest.lib.itemSquarePattern.GetAsString());
    primitiveManager.addPrimitive("ItemSquarePattern.get-row", new org.nlogo.extensions.chrest.lib.itemSquarePattern.GetRow());
    
    //jchrest.lib.ListPattern primitives
    primitiveManager.addPrimitive("ListPattern.new", new org.nlogo.extensions.chrest.lib.listPattern.New());
    primitiveManager.addPrimitive("ListPattern.get-as-netlogo-list", new org.nlogo.extensions.chrest.lib.listPattern.GetAsNetlogoList());
    primitiveManager.addPrimitive("ListPattern.get-as-string", new org.nlogo.extensions.chrest.lib.listPattern.GetAsString());
    primitiveManager.addPrimitive("ListPattern.empty?", new org.nlogo.extensions.chrest.lib.listPattern.IsEmpty());
    primitiveManager.addPrimitive("ListPattern.remove", new org.nlogo.extensions.chrest.lib.listPattern.Remove());
    primitiveManager.addPrimitive("ListPattern.remove-blind-empty-and-unknown-patches", new org.nlogo.extensions.chrest.lib.listPattern.RemoveBlindEmptyAndUnknownPatches());
    
    //jchrest.lib.Modality primitives
    primitiveManager.addPrimitive("Modality.get-modalities", new org.nlogo.extensions.chrest.lib.modality.Values());
    
    //jchrest.lib.Pattern primitives
    primitiveManager.addPrimitive("Pattern.create-number-pattern", new org.nlogo.extensions.chrest.lib.pattern.CreateNumberPattern());
    primitiveManager.addPrimitive("Pattern.get-subclasses", new org.nlogo.extensions.chrest.lib.pattern.GetSubclasses());
    
    //jchrest.lib.ReinforcementLearning primitives
    primitiveManager.addPrimitive("ReinforcementLearning.get-theory-names", new org.nlogo.extensions.chrest.lib.reinforcementLearning.GetTheoryNames());
    primitiveManager.addPrimitive("ReinforcementLearning.values", new org.nlogo.extensions.chrest.lib.reinforcementLearning.Values());
    
    //jchrest.lib.Square primitives
    primitiveManager.addPrimitive("Square.get-column", new org.nlogo.extensions.chrest.lib.square.GetColumn());
    primitiveManager.addPrimitive("Square.get-row", new org.nlogo.extensions.chrest.lib.square.GetRow());

    //jchrest.lib.VisualSpatialFieldObject primitives
    primitiveManager.addPrimitive("VisualSpatialFieldObject.get-identifier", new org.nlogo.extensions.chrest.lib.visualSpatialFieldObject.GetIdentifier());
    primitiveManager.addPrimitive("VisualSpatialFieldObject.get-object-type", new org.nlogo.extensions.chrest.lib.visualSpatialFieldObject.GetObjectType());
    primitiveManager.addPrimitive("VisualSpatialFieldObject.get-unknown-square-token", new org.nlogo.extensions.chrest.lib.visualSpatialFieldObject.GetUnknownSquareToken());
  }
}