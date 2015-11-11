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
    primitiveManager.addPrimitive("associate-list-patterns", new Chrest.AssociateListPatterns());
    primitiveManager.addPrimitive("get-action-ltm-avg-depth", new Chrest.GetActionLtmAvgDepth());
    primitiveManager.addPrimitive("get-action-ltm-size", new Chrest.GetActionLtmSize());
    primitiveManager.addPrimitive("get-action-stm", new Chrest.GetActionStm());
    primitiveManager.addPrimitive("get-action-stm-node-count", new Chrest.GetActionStmNodeCount());
    primitiveManager.addPrimitive("get-add-link-time", new Chrest.GetAddLinkTime());
    primitiveManager.addPrimitive("get-attention-clock", new Chrest.GetAttentionClock());
    primitiveManager.addPrimitive("get-discrimination-time", new Chrest.GetDiscriminationTime());
    primitiveManager.addPrimitive("get-familiarisation-time", new Chrest.GetFamiliarisationTime());
    primitiveManager.addPrimitive("get-learning-clock", new Chrest.GetLearningClock());
    primitiveManager.addPrimitive("get-production-count", new Chrest.GetProductionCount());
    primitiveManager.addPrimitive("get-reinforcement-learning-theory", new Chrest.GetReinforcementLearningTheory());
    primitiveManager.addPrimitive("get-verbal-ltm-avg-depth", new Chrest.GetVerbalLtmAvgDepth());
    primitiveManager.addPrimitive("get-verbal-ltm-size", new Chrest.GetVerbalLtmSize());
    primitiveManager.addPrimitive("get-verbal-stm", new Chrest.GetVisualStm());
    primitiveManager.addPrimitive("get-verbal-stm-node-count", new Chrest.GetVerbalStmNodeCount());
    primitiveManager.addPrimitive("get-visual-ltm-avg-depth", new Chrest.GetVisualLtmAvgDepth());
    primitiveManager.addPrimitive("get-visual-ltm-size", new Chrest.GetVisualLtmSize());
    primitiveManager.addPrimitive("get-visual-stm", new Chrest.GetVisualStm());
    primitiveManager.addPrimitive("get-visual-stm-node-count", new Chrest.GetVisualStmNodeCount());
    primitiveManager.addPrimitive("instantiate-chrest-in-turtle", new Chrest.InstantiateChrestInTurtle());
    primitiveManager.addPrimitive("learn-scene", new Chrest.LearnScene());
    primitiveManager.addPrimitive("recognise", new Chrest.Recognise());
    primitiveManager.addPrimitive("recognise-and-learn", new Chrest.RecogniseAndLearn());
    primitiveManager.addPrimitive("reinforce-production", new Chrest.ReinforceProduction());
    primitiveManager.addPrimitive("save-ltm-network-image", new Chrest.SaveLtmNetworkImage());
    primitiveManager.addPrimitive("scan-scene", new Chrest.ScanScene());
    primitiveManager.addPrimitive("set-add-link-time", new Chrest.SetAddLinkTime());
    primitiveManager.addPrimitive("set-discrimination-time", new Chrest.SetDiscriminationTime());
    primitiveManager.addPrimitive("set-domain", new Chrest.SetDomain());
    primitiveManager.addPrimitive("set-familiarisation-time", new Chrest.SetFamiliarisationTime());
    primitiveManager.addPrimitive("set-reinforcement-learning-theory", new Chrest.SetReinforcementLearningTheory());
    
    //DomainSpecific primitives
    primitiveManager.addPrimitive("DomainSpecifics.get-current-domain", new DomainSpecifics.GetCurrentDomain());
    primitiveManager.addPrimitive("DomainSpecifics.get-current-domain-name", new DomainSpecifics.GetCurrentDomainName());
    primitiveManager.addPrimitive("DomainSpecifics.get-domains", new DomainSpecifics.GetDeclaredDomains());
    primitiveManager.addPrimitive("DomainSpecifics.normalise-list-pattern", new DomainSpecifics.NormaliseListPattern());
    primitiveManager.addPrimitive("DomainSpecifics.get-salient-patches", new DomainSpecifics.GetSalientPatches());
    
    //DomainSpecifics.TileworldDomain primitives
    primitiveManager.addPrimitive("TileworldDomain.get-hole-identifier", new DomainSpecifics.TileworldDomain.GetHoleIdentifier());
    primitiveManager.addPrimitive("TileworldDomain.get-opponent-identifier", new DomainSpecifics.TileworldDomain.GetOpponentIdentifier());
    primitiveManager.addPrimitive("TileworldDomain.get-tile-identifier", new DomainSpecifics.TileworldDomain.GetTileIdentifier());
  
    //Modality primitives
    primitiveManager.addPrimitive("Modality.get-modalities", new Modality.GetModalities());
    
    //Node primitives
    primitiveManager.addPrimitive("Node.get-image", new Node.GetImage());
    primitiveManager.addPrimitive("Node.get-productions", new Node.GetProductions());
    
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
    primitiveManager.addPrimitive("Perceiver.get-fixation-xcor", new Perceiver.GetFixationXcor());
    primitiveManager.addPrimitive("Perceiver.get-fixation-ycor", new Perceiver.GetFixationYcor());
    primitiveManager.addPrimitive("Perceiver.get-fixations", new Perceiver.GetFixations());
    
    //Reinforcement primitives
    primitiveManager.addPrimitive("ReinforcementLearning.get-theories", new Reinforcement.GetReinforcementLearningTheories());
    
    //Scene primitives.
    primitiveManager.addPrimitive("Scene.new", new Scene.New());
    primitiveManager.addPrimitive("Scene.get-as-list-pattern", new Scene.GetAsListPattern());
    primitiveManager.addPrimitive("Scene.get-as-netlogo-list", new Scene.GetAsNetlogoList());
    primitiveManager.addPrimitive("Scene.get-blind-square-token", new Scene.GetBlindSquareToken());
    primitiveManager.addPrimitive("Scene.get-creator-token", new Scene.GetCreatorToken());
    primitiveManager.addPrimitive("Scene.get-empty-square-token", new Scene.GetEmptySquareToken());
    primitiveManager.addPrimitive("Scene.get-location-of-creator", new Scene.GetLocationOfCreator());
    primitiveManager.addPrimitive("Scene.get-object-locations", new Scene.GetObjectLocations());
    primitiveManager.addPrimitive("Scene.get-self-identifier", new Scene.GetCreatorToken());
    primitiveManager.addPrimitive("Scene.get-square-contents-as-netlogo-list", new Scene.GetSquareContentsAsNetlogoList());
    
    //VisualSpatialField primitives
    primitiveManager.addPrimitive("VisualSpatialField.new", new VisualSpatialField.New());
    primitiveManager.addPrimitive("VisualSpatialField.get-all", new Chrest.GetVisualSpatialFields());
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