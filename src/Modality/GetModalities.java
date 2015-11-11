package Modality;

import jchrest.lib.Modality;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.LogoListBuilder;
import org.nlogo.api.Syntax;

/**
 * Returns the result of invoking {@link jchrest.lib.Modality#toString()} and
 * packaging the result into a {@link org.nlogo.api.LogoList}.
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class GetModalities extends DefaultReporter {
  
  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(Syntax.ListType());
  }

  @Override
  public Object report(Argument[] argmnts, Context cntxt) throws ExtensionException, LogoException {
    LogoListBuilder modalityNamesList = new LogoListBuilder();
    
    for(Modality modality : Modality.values()){
      modalityNamesList.add(modality.toString());
    }
    
    return modalityNamesList.toLogoList();
  }
}
