package Chrest;

import Shared.BaseExtensionVariablesAndMethods;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import jchrest.lib.DomainSpecifics;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultCommand;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.LogoList;
import org.nlogo.api.Syntax;

/**
 * Invokes {@link 
 * jchrest.architecture.Chrest#setDomain(jchrest.lib.DomainSpecifics)} for a
 * calling turtle.
 * 
 * This class will instantiate the parameter required by {@link 
 * jchrest.architecture.Chrest#setDomain(jchrest.lib.DomainSpecifics)} using
 * the parameters passed (see below for details).
 * 
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class SetDomain extends DefaultCommand {
  
  @Override
  public Syntax getSyntax() {
    return Syntax.commandSyntax(
      new int[]{ 
        Syntax.StringType(), // A fully qualified class name that extends 
                             // jchrest.lib.DomainSpecifics which the domain of 
                             // calling turtle's CHREST model should be set to. 
        Syntax.ListType() // A list whose elements should be lists specifying
                          // the parameters required to construct an instance of
                          // the class specified in the first parameter passed.  
                          // This means that there should be n second 
                          // dimension lists where n = the number of parameters
                          // required by the class specified by the first 
                          // parameter.  For example, if 
                          // "jchrest.lib.TileworldDomain" is specified by the
                          // first parameter passed to this extension then the
                          // second paramater passed should be:
                          //
                          //[
                          //  [("jchrest.architecture.Chrest") (chrest:get-chrest-instance)]
                          //  [("java.lang.Integer") (2)]
                          //  [("java.lang.Integer") (2)]
                          //]
      }
    );
  }

  @Override
  public void perform(Argument[] args, Context cntxt) throws ExtensionException, LogoException {
    try {
      
      /**************************/
      /** GET ARGUMENTS PASSED **/
      /**************************/
      Class<?> domainClass = Class.forName(args[0].getString());
      LogoList parameterDetails = args[1].getList();
      
      /********************************************/
      /** SETUP ARRAYS TO CONSTRUCT DOMAIN CLASS **/
      /********************************************/
      Class[] constructorParamTypes = new Class[parameterDetails.size()];
      Object[] constructorParams = new Object[parameterDetails.size()];
      
      for(int i = 0; i < parameterDetails.size(); i++){
        LogoList paramDetail = (LogoList)parameterDetails.get(i);
        
        /************************************************/
        /** ADD PARAMETER TYPE TO PARAMETER TYPE ARRAY **/
        /************************************************/
        Class<?> paramType = Class.forName((String)paramDetail.get(0));
        constructorParamTypes[i] = paramType;
        
        /**************************************/
        /** ADD PARAMETER TO PARAMETER ARRAY **/
        /**************************************/
        Object parameter = paramDetail.get(1);
        
        //Since Netlogo assumes numbers are Doubles, some casting may be 
        //required to get the parameter type required.
        if(parameter instanceof Double){
          
          //Have tried getting this to work using 
          //Integer.class.isInstance(paramType) but it never passes?  This may 
          //be a little "hacky" but it works!
          if(paramType.getCanonicalName().equals(Integer.class.getCanonicalName())){
            constructorParams[i] = ((Double)parameter).intValue();
          }
        }
        //Default case: add paramater to array without type modification.
        else{
          constructorParams[i] = parameter;
        }
      }
      
      /*************************************/
      /** CONSTRUCT DOMAIN-SPECIFIC CLASS **/
      /*************************************/
      Constructor<?> domainClassConstructor = domainClass.getDeclaredConstructor(constructorParamTypes);
      domainClassConstructor.setAccessible(true);
      DomainSpecifics domain = (DomainSpecifics)domainClassConstructor.newInstance(constructorParams);
      
      /*********************************/
      /** SET CALLING TURTLE'S DOMAIN **/
      /*********************************/
      BaseExtensionVariablesAndMethods.getTurtlesChrestInstance(cntxt).setDomain(domain);
    
    } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
      throw new ExtensionException(ex.getMessage());
    }
  }
}
