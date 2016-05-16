package org.nlogo.extensions.chrest.ModelTests.Tileworld.RouletteSelection;

import jchrest.architecture.Chrest;
import jchrest.architecture.Node;
import jchrest.lib.ItemSquarePattern;
import jchrest.lib.ListPattern;
import jchrest.lib.Modality;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.LogoListBuilder;
import org.nlogo.api.Syntax;

/**
 *
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class Setup extends DefaultReporter{

  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(Syntax.ListType());
  }
  
  @Override
  public Object report(Argument[] argmnts, Context cntxt) throws ExtensionException, LogoException {
    LogoListBuilder productionsToReturn = new LogoListBuilder();
    
    //A CHREST model is required as a Node construction parameter so create an 
    //instance of one. 
    Chrest model = new Chrest(0, true);
    
    /************************************************/
    /***** CONSTRUCT NODES FOR FIRST PRODUCTION *****/
    /************************************************/
    
    LogoListBuilder production1 = new LogoListBuilder();
    
    //Construct visual Node with a defined contents and image so it can be
    //identified in the test.
    ListPattern production1VisualNodeContentsAndImage = new ListPattern(Modality.VISUAL);
    production1VisualNodeContentsAndImage.add(new ItemSquarePattern("T", 0, -1));
    Node production1VisualNode = new Node(model, production1VisualNodeContentsAndImage, production1VisualNodeContentsAndImage, 0);
    
    //Construct action Node with a defined contents and image so it can be
    //identified in the test.
    ListPattern production1ActionNodeContentsAndImage = new ListPattern(Modality.ACTION);
    production1ActionNodeContentsAndImage.add(new ItemSquarePattern("PT", 180, 1));
    Node production1ActionNode = new Node(model, production1ActionNodeContentsAndImage, production1ActionNodeContentsAndImage, 0);
    
    //Add visual and action Node to the production1 List.
    production1.add(production1VisualNode);
    production1.add(production1ActionNode);
    
    /*************************************************/
    /***** CONSTRUCT NODES FOR SECOND PRODUCTION *****/
    /*************************************************/
    
    LogoListBuilder production2 = new LogoListBuilder();
    
    //Construct visual Node with a defined contents and image so it can be
    //identified in the test.
    ListPattern production2VisualNodeContentsAndImage = new ListPattern(Modality.VISUAL);
    production2VisualNodeContentsAndImage.add(new ItemSquarePattern("T", 0, 2));
    Node production2VisualNode = new Node(model, production2VisualNodeContentsAndImage, production2VisualNodeContentsAndImage, 0);
    
    //Construct action Node with a defined contents and image so it can be
    //identified in the test.
    ListPattern production2ActionNodeContentsAndImage = new ListPattern(Modality.ACTION);
    production2ActionNodeContentsAndImage.add(new ItemSquarePattern("MV", 0, 1));
    Node production2ActionNode = new Node(model, production2ActionNodeContentsAndImage, production2ActionNodeContentsAndImage, 0);
    
    //Add visual and action Node to the production1 List.
    production2.add(production2VisualNode);
    production2.add(production2ActionNode);
    
    /*********************************************/
    /***** ADD PRODUCTIONS TO LIST TO RETURN *****/
    /*********************************************/
    
    productionsToReturn.add(production1.toLogoList());
    productionsToReturn.add(production2.toLogoList());
    return productionsToReturn.toLogoList();
  }
  
}
