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
    primitiveManager.addPrimitive("learn-production", new architecture.Chrest.LearnProduction());
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
    primitiveManager.addPrimitive("Pattern.create-number-pattern", new Pattern.CreateNumberPattern());
    primitiveManager.addPrimitive("Pattern.get-class-names", new Pattern.GetPatternClassNames());
    
    //Pattern.ItemSquare primitives
    primitiveManager.addPrimitive("ItemSquarePattern.new", new Pattern.ItemSquare.New());
    primitiveManager.addPrimitive("ItemSquarePattern.get-column", new Pattern.ItemSquare.GetColumn());
    primitiveManager.addPrimitive("ItemSquarePattern.get-item", new Pattern.ItemSquare.GetItem());
    primitiveManager.addPrimitive("ItemSquarePattern.get-as-string", new Pattern.ItemSquare.GetAsString());
    primitiveManager.addPrimitive("ItemSquarePattern.get-row", new Pattern.ItemSquare.GetRow());
    
    //Pattern.List primitives
    primitiveManager.addPrimitive("ListPattern.new", new Pattern.List.New());
    primitiveManager.addPrimitive("ListPattern.get-as-netlogo-list", new Pattern.List.GetAsNetlogoList());
    primitiveManager.addPrimitive("ListPattern.get-as-string", new Pattern.List.GetAsString());
    primitiveManager.addPrimitive("ListPattern.empty?", new Pattern.List.IsEmpty());
    primitiveManager.addPrimitive("ListPattern.remove", new Pattern.List.Remove());
    primitiveManager.addPrimitive("ListPattern.remove-blind-empty-and-unknown-patches", new Pattern.List.RemoveBlindEmptyAndUnknownPatches());
    
    //Perceiver primitives.
    primitiveManager.addPrimitive("Perceiver.get-fixations", new architecture.Perceiver.GetFixations());
    
    //Reinforcement primitives
    primitiveManager.addPrimitive("ReinforcementLearning.get-theories", new Reinforcement.GetReinforcementLearningTheories());
    
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
    
    //VisualSpatialField primitives
    primitiveManager.addPrimitive("VisualSpatialField.new", new VisualSpatialField.New());
    primitiveManager.addPrimitive("VisualSpatialField.get-all", new architecture.Chrest.GetVisualSpatialFields());
    primitiveManager.addPrimitive("VisualSpatialField.get-as-netlogo-list", new VisualSpatialField.GetAsNetlogoList());
    primitiveManager.addPrimitive("VisualSpatialField.get-as-scene", new VisualSpatialField.GetAsScene());
    primitiveManager.addPrimitive("VisualSpatialField.get-object-locations", new VisualSpatialField.GetObjectLocations());
    primitiveManager.addPrimitive("VisualSpatialField.is-object-on-square?", new VisualSpatialField.IsObjectOnSquare());
    primitiveManager.addPrimitive("VisualSpatialField.move-objects", new VisualSpatialField.MoveObjects());
    
    //VisualSpatialFieldObject primitives
    primitiveManager.addPrimitive("VisualSpatialFieldObject.get-identifier", new VisualSpatialFieldObject.GetIdentifier());
    primitiveManager.addPrimitive("VisualSpatialFieldObject.get-object-class", new VisualSpatialFieldObject.GetObjectClass());
    primitiveManager.addPrimitive("VisualSpatialFieldObject.get-unknown-square-token", new VisualSpatialFieldObject.GetUnknownSquareToken());
  }
}