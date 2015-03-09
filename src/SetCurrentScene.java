
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import jchrest.lib.Scene;
import org.nlogo.agent.AgentSet;
import org.nlogo.agent.Patch;
import org.nlogo.agent.Turtle;
import org.nlogo.agent.World;
import org.nlogo.api.AgentException;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultCommand;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.LogoList;
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
 * 2            String          The name of the scene (first parameter used by 
 *                              {@link jchrest.lib.Scene#Scene(java.lang.String, int, int)}).
 * 3            List            A 2D list of strings that act as key-value 
 *                              pairs.  Keys are breed names and values are 
 *                              identifiers that should be used to instantiate
 *                              a {@link jchrest.lib.Scene} instance according
 *                              to the relevant domain found in 
 *                              {@link jchrest.lib}.
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class SetCurrentScene extends DefaultCommand {

  @Override
  public Syntax getSyntax(){
    return Syntax.commandSyntax(new int[]{Syntax.BooleanType(), Syntax.StringType(), Syntax.ListType()});
  }
  
  @Override
  public void perform(Argument[] args, Context context) throws ExtensionException, LogoException {
    org.nlogo.agent.Agent callingAgent = BaseExtensionVariablesAndMethods.getAgent(context);
    
    try {
      //Check that the list passed is correct before starting since there may be
      //a number of issues with it given that its user-specified.  Check:
      // 1. That the list is a 2D list of strings.
      // 2. That the first elements in the second-dimension are breed names in
      //    the model.
      //If these checks pass, generate a HashMap version of the list for easy 
      //Scene creation later.
      
      //Create the HashMap that will (hopefully) be populated.
      Map<String, String> breedTokenMap = new HashMap<>();
      
      //Get the breed names in the model.
      World world = (World)context.getAgent().world();
      Set<String> breedsInModel = world.getBreeds().keySet();
      
      //Process the list.
      LogoList breedsAndTokens = args[2].getList();
      for(int i = 0; i < breedsAndTokens.size(); i++){
        Object breedAndToken = breedsAndTokens.get(i);
        if(breedAndToken instanceof LogoList){
          LogoList breedTokenList = (LogoList)breedAndToken;
          Object breed = breedTokenList.get(0);
          if(breed instanceof String){
            String breedName = (String)breed;
            
            //Remember to upper-case the breed name since Netlogo stores 
            //variable names in upper-case.
            breedName = breedName.toUpperCase();
            
            //If breedName is "SELF" or if it isn't but is a specified breed 
            //name, continue.
            if( breedName.equals("SELF") || breedsInModel.contains(breedName) ){
              Object token = breedTokenList.get(1);
              if(token instanceof String){
                breedTokenMap.put( breedName, (String)token );
              }
              else{
                throw new ExtensionException("The second element of element " + i + " in the list passed is not an instance of java.lang.String.");
              }
            }
            else{
              throw new ExtensionException("The first element of element " + i + " in the list passed (" + breedName + ") is not a breed in this model.");
            }
          }
          else{
            throw new ExtensionException("The first element of element " + i + " in the list passed is not an instance of java.lang.String.");
          }
        }
        else{
          throw new ExtensionException("Element " + i + " of the second-dimension in the list passed is not an instance of org.nlogo.api.LogoList.");
        }
      }
      
      //Get the calling agent's sight radius value.
      Double sightRadiusDouble = (Double)callingAgent.getBreedVariable(BaseExtensionVariablesAndMethods.SIGHT_RADIUS_BREED_VAR_NAME);
      int sightRadius = sightRadiusDouble.intValue();
      
      //The scene will consist of sightRadius squares north/east/south/west of
      //the agent's current location.  So multiply the value of sightRadius by 2
      //to get how many squares in total can be seen north/south and east/west
      //and add 1 to take into account the current row/col where the agent is.
      //To illustrate, if the sight radius is set to 2 then the agent can see
      //the following ("A" represents the agent, coordinates are Scene-specific
      //not domain/model-specific):
      //
      // 2  |---|---|---|---|---|
      // 1  |---|---|---|---|---|
      // 0  |---|---|-A-|---|---|
      // -1 |---|---|---|---|---|
      // -2 |---|---|---|---|---|
      //      -2  -1  0   1   2
      Scene scene = new Scene(args[1].getString(), ((sightRadius * 2) + 1), ((sightRadius * 2) + 1) );
  
      //Populate the scene starting from south-west, continuing eastwards then
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
              
              //If the turtle isn't hidden, add it to the scene appropriately
              if( !turtle.hidden() ){
              
                //Set the identifier for this turtle to its ID.
                String identifier = String.valueOf(turtle.id);

                //If the first parameter is false, the identifier should be 
                //overwritten with the token specified for the turtle's breed or
                //the token specified for "SELF" if the turtle seen is the 
                //calling agent.
                if(!args[0].getBooleanValue()){
                  if(turtle.id == callingAgent.id){
                    identifier = breedTokenMap.get("SELF");
                  }
                  else{
                    identifier = breedTokenMap.get(turtle.getBreed().printName());
                  }
                }

                //Convert the domain-specific x/y coordinates into non 
                //domain-specific Scene coordinates.
                int sceneXCor = colOffset + sightRadius;
                int sceneYCor = rowOffset + sightRadius;
                scene.setItem(sceneYCor, sceneXCor, identifier);
              }
            }
          }
        }
      }
      
      //Finally, set the "current-scene" variable to the Scene generated.
      callingAgent.setBreedVariable(BaseExtensionVariablesAndMethods.CURRENT_SCENE_BREED_VAR_NAME, scene);
    } catch (AgentException ex) {
      Logger.getLogger(SetCurrentScene.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
    }
  }
}
