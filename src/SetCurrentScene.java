
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import jchrest.lib.Scene;
import org.nlogo.agent.AgentSet;
import org.nlogo.agent.Patch;
import org.nlogo.agent.Turtle;
import org.nlogo.api.AgentException;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultCommand;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 * Creates a new {@link jchrest.lib.Scene} instance for CHREST to work with to 
 * the calling turtle's "current-scene" turtle variable.
 * 
 * One parameter must be passed when the Netlogo extension primitive that 
 * invokes this class is used in a Netlogo model:
 * 
 * Param #      Data Type       Description
 * -------      ---------       -----------
 * 1            Boolean         Indicates whether the identifier for turtles in
 *                              the scene should be turtle ID's or breed names.
 *                              Pass 'true' for ID's, 'false' for breed names.
 * 
 * @author martyn
 */
public class SetCurrentScene extends DefaultCommand {
  
  
  @Override
  public Syntax getSyntax(){
    return Syntax.commandSyntax(new int[]{Syntax.BooleanType()});
  }
  
  @Override
  public void perform(Argument[] args, Context context) throws ExtensionException, LogoException {
    org.nlogo.agent.Agent callingAgent = BaseExtensionVariablesAndMethods.getAgent(context);
    
    try {
      //Get the calling agent's sight radius value.
      Double sightRadiusDouble = (Double)callingAgent.getBreedVariable("sight-radius".toUpperCase());
      int sightRadius = sightRadiusDouble.intValue();
      
      //The scene will consist of sightRadius squares north/east/south/west of
      //the agent's current location.  So multiply the value of sightRadius by 2
      //to get how many squares in total can be seen north/south and east/west
      //and add 1 to take into account the current row/col where the agent is.
      //To illustrate, if the sight radius is set to 2 then the agent can see
      //the following ("A" represents the agent):
      //
      // 2  |---|---|---|---|---|
      // 1  |---|---|---|---|---|
      // 0  |---|---|-A-|---|---|
      // -1 |---|---|---|---|---|
      // -2 |---|---|---|---|---|
      //      -2  -1  0   1   2
      Scene scene = new Scene("Tileworld scene", ((sightRadius * 2) + 1), ((sightRadius * 2) + 1) );
  
      //Popoulate the scene starting from south-west, continuing eastwards then
      //north until north-east.
      for(int rowOffset = sightRadius * -1; rowOffset <= sightRadius; rowOffset++){
        for(int colOffset = sightRadius * -1; colOffset <= sightRadius; colOffset++){

          //Get the patch and return agents on that patch.
          Patch patch = callingAgent.getPatchAtOffsets(colOffset, rowOffset);
          AgentSet agentsOnPatch = patch.turtlesHereAgentSet();
          
          //If there are agents on the patch, process them accordingly.
          if(!agentsOnPatch.isEmpty()){
            
            //Get an iterator of Turtle instances rather than an iterator of 
            //Agent instances so that the "breed" and "hidden?" values can be 
            //checked.
            Iterator<Turtle> turtles = patch.turtlesHere().iterator();
            while(turtles.hasNext()){
              Turtle turtle = turtles.next();
              
              //TODO: make it so that identifiers are unique.
              String identifier = turtle.getBreed().printName().substring(0, 1);
              
              if(args[0].getBooleanValue()){
                identifier = String.valueOf(turtle.id);
              }

              //If the turtle isn't hidden, add it to the scene appropriately
              if( !turtle.hidden() ){
                int sceneXCor = colOffset + sightRadius;
                int sceneYCor = rowOffset + sightRadius;
                
                if(turtle.id == callingAgent.id){
                  scene.setItem(sceneYCor, sceneXCor, BaseExtensionVariablesAndMethods._selfIdentifierToken);
                }
                else{
                  scene.setItem(sceneYCor, sceneXCor, identifier);
                }
              }
            }
          }
        }
      }
      
      callingAgent.setBreedVariable("current-scene".toUpperCase(), scene);
    } catch (AgentException ex) {
      Logger.getLogger(SetCurrentScene.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
    }
  }
}
