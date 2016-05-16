package org.nlogo.extensions.chrest.ModelTests.Tileworld.LearnAction;

import java.lang.reflect.Field;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jchrest.architecture.Chrest;
import jchrest.architecture.Link;
import jchrest.architecture.Node;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.LogoListBuilder;
import org.nlogo.extensions.chrest.ChrestExtension;

/**
 *
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class Test extends DefaultReporter {

  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    LogoListBuilder actionNodes = new LogoListBuilder();
    Chrest turtlesModel = ChrestExtension.getTurtlesChrestInstance(context);
    
    try {
      Field chrestCognitionClockField = Chrest.class.getDeclaredField("_cognitionClock");
      chrestCognitionClockField.setAccessible(true);
      int timeLearningComplete = (int)chrestCognitionClockField.get(turtlesModel);
      
      Field chrestActionLtmField = Chrest.class.getDeclaredField("_actionLtm");
      chrestActionLtmField.setAccessible(true);
      Node actionRootNode = (Node)chrestActionLtmField.get(turtlesModel);
      
      List<Link> actionRootNodeChildLinks = actionRootNode.getChildren(timeLearningComplete);
      for(Link actionRootNodeChildLink : actionRootNodeChildLinks){
        actionNodes.add(actionRootNodeChildLink.getChildNode());
      }
      
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
      Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    return actionNodes.toLogoList();
  }
  
}
