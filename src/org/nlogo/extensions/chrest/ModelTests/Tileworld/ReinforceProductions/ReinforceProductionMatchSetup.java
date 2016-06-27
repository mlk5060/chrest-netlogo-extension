/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nlogo.extensions.chrest.ModelTests.Tileworld.ReinforceProductions;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jchrest.architecture.Chrest;
import jchrest.architecture.Node;
import jchrest.architecture.Stm;
import jchrest.lib.HistoryTreeMap;
import jchrest.lib.ListPattern;

/**
 *
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class ReinforceProductionMatchSetup {
  static Node invoke(Chrest turtlesChrestInstance, ListPattern vision, ListPattern action){
    
    Node visualNode = new Node(turtlesChrestInstance, vision, vision, 0);
    Node actionNode = new Node(turtlesChrestInstance, action, action, 0);
    
    LinkedHashMap<Node, Double> visualNodeProductions = new LinkedHashMap();
    visualNodeProductions.put(actionNode, 1.0);
    HistoryTreeMap<Integer, LinkedHashMap<Node, Double>> visualNodeProductionHistory = new HistoryTreeMap();
    visualNodeProductionHistory.put(0, visualNodeProductions);
    
    //Create visual STM
    List<Node> visualStmItems = new ArrayList();
    visualStmItems.add(visualNode);
    HistoryTreeMap visualStmItemHistory = new HistoryTreeMap();
    visualStmItemHistory.put(0, visualStmItems);
    
    //Create action STM
    List<Node> actionStmItems = new ArrayList();
    actionStmItems.add(actionNode);
    HistoryTreeMap actionStmItemHistory = new HistoryTreeMap();
    actionStmItemHistory.put(0, actionStmItems);
    
    try {
      
      //Set production
      Field node_production_history_field = Node.class.getDeclaredField("_productionHistory");
      node_production_history_field.setAccessible(true);
      node_production_history_field.set(visualNode, visualNodeProductionHistory);
      
      //Set visual and action STM
      Field chrest_visual_stm_field = Chrest.class.getDeclaredField("_visualStm");
      chrest_visual_stm_field.setAccessible(true);
      
      Field chrest_action_stm_field = Chrest.class.getDeclaredField("_actionStm");
      chrest_action_stm_field.setAccessible(true);
      
      Field stm_item_history_field = Stm.class.getDeclaredField("_itemHistory");
      stm_item_history_field.setAccessible(true);
      
      stm_item_history_field.set(chrest_visual_stm_field.get(turtlesChrestInstance), visualStmItemHistory);
      stm_item_history_field.set(chrest_action_stm_field.get(turtlesChrestInstance), actionStmItemHistory);
      
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
      Logger.getLogger(ReinforceExactProductionMatchTest.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    return visualNode;
  }
}
