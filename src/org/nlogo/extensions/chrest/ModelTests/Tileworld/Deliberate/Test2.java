package org.nlogo.extensions.chrest.ModelTests.Tileworld.Deliberate;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
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
 *
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class Test2 extends DefaultCommand {

  @Override
  public void perform(Argument[] args, Context context) throws ExtensionException, LogoException {
    Chrest turtlesModel = ChrestExtension.getTurtlesChrestInstance(context);
    
    /*******************************/
    /***** CREATE PRODUCTION 1 *****/
    /*******************************/
    
    //Create Visual Node 1. Set its contents and image so that, when the 
    //problem-solving system is used, the action returned will be to move 
    //randomly, i.e. do not specify the location of a tile in the visual Node's
    //contents/image.
    ListPattern visualNode1ContentsAndImage = new ListPattern(Modality.VISUAL);
    visualNode1ContentsAndImage.add(new ItemSquarePattern("H", 0, -1));
    Node visualNode1 = new Node(turtlesModel, visualNode1ContentsAndImage, visualNode1ContentsAndImage, 0);
    
    //Create Action Node 1 (should stipulate use of the problem-solving system).
    ListPattern actionNode1ContentsAndImage = new ListPattern(Modality.ACTION);
    actionNode1ContentsAndImage.add(new ItemSquarePattern("PS", 0, 0));
    Node actionNode1 = new Node(turtlesModel, actionNode1ContentsAndImage, actionNode1ContentsAndImage, 0);
    
    //Construct production between Visual Node 1 and Action Node 1 and value the
    //production so that it is guaranteed to be selected (production selection 
    //algorithms are tested seperately so weighting production selection is OK 
    //here).
    HashMap visualNode1Production = new HashMap();
    visualNode1Production.put(actionNode1, 1.0);
    
    /*******************************/
    /***** CREATE PRODUCTION 2 *****/
    /*******************************/
    
    //Create Visual Node 2.
    ListPattern visualNode2ContentsAndImage = new ListPattern(Modality.VISUAL);
    visualNode2ContentsAndImage.add(new ItemSquarePattern("H", 0, -2));
    Node visualNode2 = new Node(turtlesModel, visualNode2ContentsAndImage, visualNode2ContentsAndImage, 0);
    
    //Create Action Node 2 (should have non-empty contents and image so that it
    //can be identified in test code).
    ListPattern actionNode2ContentsAndImage = new ListPattern(Modality.ACTION);
    actionNode2ContentsAndImage.add(new ItemSquarePattern("MV", 180, 1));
    Node actionNode2 = new Node(turtlesModel, actionNode2ContentsAndImage, actionNode2ContentsAndImage, 0);
    
    //Construct production between Visual Node 2 and Action Node 2 and value the
    //production so that it is guaranteed to NOT be selected (production 
    //selection algorithms are tested seperately so weighting production 
    //selection is OK here).
    HashMap visualNode2Production = new HashMap();
    visualNode2Production.put(actionNode2, 0.0);
    
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
      visualNode1ProductionHistory.put(1, visualNode1Production);
      visualNode2ProductionHistory.put(1, visualNode2Production);
      
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
