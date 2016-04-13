package VisualSpatialFieldObject;

import jchrest.lib.VisualSpatialFieldObject;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 * Returns the result of invoking {@link 
 * jchrest.lib.VisualSpatialFieldObject#getObjectClass()}.
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
   * @param args The {@link jchrest.lib.VisualSpatialFieldObject} that {@link 
   * jchrest.lib.VisualSpatialFieldObject#getObjectType()} is to be invoked
   * in context of.
   * @param context
   * 
   * @return The result of invoking {@link 
   * jchrest.lib.VisualSpatialFieldObject#getObjectType()} in context of the 
   * {@link jchrest.lib.VisualSpatialFieldObject} specified as a parameter to 
   * this primitive.
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
  @Override
  public Object report(Argument[] args, Context context) throws ExtensionException, LogoException {
    return ((VisualSpatialFieldObject)args[0].get()).getObjectType();
  }
  
}
