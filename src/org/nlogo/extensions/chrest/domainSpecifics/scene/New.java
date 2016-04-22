package org.nlogo.extensions.chrest.domainSpecifics.scene;

import jchrest.domainSpecifics.Scene;
import jchrest.domainSpecifics.SceneObject;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.LogoList;
import org.nlogo.api.Syntax;
import org.nlogo.extensions.chrest.ChrestExtension;

/**
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class New extends DefaultReporter {

  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(
      new int[]{
        Syntax.ListType(),
        Syntax.StringType()
      }, 
      Syntax.WildcardType()
    );
  }
  
  /**
   * 
   * @param args The first parameter passed should be a {@link 
   * org.nlogo.api.LogoList} containing other {@link org.nlogo.api.LogoList 
   * LogoLists} detailing what {@link org.nlogo.agent.Patch Patches} can be 
   * "seen" by the calling turtle details of {@link org.nlogo.agent.Turtle 
   * Turtles} on these {@link org.nlogo.agent.Patch Patches}.  Each inner {@link
   * {@link org.nlogo.api.LogoList} should contain 4 elements:
   * 
   * <ol type="1">
   *  <li>{@link org.nlogo.agent.Patch#pxcor}</li>
   *  <li>{@link org.nlogo.agent.Patch#pycor}</li>
   *  <li>
   *    The {@link org.nlogo.agent.Turtle#id} of the {@link 
   *    org.nlogo.agent.Turtle} on the {@link org.nlogo.agent.Patch} as a {@link 
   *    java.lang.String}.
   *  </li>
   *  <li>
   *    The result of {@link org.nlogo.agent.Turtle#getBreed()} for the {@link 
   *    org.nlogo.agent.Turtle} on the {@link org.nlogo.agent.Patch}.
   *  </li>
   * </ol>
   * 
   * The second parameter will be used to set the name of the {@link 
   * jchrest.domainSpecifics.Scene}.
   * @param context
   * 
   * @return The result of invoking {@link jchrest.domainSpecifics.Scene#Scene(
   * java.lang.String, int, int, int, int, 
   * jchrest.architecture.VisualSpatialField)}.
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    
    LogoList vision = args[0].getList();

    Integer minX = null;
    Integer maxX = null;
    Integer minY = null;
    Integer maxY = null;

    for(int i = 0; i < vision.size(); i++){
      LogoList patchInfo = (LogoList)vision.get(i);
      int xcor = ((Double)patchInfo.get(0)).intValue();
      int ycor = ((Double)patchInfo.get(1)).intValue();

      //Set minX.
      if(minX == null){
        minX = xcor;
      }
      else if(xcor < minX){
        minX = xcor;
      }

      //Set minY.
      if(minY == null){
        minY = ycor;
      }
      else if(ycor < minY){
        minY = ycor;
      }

      //Set maxX.
      if(maxX == null){
        maxX = xcor;
      }
      else if(xcor > maxX){
        maxX = xcor;
      }

      //Set maxY.
      if(maxY == null){
        maxY = ycor;
      }
      else if(ycor > maxY){
        maxY = ycor;
      }
    }

    //After finding the difference between min/max x/y values, add 1 to the 
    //result since, if max/minX are 2 and 1 respectively, the result (1) is not 
    //correct (2 should be returned).
    Scene scene = new Scene(
      args[1].getString(), 
      (maxY - minY) + 1, 
      (maxX - minX) + 1,
      minX,
      minY,
      null
    );

    //////////////////////////
    ///// POPULATE SCENE /////
    //////////////////////////
    
    for(int i = 0; i < vision.size(); i++){
      LogoList patchInfo = (LogoList)vision.get(i);
      
      scene.addObjectToSquare(
        scene.getSceneSpecificColFromDomainSpecificCol( ((Double)patchInfo.get(0)).intValue() ),
        scene.getSceneSpecificRowFromDomainSpecificRow( ((Double)patchInfo.get(1)).intValue() ),
        new SceneObject(
          (String)patchInfo.get(2),
          (String)patchInfo.get(3))
      );
    }

    return scene;
  }
}
