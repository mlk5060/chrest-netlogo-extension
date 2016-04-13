import org.nlogo.api.DefaultClassManager;
import org.nlogo.api.PrimitiveManager;

/**
 * Primitive manager for CHREST Netlogo extension.
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class ChrestExtension extends DefaultClassManager {
  
  //TODO: Check if the primitive name appears in ExtensionExceptions that are
  //thrown.  If not, try to catch these exceptions in this class and prepend the
  //error message with the relative primitive name.
  
  //TODO: Review and improve javadoc at top of each class file (add {@link} tags
  //etc.).
  
  //TODO: Put all tabular-formatted information in class' javadoc's into actual 
  //HTML tables.

  @Override
  public void load(PrimitiveManager primitiveManager) {
    
    //Chrest primitives
    primitiveManager.addPrimitive("get-add-production-time", new architecture.Chrest.GetAddProductionTime());
    primitiveManager.addPrimitive("get-attention-clock", new architecture.Chrest.GetAttentionClock());
    primitiveManager.addPrimitive("get-cognition-clock", new architecture.Chrest.GetCognitionClock());
    primitiveManager.addPrimitive("get-discrimination-time", new architecture.Chrest.GetDiscriminationTime());
    primitiveManager.addPrimitive("get-familiarisation-time", new architecture.Chrest.GetFamiliarisationTime());
    primitiveManager.addPrimitive("get-ltm-avg-depth", new architecture.Chrest.GetLtmAverageDepth());
    primitiveManager.addPrimitive("get-ltm-size", new architecture.Chrest.GetLtmSize());
    primitiveManager.addPrimitive("get-perceiver", new architecture.Chrest.GetPerceiver());
    primitiveManager.addPrimitive("get-production-count", new architecture.Chrest.GetProductionCount());
    primitiveManager.addPrimitive("get-reinforcement-learning-theory", new architecture.Chrest.GetReinforcementLearningTheory());
    primitiveManager.addPrimitive("get-stm-node-count", new architecture.Stm.GetCount());
    primitiveManager.addPrimitive("get-visual-spatial-fields", new architecture.Chrest.GetVisualSpatialFields());
    primitiveManager.addPrimitive("get-visual-spatial-field-as-scene", new architecture.Chrest.GetVisualSpatialFieldAsScene());
    primitiveManager.addPrimitive("get-visual-spatial-field-object-locations", new architecture.Chrest.GetVisualSpatialFieldObjectLocations());
    primitiveManager.addPrimitive("learn-production", new architecture.Chrest.LearnProduction());
    primitiveManager.addPrimitive("move-visual-spatial-field-objects", new architecture.Chrest.MoveObjectsInVisualSpatialField());
    primitiveManager.addPrimitive("recognise-and-learn", new architecture.Chrest.RecogniseAndLearn());
    primitiveManager.addPrimitive("reinforce-production", new architecture.Chrest.ReinforceProduction());
    primitiveManager.addPrimitive("save-ltm-network-image", new architecture.Chrest.SaveLtmNetworkImage());
    primitiveManager.addPrimitive("schedule-or-make-next-fixation", new architecture.Chrest.ScheduleOrMakeNextFixation());
    primitiveManager.addPrimitive("set-add-production-time", new architecture.Chrest.SetAddProductionTime());
    primitiveManager.addPrimitive("set-discrimination-time", new architecture.Chrest.SetDiscriminationTime());
    primitiveManager.addPrimitive("set-domain", new architecture.Chrest.SetDomain());
    primitiveManager.addPrimitive("set-familiarisation-time", new architecture.Chrest.SetFamiliarisationTime());
    primitiveManager.addPrimitive("set-reinforcement-learning-theory", new architecture.Chrest.SetReinforcementLearningTheory());
    
    //DomainSpecifics.TileworldDomain primitives
    primitiveManager.addPrimitive("TileworldDomain.get-hole-identifier", new domainSpecifics.tileworld.GetHoleIdentifier());
    primitiveManager.addPrimitive("TileworldDomain.get-opponent-identifier", new domainSpecifics.tileworld.GetOpponentIdentifier());
    primitiveManager.addPrimitive("TileworldDomain.get-tile-identifier", new domainSpecifics.tileworld.GetTileIdentifier());
  
    //Modality primitives
    primitiveManager.addPrimitive("Modality.get-modalities", new lib.Modality.Values());
    
    //Node primitives
    primitiveManager.addPrimitive("Node.get-image", new architecture.Node.GetImage());
    primitiveManager.addPrimitive("Node.get-productions", new architecture.Node.GetProductions());
    
    //Pattern primitives
    primitiveManager.addPrimitive("Pattern.create-number-pattern", new lib.Pattern.CreateNumberPattern());
    primitiveManager.addPrimitive("Pattern.get-subclasses", new lib.Pattern.GetSubclasses());
    
    //ItemSquarePattern primitives
    primitiveManager.addPrimitive("ItemSquarePattern.new", new lib.ItemSquarePattern.New());
    primitiveManager.addPrimitive("ItemSquarePattern.get-column", new lib.ItemSquarePattern.GetColumn());
    primitiveManager.addPrimitive("ItemSquarePattern.get-item", new lib.ItemSquarePattern.GetItem());
    primitiveManager.addPrimitive("ItemSquarePattern.get-as-string", new lib.ItemSquarePattern.GetAsString());
    primitiveManager.addPrimitive("ItemSquarePattern.get-row", new lib.ItemSquarePattern.GetRow());
    
    //ListPattern primitives
    primitiveManager.addPrimitive("ListPattern.new", new lib.ListPattern.New());
    primitiveManager.addPrimitive("ListPattern.get-as-netlogo-list", new lib.ListPattern.GetAsNetlogoList());
    primitiveManager.addPrimitive("ListPattern.get-as-string", new lib.ListPattern.GetAsString());
    primitiveManager.addPrimitive("ListPattern.empty?", new lib.ListPattern.IsEmpty());
    primitiveManager.addPrimitive("ListPattern.remove", new lib.ListPattern.Remove());
    primitiveManager.addPrimitive("ListPattern.remove-blind-empty-and-unknown-patches", new lib.ListPattern.RemoveBlindEmptyAndUnknownPatches());
    
    //Perceiver primitives.
    primitiveManager.addPrimitive("Perceiver.get-fixations", new architecture.Perceiver.GetFixations());
    
    //Reinforcement primitives
    primitiveManager.addPrimitive("ReinforcementLearning.get-theory-names", new lib.ReinforcementLearning.GetTheoryNames());
    primitiveManager.addPrimitive("ReinforcementLearning.values", new lib.ReinforcementLearning.Values());
    
    //Scene primitives.
    primitiveManager.addPrimitive("Scene.new", new domainSpecifics.Scene.New());
    primitiveManager.addPrimitive("Scene.get-as-list-pattern", new domainSpecifics.Scene.GetAsListPattern());
    primitiveManager.addPrimitive("Scene.get-as-netlogo-list", new domainSpecifics.Scene.GetAsNetlogoList());
    primitiveManager.addPrimitive("Scene.get-blind-square-token", new domainSpecifics.Scene.GetBlindSquareToken());
    primitiveManager.addPrimitive("Scene.get-creator-token", new domainSpecifics.Scene.GetCreatorToken());
    primitiveManager.addPrimitive("Scene.get-empty-square-token", new domainSpecifics.Scene.GetEmptySquareToken());
    primitiveManager.addPrimitive("Scene.get-location-of-creator", new domainSpecifics.Scene.GetLocationOfCreator());
    primitiveManager.addPrimitive("Scene.get-scene-object-locations", new domainSpecifics.Scene.GetSceneObjectLocations());
    primitiveManager.addPrimitive("Scene.get-creator-token", new domainSpecifics.Scene.GetCreatorToken());
    primitiveManager.addPrimitive("Scene.get-square-contents", new domainSpecifics.Scene.GetSquareContents());
    
    //VisualSpatialFieldObject primitives
    primitiveManager.addPrimitive("VisualSpatialFieldObject.get-identifier", new lib.VisualSpatialFieldObject.GetIdentifier());
    primitiveManager.addPrimitive("VisualSpatialFieldObject.get-object-type", new lib.VisualSpatialFieldObject.GetObjectType());
    primitiveManager.addPrimitive("VisualSpatialFieldObject.get-unknown-square-token", new lib.VisualSpatialFieldObject.GetUnknownSquareToken());
  }
}