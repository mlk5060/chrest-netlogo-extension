import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jchrest.lib.ItemSquarePattern;
import jchrest.lib.MindsEyeMoveObjectException;
import org.nlogo.api.AgentException;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultCommand;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.LogoList;
import org.nlogo.api.Syntax;


/**
 * Moves objects specified to domain coordinates specified in the latest minds 
 * eye that is associated with the calling turtle's CHREST instance.
 * 
 * Two parameters must be passed when the Netlogo extension primitive that 
 * invokes this class is used in a Netlogo model:
 * 
 * Param #      Data Type       Description
 * -------      ---------       -----------
 * 1            2D list         A 2D list whose first dimension elements should 
 *                              contain lists of ItemSquarePattern instances 
 *                              whose elements specify a sequence of moves for 
 *                              one object using object identifiers and 
 *                              domain-specific coordinates.  
 *                              For example, if two objects, A and B, are to be 
 *                              moved from domain specific x/y coordinates 0/1 
 *                              and 0/2 to 1/1 and 1/2 respectively, the list 
 *                              passed should be: 
 *                              [ 
 *                                [ 
 *                                  [A 0 1] [A 1 1] 
 *                                ] 
 *                                [ 
 *                                  [B 0 2] [B 1 2] 
 *                                ] 
 *                              ]
 * 
 * 2            Number          The current Netlogo time (in milliseconds).
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
class MoveObjectsInMindsEye extends DefaultCommand {

  @Override
  public Syntax getSyntax() {
    return Syntax.commandSyntax(new int[]{Syntax.ListType(), Syntax.NumberType()});
  }
  
  
  @Override
  public void perform(Argument[] args, Context context) throws ExtensionException, LogoException {
    try {
      if(BaseExtensionVariablesAndMethods.agentHasChrestInstance(context)){
        
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
                throw new ExtensionException("Object move specification " + objectMoveContents.toString() + " is not a string.  Please rectify.");
              }
            }
          }
          else{
            throw new ExtensionException("The second dimension " + objectMoves.toString() + " element of the " + moves.toString() + " list is not a list.  Please rectify.");
          }
        }
        
        BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(context).getMindsEyes().lastEntry().getValue().moveObjects(movesArrayList, args[1].getIntValue());
      }
    } catch (AgentException ex) {
      Logger.getLogger(MoveObjectsInMindsEye.class.getName()).log(Level.SEVERE,"", ex);
    } catch (MindsEyeMoveObjectException e){
      throw new ExtensionException(e);
    }
  }
}
