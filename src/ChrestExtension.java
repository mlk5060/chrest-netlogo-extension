import org.nlogo.api.DefaultClassManager;
import org.nlogo.api.PrimitiveManager;

/**
 * Primitive manager for CHREST Netlogo extension.
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class ChrestExtension extends DefaultClassManager {

  @Override
  public void load(PrimitiveManager primitiveManager) {
    
    //CHREST primitives
    primitiveManager.addPrimitive("associate-patterns", new AssociatePatterns());
    primitiveManager.addPrimitive("create-item-square-pattern", new CreateItemSquarePattern());
    primitiveManager.addPrimitive("create-number-pattern", new CreateNumberPattern());
    primitiveManager.addPrimitive("get-add-link-time", new GetAddLinkTime());
    primitiveManager.addPrimitive("get-attention-clock", new GetAttentionClock());
    primitiveManager.addPrimitive("get-discrimination-time", new GetDiscriminationTime());
    primitiveManager.addPrimitive("get-familiarisation-time", new GetFamiliarisationTime());
    primitiveManager.addPrimitive("get-learning-clock", new GetLearningClock());
    primitiveManager.addPrimitive("get-ltm-modality-avg-depth", new LtmModalityAvgDepth());
    primitiveManager.addPrimitive("get-ltm-modality-num-action-links", new LtmModalityNumActionLinks());
    primitiveManager.addPrimitive("get-ltm-modality-size", new LtmModalitySize());
    primitiveManager.addPrimitive("get-stm-contents-by-modality", new GetStmContentsByModality());
    primitiveManager.addPrimitive("get-stm-modality-size", new StmModalitySize());
    primitiveManager.addPrimitive("instantiate-chrest-in-turtle", new InstantiateChrestInTurtle());
    primitiveManager.addPrimitive("recognise-and-learn-pattern", new RecogniseAndLearnPattern());
    primitiveManager.addPrimitive("recognise-pattern-and-return-patterns-of-specified-modality", new RecognisePatternAndReturnPatternsOfSpecifiedModality());
    primitiveManager.addPrimitive("reinforce-action-link", new ReinforceActionLink());
    primitiveManager.addPrimitive("save-ltm-network-image", new SaveLtmNetworkImage());
    primitiveManager.addPrimitive("set-add-link-time", new SetAddLinkTime());
    primitiveManager.addPrimitive("set-discrimination-time", new SetDiscriminationTime());
    primitiveManager.addPrimitive("set-familiarisation-time", new SetFamiliarisationTime());
    
    //Mind's eye primitives
    primitiveManager.addPrimitive("get-minds-eye-scene", new GetMindsEyeScene());
    primitiveManager.addPrimitive("instantiate-minds-eye", new InstantiateMindsEye());
    primitiveManager.addPrimitive("move-objects-in-minds-eye", new MoveObjectsInMindsEye());
    
    //Reinforcement theory primitives
    primitiveManager.addPrimitive("get-reinforcement-learning-theories", new GetReinforcementLearningTheories());
    primitiveManager.addPrimitive("get-reinforcement-learning-theory", new GetReinforcementLearningTheory());
    primitiveManager.addPrimitive("set-reinforcement-learning-theory", new SetReinforcementLearningTheory());
    
    //Perception primitives.
    primitiveManager.addPrimitive("set-current-scene", new SetCurrentScene());
    primitiveManager.addPrimitive("get-current-scene", new GetCurrentScene());
    primitiveManager.addPrimitive("learn-current-scene", new LearnCurrentScene());
  }
}