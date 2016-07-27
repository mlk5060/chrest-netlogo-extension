package org.nlogo.extensions.chrest.domainSpecifics.sceneObject;

import jchrest.domainSpecifics.SceneObject;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 *
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class GetObjectType extends DefaultReporter {

  @Override
  public Syntax getSyntax(){
    return Syntax.reporterSyntax(
      new int[]{
        Syntax.WildcardType()
      }, 
      Syntax.StringType()
    );
  }
  
  /**
   * 
   * @param args A {@link jchrest.domainSpecifics.SceneObject}.
   * @param context
   * 
   * @return The result of invoking {@link 
   * jchrest.domainSpecifics.SceneObject#getObjectType()} on the {@link 
   * jchrest.domainSpecifics.SceneObject} passed as a parameter to this 
   * primitive.
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    return ((SceneObject)args[0].get()).getObjectType();
  }
  
}
