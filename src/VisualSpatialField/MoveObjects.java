package VisualSpatialField;

import Shared.BaseExtensionVariablesAndMethods;
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
 * Invokes {@link jchrest.architecture.VisualSpatialField#moveObjects(
 * java.util.ArrayList, int, boolean) for the calling turtle.
 * 
 * The first parameter passed must be a 2D {@link org.nlogo.api.LogoList} whose 
 * first dimension {@link org.nlogo.api.LogoList} should contain {@link 
 * org.nlogo.api.LogoList}s of {@link jchrest.lib.ItemSquarePattern}s that 
 * specify a sequence of moves for one {@link 
 * jchrest.lib.VisualSpatialFieldObject} using {@link 
 * jchrest.lib.VisualSpatialFieldObject#_identifier}s and absolute
 * {@link jchrest.lib.VisualSpatialFieldObject} coordinates. For example, if 
 * two objects of class "block" and with identifiers, A and B, are to be moved 
 * from coordinates (0, 1) and (0, 2) to (1, 1) and (1, 2) respectively, the 
 * {@link org.nlogo.api.LogoList} passed should look like: 
 * 
 *  [
 *    [
 *      &lt;[A 0 1]&gt; &lt;[A 1 1]&gt;
 *    ]
 *    [ 
 *      &lt;[B 0 2]&gt; &lt;[B 1 2]&gt;
 *    ]
 *  ]
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class MoveObjects extends DefaultCommand {

  @Override
  public Syntax getSyntax() {
    return Syntax.commandSyntax(new int[]{
      Syntax.ListType(), 
      Syntax.NumberType(),
      Syntax.BooleanType()
    });
  }
  
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
      BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(context)
        .getVisualSpatialFields().lastEntry().getValue().moveObjects(
          movesArrayList,
          args[1].getIntValue(),
          args[2].getBooleanValue()
        );
    } catch (VisualSpatialFieldException ex) {
      throw new ExtensionException(ex);
    }
  }
}
