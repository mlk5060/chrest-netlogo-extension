package org.nlogo.extensions.chrest.architecture.chrest;

import org.nlogo.extensions.chrest.ChrestExtension;
import java.util.ArrayList;
import jchrest.lib.ItemSquarePattern;
import jchrest.lib.VisualSpatialFieldException;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultCommand;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.LogoList;
import org.nlogo.api.Syntax;


/**
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class MoveObjectsInVisualSpatialField extends DefaultCommand {

  @Override
  public Syntax getSyntax() {
    return Syntax.commandSyntax(new int[]{
      Syntax.ListType(), 
      Syntax.NumberType()
    });
  }
  
  /**
   * Invokes {@link jchrest.architecture.Chrest#moveObjectsInVisualSpatialField(
   * java.util.ArrayList, int) in context of the calling  turtle's {@link 
   * jchrest.architecture.Chrest} instance.
   * 
   * @param args The first parameter should be a {@link org.nlogo.api.LogoList} 
   * containing {@link org.nlogo.api.LogoList LogoLists} that specify moves
   * for {@link jchrest.lib.VisualSpatialFieldObject VisualSpatialFieldObjects}.  
   * These moves should be specified using {@link jchrest.lib.ItemSquarePattern 
   * ItemSquarePatterns} whose structure should be as declared by {@link 
   * jchrest.architecture.Chrest#moveObjectsInVisualSpatialField(
   * java.util.ArrayList, int)}.  For example, if two {@link 
   * jchrest.lib.VisualSpatialFieldObject VisualSpatialFieldObjects} with 
   * identifiers "A" and "B" are to be moved from (0, 1) and (1, 2) to (1, 0) 
   * and (2, 1) respectively, the {@link org.nlogo.api.LogoList} passed should
   * look like the following:
   * <p>
   * [<br/>
   * &nbsp;[{@code <[A 0 1][A 1 0]>}]<br/>
   * &nbsp;[{@code <[B 1 2][B 2 1]>}]<br/>
   * ]
   * <p>
   * For other parameters see {@link 
   * jchrest.architecture.Chrest#moveObjectsInVisualSpatialField(
   * java.util.ArrayList, int)}.
   * @param context
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public void perform(Argument[] args, Context context) throws ExtensionException, LogoException {
    LogoList moves = args[0].getList();
    ArrayList<ArrayList<ItemSquarePattern>> movesArrayList = new ArrayList<>();

    //Get the first dimension of the first parameter passed as a LogoList
    //and check that all first dimension list elements are lists and that
    //all second dimension elements are ItemSquarePattern instances.
    for(int objectMoveSet = 0; objectMoveSet < moves.size(); objectMoveSet++){

      Object objectMoves = moves.get(objectMoveSet);
      if( objectMoves instanceof LogoList ){

        movesArrayList.add(new ArrayList<>());

        LogoList objectMovesLogoList = (LogoList)objectMoves;
        for(int objectMove = 0; objectMove < objectMovesLogoList.size(); objectMove++){

          Object objectMoveContents = objectMovesLogoList.get(objectMove);
          if( objectMoveContents instanceof ItemSquarePattern ){
            movesArrayList.get(objectMoveSet).add((ItemSquarePattern)objectMoveContents);
          }
          else{
            throw new ExtensionException("Object move specification " + objectMoveContents.toString() + " is not an ItemSquarePattern (" + objectMoveContents.getClass().getCanonicalName() + ").  Please rectify.");
          }
        }
      }
      else{
        throw new ExtensionException("The second dimension " + objectMoves.toString() + " element of the " + moves.toString() + " list is not a list.  Please rectify.");
      }
    }

    try {
      ChrestExtension.getTurtlesChrestInstance(context).moveObjectsInVisualSpatialField(movesArrayList, args[1].getIntValue());
    } catch (VisualSpatialFieldException ex) {
      throw new ExtensionException(ex);
    }
  }
}
