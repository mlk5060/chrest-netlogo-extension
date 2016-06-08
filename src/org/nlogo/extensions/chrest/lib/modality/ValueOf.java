package org.nlogo.extensions.chrest.lib.modality;

import jchrest.lib.Modality;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class ValueOf extends DefaultReporter {
  
  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(new int[]{Syntax.StringType()}, Syntax.WildcardType());
  }

  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    return Modality.valueOf(args[0].getString());
  }
}
