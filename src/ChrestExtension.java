
import org.nlogo.api.DefaultClassManager;
import org.nlogo.api.PrimitiveManager;

/**
 * Primitive manager for CHREST Netlogo extension.
 * 
 * @author Martyn Lloyd-Kelly <mlk5060@liverpool.ac.uk>
 */
public class ChrestExtension extends DefaultClassManager {

  @Override
  public void load(PrimitiveManager primitiveManager) {
    primitiveManager.addPrimitive("associate-patterns", new AssociatePatterns());
    primitiveManager.addPrimitive("create-item-square-pattern", new CreateItemSquarePattern());
    primitiveManager.addPrimitive("create-number-pattern", new CreateNumberPattern());
    primitiveManager.addPrimitive("get-action-links", new GetActionLinks());
    primitiveManager.addPrimitive("get-add-link-time", new GetAddLinkTime());
    primitiveManager.addPrimitive("get-chrest-clock", new GetChrestClock());
    primitiveManager.addPrimitive("get-discrimination-time", new GetDiscriminationTime());
    primitiveManager.addPrimitive("get-familiarisation-time", new GetFamiliarisationTime());
    primitiveManager.addPrimitive("get-ltm-modality-num-action-links", new LtmModalityNumActionLinks());
    primitiveManager.addPrimitive("get-ltm-modality-avg-depth", new LtmModalityAvgDepth());
    primitiveManager.addPrimitive("get-ltm-modality-size", new LtmModalitySize());
    primitiveManager.addPrimitive("get-reinforcement-learning-theories", new GetReinforcementLearningTheories());
    primitiveManager.addPrimitive("get-reinforcement-learning-theory", new GetReinforcementLearningTheory());
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
    primitiveManager.addPrimitive("set-reinforcement-learning-theory", new SetReinforcementLearningTheory());
  }
}