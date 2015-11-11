package Chrest;

import Shared.BaseExtensionVariablesAndMethods;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jchrest.architecture.Chrest;
import jchrest.lib.DomainSpecifics;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultCommand;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

/**
 * Invokes {@link 
 * jchrest.architecture.Chrest#setDomain(jchrest.lib.DomainSpecifics)} for a
 * calling turtle.
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class SetDomain extends DefaultCommand {
  
  @Override
  public Syntax getSyntax() {
    return Syntax.commandSyntax(
      new int[]{ 
        Syntax.StringType() //A fully qualified class name that extends 
                            //jchrest.lib.DomainSpecifics.
      }
    );
  }

  @Override
  public void perform(Argument[] args, Context cntxt) throws ExtensionException, LogoException {
    try {
      Chrest turtlesChrestInstance = BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(cntxt);
      Class<?> domainClass = Class.forName(args[0].getString());
      Constructor<?> domainClassConstructor = domainClass.getDeclaredConstructor(new Class[]{Chrest.class});
      domainClassConstructor.setAccessible(true);
      turtlesChrestInstance.setDomain((DomainSpecifics)domainClassConstructor.newInstance(turtlesChrestInstance));
    } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
      throw new ExtensionException(ex);
    }
  }
}
