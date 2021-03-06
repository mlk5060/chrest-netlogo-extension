package org.nlogo.extensions.chrest.ModelTests.Tileworld.Deliberate;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import jchrest.architecture.Chrest;
import jchrest.architecture.Node;
import jchrest.architecture.Stm;
import jchrest.lib.HistoryTreeMap;
import jchrest.lib.ItemSquarePattern;
import jchrest.lib.ListPattern;
import jchrest.lib.Modality;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultCommand;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.extensions.chrest.ChrestExtension;

/**
 * Populates a calling {@link org.nlogo.agent.Agent Agent's} {@link 
 * jchrest.lib.Modality#VISUAL} {@link jchrest.architecture.Stm} with two {@link 
 * jchrest.lib.Modality#VISUAL} {@link jchrest.architecture.Node Nodes} so that 
 * the <i>deliberate</i> procedure in the Netlogo Tileworld model can be tested.
 * 
 * Note that there is only one production that is guaranteed to be selected due
 * to the ratings specified.
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class Test1 extends DefaultCommand {

  @Override
  public void perform(Argument[] args, Context context) throws ExtensionException, LogoException {
    Chrest turtlesModel = ChrestExtension.getTurtlesChrestInstance(context);
    
    /*******************************/
    /***** CREATE VISUAL NODES *****/
    /*******************************/
    
    //Create Visual Node 1.  This should have an information count > 0 so that
    //it is selected during the first round of roulette selection when
    //Chrest.generateActionUsingVisualPatternRecognition() is invoked in the 
    //Netlogo code.
    ListPattern visualNode1ContentsAndImage = new ListPattern(Modality.VISUAL);
    visualNode1ContentsAndImage.add(new ItemSquarePattern("T", 0, -1));
    Node visualNode1 = new Node(turtlesModel, visualNode1ContentsAndImage, visualNode1ContentsAndImage, 0);
    
    //Create Visual Node 2.  This should have an information count of 0 so that 
    //it won't be a candidate Node for selection during the first round of 
    //roulette selection when Chrest.generateActionUsingVisualPatternRecognition() 
    //is invoked in the Netlogo code.
    ListPattern visualNode2ContentsAndImage = new ListPattern(Modality.VISUAL);
    Node visualNode2 = new Node(turtlesModel, visualNode2ContentsAndImage, visualNode2ContentsAndImage, 0);
    
    /*******************************/
    /***** CREATE ACTION NODES *****/
    /*******************************/
    
    //Create action Node 1.
    ListPattern actionNode1ContentsAndImage = new ListPattern(Modality.ACTION);
    actionNode1ContentsAndImage.add(new ItemSquarePattern("PT", 180, 1));
    actionNode1ContentsAndImage.add(new ItemSquarePattern("PT", 180, 1));
    Node actionNode1 = new Node(turtlesModel, actionNode1ContentsAndImage, actionNode1ContentsAndImage, 0);
    
    //Create action Node 2.
    ListPattern actionNode2ContentsAndImage = new ListPattern(Modality.ACTION);
    actionNode2ContentsAndImage.add(new ItemSquarePattern("MV", 180, 1));
    Node actionNode2 = new Node(turtlesModel, actionNode2ContentsAndImage, actionNode2ContentsAndImage, 0);
    
    //Create action node 3.
    ListPattern actionNode3ContentsAndImage = new ListPattern(Modality.ACTION);
    actionNode3ContentsAndImage.add(new ItemSquarePattern("MV", 0, 1));
    Node actionNode3 = new Node(turtlesModel, actionNode3ContentsAndImage, actionNode3ContentsAndImage, 0);
    
    /******************************/
    /***** CREATE PRODUCTIONS *****/
    /******************************/
    
    //Construct productions between visual Node 1 and action Nodes 1 and 2 and 
    //value the productions so that action Node 1 is guaranteed to be selected 
    //(production selection algorithms are tested seperately so weighting 
    //production selection is OK here).
    HashMap visualNode1Productions = new LinkedHashMap();
    visualNode1Productions.put(actionNode1, 1.0);
    visualNode1Productions.put(actionNode2, 0.0);
    
    //Construct production between visual Node 2 and Action Node 2 and value the
    //production so that it is guaranteed to be selected.  However, since visual
    //Node 2 has an information count of 0 and visual Node 1 has an information
    //count of 1, visual Node 2 and hence, this production, should never be 
    //selected.
    HashMap visualNode2Productions = new LinkedHashMap();
    visualNode2Productions.put(actionNode3, 1.0);
    
    try {
      
      /***************************/
      /***** SET PRODUCTIONS *****/
      /***************************/
      
      //Set the private "_productionHistory" variable in the Node class to true 
      //so that productions can be set for each Visual Node created above.
      Field nodeProductionHistoryField = Node.class.getDeclaredField("_productionHistory");
      nodeProductionHistoryField.setAccessible(true);
      
      //The production history for a Node has an intital entry with a key set to
      //the time the Node is created (in this case, 0) and, since a Node's 
      //production history is a HistoryTreeMap, this entry can not be replaced.
      //To get around this, create an entry using the next available key, i.e. 1
      HistoryTreeMap visualNode1ProductionHistory = (HistoryTreeMap)nodeProductionHistoryField.get(visualNode1);
      HistoryTreeMap visualNode2ProductionHistory = (HistoryTreeMap)nodeProductionHistoryField.get(visualNode2);
      visualNode1ProductionHistory.put(1, visualNode1Productions);
      visualNode2ProductionHistory.put(1, visualNode2Productions);
      
      /**************************/
      /***** SET VISUAL STM *****/
      /**************************/
      
      //Need to get the visual STM of the calling turtle's CHREST model.  This 
      //is a private field so needs to be made accessible so it can be set 
      //"manually".
      Field chrestVisualStmField = Chrest.class.getDeclaredField("_visualStm");
      chrestVisualStmField.setAccessible(true);
      
      //Need to get the item history of visual STM for the calling turtle's 
      //CHREST model.  This is a private field so needs to be made accessible so 
      //it can be set "manually".
      Field stmItemHistoryField = Stm.class.getDeclaredField("_itemHistory");
      stmItemHistoryField.setAccessible(true);
      
      //Add the Visual Nodes (with their productions) to Visual STM at a time
      //when the productions exist, i.e. 1.
      //Create a new Visual STM state.
      ArrayList<Node> visualStmState = new ArrayList();
      visualStmState.add(visualNode1);
      visualStmState.add(visualNode2);
      
      HistoryTreeMap visualStm = (HistoryTreeMap)stmItemHistoryField.get(chrestVisualStmField.get(turtlesModel));
      visualStm.put(1, visualStmState);
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
      Logger.getLogger(Test1.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
}
