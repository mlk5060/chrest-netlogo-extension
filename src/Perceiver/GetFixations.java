package Perceiver;

import Shared.BaseExtensionVariablesAndMethods;
import java.util.ArrayList;
import java.util.Iterator;
import jchrest.lib.Fixation;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.LogoListBuilder;
import org.nlogo.api.Syntax;

/**
 * Returns the result of invoking {@link 
 * jchrest.architecture.Perceiver#getFixations()} and packaging the results into
 * a {@link org.nlogo.api.LogoList} with duplicate {@link jchrest.lib.Fixation}s 
 * removed.
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class GetFixations extends DefaultReporter {
  
  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(Syntax.LinkType());
  }

  @Override
  public Object report(Argument[] argmnts, Context cntxt) throws ExtensionException, LogoException {
    LogoListBuilder fixationsLogoList = new LogoListBuilder();
    ArrayList<Fixation> fixationList = new ArrayList<>();
    
    Iterator<Fixation> fixations = BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(cntxt).getPerceiver().getFixations().iterator();
    while(fixations.hasNext()){
      Fixation fixation = fixations.next();
      boolean fixationAlreadyAdded = false;


      for(Fixation fixationInList : fixationList){
        if(
          fixationInList.getX() == fixation.getX() &&
          fixationInList.getY() == fixation.getY()
        ){
          fixationAlreadyAdded = true;
          break;
        }
      }

      if(!fixationAlreadyAdded){
        fixationList.add(fixation);
      }
    }
    
    fixationsLogoList.addAll(fixationList);
    return fixationsLogoList.toLogoList();
  }
  
}
