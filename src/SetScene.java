
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Sets the current scene for CHREST to work with using Netlogo information.
 * 
 * @author martyn
 */
public class SetScene extends DefaultCommand {
  
  @Override
  public void perform(Argument[] args, Context context) throws ExtensionException, LogoException {
    org.nlogo.agent.Agent callingAgent = BaseExtensionVariablesAndMethods.getAgent(context);
    
    try {
      //Get the calling agent's sight radius value.
      int sightRadius = (int)callingAgent.getBreedVariable("sight-radius");
      
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
      for(int yCorOffset = sightRadius * -1; yCorOffset <= sightRadius; yCorOffset++){
        for(int xCorOffset = sightRadius * -1; xCorOffset <= sightRadius; xCorOffset++){

          //Get the patch and return agents on that patch.
          Patch patch = callingAgent.getPatchAtOffsets(xCorOffset, yCorOffset);
          AgentSet agentsOnPatch = patch.turtlesHereAgentSet();
          
          //If there are agents on the patch, process them accordingly.
          if(!agentsOnPatch.isEmpty()){
            
            //Get an iterator of Turtle instances rather than an iterator of 
            //Agent instances so that the "breed" and "hidden?" values can be 
            //checked.
            Iterator<Turtle> turtles = patch.turtlesHere().iterator();
            while(turtles.hasNext()){
              Turtle turtle = turtles.next();
              String turtleBreed = turtle.getBreed().printName();

              //If the turtle isn't hidden, add it to the scene appropriately
              if( !turtle.hidden() ){
                int sceneXCor = xCorOffset + sightRadius;
                int sceneYCor = yCorOffset + sightRadius;
                
                if(turtle.id == callingAgent.id){
                  scene.setItem(sceneXCor, sceneYCor, "self");
                }
                else{
                  scene.setItem(sceneXCor, sceneYCor, turtleBreed);
                }
              }
              
            }
          }
        }
      }
      
      System.out.println(scene.toString());
    } catch (AgentException ex) {
      Logger.getLogger(SetScene.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
    }
  }
}
