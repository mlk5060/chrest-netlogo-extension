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
  public void load(PrimitiveManager pm) {
    
    //jchrest.architecture.Chrest primitives
    pm.addPrimitive("get-add-production-time", new org.nlogo.extensions.chrest.architecture.chrest.GetAddProductionTime());
    pm.addPrimitive("get-attention-clock", new org.nlogo.extensions.chrest.architecture.chrest.GetAttentionClock());
    pm.addPrimitive("get-cognition-clock", new org.nlogo.extensions.chrest.architecture.chrest.GetCognitionClock());
    pm.addPrimitive("get-discrimination-time", new org.nlogo.extensions.chrest.architecture.chrest.GetDiscriminationTime());
    pm.addPrimitive("get-familiarisation-time", new org.nlogo.extensions.chrest.architecture.chrest.GetFamiliarisationTime());
    pm.addPrimitive("get-ltm-avg-depth", new org.nlogo.extensions.chrest.architecture.chrest.GetLtmAverageDepth());
    pm.addPrimitive("get-ltm-modality-size", new org.nlogo.extensions.chrest.architecture.chrest.GetLtmModalitySize());
    pm.addPrimitive("get-ltm-size", new org.nlogo.extensions.chrest.architecture.chrest.GetLtmSize());
    pm.addPrimitive("get-perceiver", new org.nlogo.extensions.chrest.architecture.chrest.GetPerceiver());
    pm.addPrimitive("get-production-count", new org.nlogo.extensions.chrest.architecture.chrest.GetProductionCount());
    pm.addPrimitive("get-reinforcement-learning-theory", new org.nlogo.extensions.chrest.architecture.chrest.GetReinforcementLearningTheory());
    pm.addPrimitive("get-stm", new org.nlogo.extensions.chrest.architecture.chrest.GetStm());
    pm.addPrimitive("get-visual-spatial-field", new org.nlogo.extensions.chrest.architecture.chrest.GetVisualSpatialField());
    pm.addPrimitive("get-visual-spatial-field-as-scene", new org.nlogo.extensions.chrest.architecture.chrest.GetVisualSpatialFieldAsScene());
    pm.addPrimitive("get-visual-spatial-field-object-locations", new org.nlogo.extensions.chrest.architecture.chrest.GetVisualSpatialFieldObjectLocations());
    pm.addPrimitive("is-attention-free?", new org.nlogo.extensions.chrest.architecture.chrest.IsAttentionFree());
    pm.addPrimitive("learn-production", new org.nlogo.extensions.chrest.architecture.chrest.LearnProduction());
    pm.addPrimitive("move-visual-spatial-field-objects", new org.nlogo.extensions.chrest.architecture.chrest.MoveObjectsInVisualSpatialField());
    pm.addPrimitive("new", new org.nlogo.extensions.chrest.architecture.chrest.New());
    pm.addPrimitive("recognise", new org.nlogo.extensions.chrest.architecture.chrest.Recognise());
    pm.addPrimitive("recognise-and-learn", new org.nlogo.extensions.chrest.architecture.chrest.RecogniseAndLearn());
    pm.addPrimitive("reinforce-production", new org.nlogo.extensions.chrest.architecture.chrest.ReinforceProduction());
    pm.addPrimitive("save-ltm-network-image", new org.nlogo.extensions.chrest.architecture.chrest.SaveLtmNetworkImage());
    pm.addPrimitive("schedule-or-make-next-fixation", new org.nlogo.extensions.chrest.architecture.chrest.ScheduleOrMakeNextFixation());
    pm.addPrimitive("set-add-production-time", new org.nlogo.extensions.chrest.architecture.chrest.SetAddProductionTime());
    pm.addPrimitive("set-discrimination-time", new org.nlogo.extensions.chrest.architecture.chrest.SetDiscriminationTime());
    pm.addPrimitive("set-domain", new org.nlogo.extensions.chrest.architecture.chrest.SetDomain());
    pm.addPrimitive("set-familiarisation-time", new org.nlogo.extensions.chrest.architecture.chrest.SetFamiliarisationTime());
    pm.addPrimitive("set-recognised-visual-spatial-field-object-lifespan", new org.nlogo.extensions.chrest.architecture.chrest.SetRecognisedVisualSpatialFieldObjectLifespan());
    pm.addPrimitive("set-reinforcement-learning-theory", new org.nlogo.extensions.chrest.architecture.chrest.SetReinforcementLearningTheory());
    pm.addPrimitive("set-time-to-access-visual-spatial-field", new org.nlogo.extensions.chrest.architecture.chrest.SetTimeToAccessVisualSpatialField());
    pm.addPrimitive("set-time-to-encode-recognised-scene-object-as-visual-spatial-field-object", new org.nlogo.extensions.chrest.architecture.chrest.SetTimeToEncodeRecognisedSceneObjectAsVisualSpatialFieldObject());
    pm.addPrimitive("set-time-to-encode-unrecognised-empty-square-scene-object-as-visual-spatial-field-object", new org.nlogo.extensions.chrest.architecture.chrest.SetTimeToEncodeUnrecognisedEmptySquareSceneObjectAsVisualSpatialFieldObject());
    pm.addPrimitive("set-time-to-encode-unrecognised-non-empty-square-scene-object-as-visual-spatial-field-object", new org.nlogo.extensions.chrest.architecture.chrest.SetTimeToEncodeUnrecognisedNonEmptySquareSceneObjectAsVisualSpatialFieldObject());
    pm.addPrimitive("set-time-to-move-visual-spatial-field-object", new org.nlogo.extensions.chrest.architecture.chrest.SetTimeToMoveVisualSpatialFieldObject());
    pm.addPrimitive("set-time-to-process-unrecognised-scene-object-during-visual-spatial-field-construction", new org.nlogo.extensions.chrest.architecture.chrest.SetTimeToProcessUnrecognisedSceneObjectDuringVisualSpatialFieldConstruction());
    pm.addPrimitive("set-unrecognised-visual-spatial-field-object-lifespan", new org.nlogo.extensions.chrest.architecture.chrest.SetUnrecognisedVisualSpatialFieldObjectLifespan());
    pm.addPrimitive("turn-off-debugging", new org.nlogo.extensions.chrest.architecture.chrest.TurnOffDebugging());
    pm.addPrimitive("turn-on-debugging", new org.nlogo.extensions.chrest.architecture.chrest.TurnOnDebugging());
    
    //jchrest.architecture.Node primitives
    pm.addPrimitive("Node.get-all-information", new org.nlogo.extensions.chrest.architecture.node.GetAllInformation());
    pm.addPrimitive("Node.get-contents", new org.nlogo.extensions.chrest.architecture.node.GetContents());
    pm.addPrimitive("Node.get-filled-slots", new org.nlogo.extensions.chrest.architecture.node.GetFilledSlots());
    pm.addPrimitive("Node.get-image", new org.nlogo.extensions.chrest.architecture.node.GetImage());
    pm.addPrimitive("Node.get-productions", new org.nlogo.extensions.chrest.architecture.node.GetProductions());
    pm.addPrimitive("Node.get-reference", new org.nlogo.extensions.chrest.architecture.node.GetReference());
    pm.addPrimitive("Node.is-root-node?", new org.nlogo.extensions.chrest.architecture.node.IsRootNode());
    pm.addPrimitive("Node.is-template?", new org.nlogo.extensions.chrest.architecture.node.IsTemplate());
    
    //jchrest.architecture.Perceiver primitives.
    pm.addPrimitive("Perceiver.get-fixation-field-of-view", new org.nlogo.extensions.chrest.architecture.perceiver.GetFixationFieldOfView());
    pm.addPrimitive("Perceiver.get-fixations", new org.nlogo.extensions.chrest.architecture.perceiver.GetFixations());
    pm.addPrimitive("Perceiver.get-fixations-performed", new org.nlogo.extensions.chrest.architecture.perceiver.GetFixationsPerformed());
    pm.addPrimitive("Perceiver.get-objects-seen-in-fixation-field-of-view", new org.nlogo.extensions.chrest.architecture.perceiver.GetObjectsSeenInFixationFieldOfView());
    
    //jchrest.architecture.Stm primitives
    pm.addPrimitive("Stm.get-count", new org.nlogo.extensions.chrest.architecture.stm.GetCount());
    pm.addPrimitive("Stm.get-contents", new org.nlogo.extensions.chrest.architecture.stm.GetContents());
    
    //jchrest.architecture.VisualSpatialField primitives
    pm.addPrimitive("VisualSpatialField.get-coordinate-contents", new org.nlogo.extensions.chrest.architecture.visualSpatialField.GetCoordinateContents());
    pm.addPrimitive("VisualSpatialField.get-height", new org.nlogo.extensions.chrest.architecture.visualSpatialField.GetHeight());
    pm.addPrimitive("VisualSpatialField.get-object-locations", new org.nlogo.extensions.chrest.architecture.visualSpatialField.GetObjectLocations());
    pm.addPrimitive("VisualSpatialField.get-width", new org.nlogo.extensions.chrest.architecture.visualSpatialField.GetWidth());
    
    //jchrest.domainSpecifics.Fixation primitives
    pm.addPrimitive("Fixation.get-column-fixated-on", new org.nlogo.extensions.chrest.domainSpecifics.fixation.GetColumnFixatedOn());
    pm.addPrimitive("Fixation.get-object-seen", new org.nlogo.extensions.chrest.domainSpecifics.fixation.GetObjectSeen());
    pm.addPrimitive("Fixation.get-row-fixated-on", new org.nlogo.extensions.chrest.domainSpecifics.fixation.GetRowFixatedOn());
    pm.addPrimitive("Fixation.get-scene", new org.nlogo.extensions.chrest.domainSpecifics.fixation.GetScene());
    pm.addPrimitive("Fixation.has-been-performed?", new org.nlogo.extensions.chrest.domainSpecifics.fixation.HasBeenPerformed());
    pm.addPrimitive("Fixation.to-string", new org.nlogo.extensions.chrest.domainSpecifics.fixation.ToString());
    
    //jchrest.lib.domainSpecifics.Scene primitives.
    pm.addPrimitive("Scene.new", new org.nlogo.extensions.chrest.domainSpecifics.scene.New());
    pm.addPrimitive("Scene.get-as-list-pattern", new org.nlogo.extensions.chrest.domainSpecifics.scene.GetAsListPattern());
    pm.addPrimitive("Scene.get-as-netlogo-list", new org.nlogo.extensions.chrest.domainSpecifics.scene.GetAsNetlogoList());
    pm.addPrimitive("Scene.get-blind-square-token", new org.nlogo.extensions.chrest.domainSpecifics.scene.GetBlindSquareToken());
    pm.addPrimitive("Scene.get-creator-token", new org.nlogo.extensions.chrest.domainSpecifics.scene.GetCreatorToken());
    pm.addPrimitive("Scene.get-domain-specific-col-from-scene-specific-col", new org.nlogo.extensions.chrest.domainSpecifics.scene.GetDomainSpecificColFromSceneSpecificCol());
    pm.addPrimitive("Scene.get-domain-specific-row-from-scene-specific-row", new org.nlogo.extensions.chrest.domainSpecifics.scene.GetDomainSpecificRowFromSceneSpecificRow());
    pm.addPrimitive("Scene.get-empty-square-token", new org.nlogo.extensions.chrest.domainSpecifics.scene.GetEmptySquareToken());
    pm.addPrimitive("Scene.get-location-of-creator", new org.nlogo.extensions.chrest.domainSpecifics.scene.GetLocationOfCreator());
    pm.addPrimitive("Scene.get-name", new org.nlogo.extensions.chrest.domainSpecifics.scene.GetName());
    pm.addPrimitive("Scene.get-scene-object-locations", new org.nlogo.extensions.chrest.domainSpecifics.scene.GetSceneObjectLocations());
    pm.addPrimitive("Scene.get-scene-specific-col-from-domain-specific-col", new org.nlogo.extensions.chrest.domainSpecifics.scene.GetSceneSpecificColFromDomainSpecificCol());
    pm.addPrimitive("Scene.get-scene-specific-row-from-domain-specific-row", new org.nlogo.extensions.chrest.domainSpecifics.scene.GetSceneSpecificRowFromDomainSpecificRow());
    pm.addPrimitive("Scene.get-square-contents", new org.nlogo.extensions.chrest.domainSpecifics.scene.GetSquareContents());
    
    //jchrest.domainSpecifics.SceneObject primitives.
    pm.addPrimitive("SceneObject.get-identifier", new org.nlogo.extensions.chrest.domainSpecifics.sceneObject.GetIdentifier());
    pm.addPrimitive("SceneObject.get-object-type", new org.nlogo.extensions.chrest.domainSpecifics.sceneObject.GetObjectType());
    
    //jchrest.domainSpecifics.tileworld.TileworldDomain primitives
    pm.addPrimitive("TileworldDomain.get-hole-token", new org.nlogo.extensions.chrest.domainSpecifics.tileworld.GetHoleSceneObjectTypeToken());
    pm.addPrimitive("TileworldDomain.get-opponent-token", new org.nlogo.extensions.chrest.domainSpecifics.tileworld.GetOpponentSceneObjectTypeToken());
    pm.addPrimitive("TileworldDomain.get-tile-token", new org.nlogo.extensions.chrest.domainSpecifics.tileworld.GetTileSceneObjectTypeToken());
    
    //jchrest.lib.ItemSquarePattern primitives
    pm.addPrimitive("ItemSquarePattern.new", new org.nlogo.extensions.chrest.lib.itemSquarePattern.New());
    pm.addPrimitive("ItemSquarePattern.get-column", new org.nlogo.extensions.chrest.lib.itemSquarePattern.GetColumn());
    pm.addPrimitive("ItemSquarePattern.get-item", new org.nlogo.extensions.chrest.lib.itemSquarePattern.GetItem());
    pm.addPrimitive("ItemSquarePattern.get-as-string", new org.nlogo.extensions.chrest.lib.itemSquarePattern.GetAsString());
    pm.addPrimitive("ItemSquarePattern.get-row", new org.nlogo.extensions.chrest.lib.itemSquarePattern.GetRow());
    
    //jchrest.lib.ListPattern primitives
    pm.addPrimitive("ListPattern.new", new org.nlogo.extensions.chrest.lib.listPattern.New());
    pm.addPrimitive("ListPattern.get-as-netlogo-list", new org.nlogo.extensions.chrest.lib.listPattern.GetAsNetlogoList());
    pm.addPrimitive("ListPattern.get-as-string", new org.nlogo.extensions.chrest.lib.listPattern.GetAsString());
    pm.addPrimitive("ListPattern.empty?", new org.nlogo.extensions.chrest.lib.listPattern.IsEmpty());
    pm.addPrimitive("ListPattern.remove", new org.nlogo.extensions.chrest.lib.listPattern.Remove());
    pm.addPrimitive("ListPattern.remove-blind-empty-and-unknown-patches", new org.nlogo.extensions.chrest.lib.listPattern.RemoveBlindEmptyAndUnknownPatches());
    
    //jchrest.lib.Modality primitives
    pm.addPrimitive("Modality.get-modalities", new org.nlogo.extensions.chrest.lib.modality.Values());
    
    //jchrest.lib.Pattern primitives
    pm.addPrimitive("Pattern.create-number-pattern", new org.nlogo.extensions.chrest.lib.pattern.CreateNumberPattern());
    pm.addPrimitive("Pattern.get-subclasses", new org.nlogo.extensions.chrest.lib.pattern.GetSubclasses());
    
    //jchrest.lib.ReinforcementLearning primitives
    pm.addPrimitive("ReinforcementLearning.get-theory-names", new org.nlogo.extensions.chrest.lib.reinforcementLearning.GetTheoryNames());
    pm.addPrimitive("ReinforcementLearning.values", new org.nlogo.extensions.chrest.lib.reinforcementLearning.Values());
    
    //jchrest.lib.Square primitives
    pm.addPrimitive("Square.get-column", new org.nlogo.extensions.chrest.lib.square.GetColumn());
    pm.addPrimitive("Square.get-row", new org.nlogo.extensions.chrest.lib.square.GetRow());

    //jchrest.lib.VisualSpatialFieldObject primitives
    pm.addPrimitive("VisualSpatialFieldObject.get-identifier", new org.nlogo.extensions.chrest.lib.visualSpatialFieldObject.GetIdentifier());
    pm.addPrimitive("VisualSpatialFieldObject.get-object-type", new org.nlogo.extensions.chrest.lib.visualSpatialFieldObject.GetObjectType());
    pm.addPrimitive("VisualSpatialFieldObject.get-unknown-square-token", new org.nlogo.extensions.chrest.lib.visualSpatialFieldObject.GetUnknownSquareToken());
    
    //Model tests
    pm.addPrimitive("TileworldModelTests#are-visual-spatial-field-squares-valid-at-time?.test", new org.nlogo.extensions.chrest.ModelTests.Tileworld.AreVisualSpatialFieldSquaresValidAtTime.Test());
    pm.addPrimitive("TileworldModelTests#deliberate.test-1", new org.nlogo.extensions.chrest.ModelTests.Tileworld.Deliberate.Test1());
    pm.addPrimitive("TileworldModelTests#deliberate.test-2", new org.nlogo.extensions.chrest.ModelTests.Tileworld.Deliberate.Test2());
    pm.addPrimitive("TileworldModelTests#deliberate.test-3", new org.nlogo.extensions.chrest.ModelTests.Tileworld.Deliberate.Test3());
    pm.addPrimitive("TileworldModelTests#deliberate.test-4", new org.nlogo.extensions.chrest.ModelTests.Tileworld.Deliberate.Test4());
    pm.addPrimitive("TileworldModelTests#roulette-selection.setup", new org.nlogo.extensions.chrest.ModelTests.Tileworld.RouletteSelection.Setup());
    pm.addPrimitive("TileworldModelTests#generate-visual-spatial-field-moves.test-1", new org.nlogo.extensions.chrest.ModelTests.Tileworld.GenerateVisualSpatialFieldMoves.Test1());
    pm.addPrimitive("TileworldModelTests#generate-visual-spatial-field-moves.test-2", new org.nlogo.extensions.chrest.ModelTests.Tileworld.GenerateVisualSpatialFieldMoves.Test2());
    pm.addPrimitive("TileworldModelTests#generate-visual-spatial-field-moves.test-3", new org.nlogo.extensions.chrest.ModelTests.Tileworld.GenerateVisualSpatialFieldMoves.Test3());
    pm.addPrimitive("TileworldModelTests#generate-visual-spatial-field-moves.test-4", new org.nlogo.extensions.chrest.ModelTests.Tileworld.GenerateVisualSpatialFieldMoves.Test4());
    pm.addPrimitive("TileworldModelTests#generate-visual-spatial-field-moves.test-5", new org.nlogo.extensions.chrest.ModelTests.Tileworld.GenerateVisualSpatialFieldMoves.Test5());
    pm.addPrimitive("TileworldModelTests#learn-action.test", new org.nlogo.extensions.chrest.ModelTests.Tileworld.LearnAction.Test());
  }
}