package org.nlogo.extensions.chrest.architecture.chrest;

import org.nlogo.extensions.chrest.ChrestExtension;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import jchrest.domainSpecifics.DomainSpecifics;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultCommand;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.LogoList;
import org.nlogo.api.Syntax;

/**
 * @author Martyn Lloyd-Kelly <martynlk@liverpool.ac.uk>
 */
public class SetDomain extends DefaultCommand {
  
  @Override
  public Syntax getSyntax() {
    return Syntax.commandSyntax(
      new int[]{ 
        Syntax.StringType(),
        Syntax.ListType()
      }
    );
  }

  /**
   * Invokes {@link jchrest.architecture.Chrest#setDomain(
   * jchrest.lib.DomainSpecifics)} in context of the calling turtle's {@link 
   * jchrest.architecture.Chrest} instance.  The function will invoke an 
   * instance of a class that extends {@link 
   * jchrest.domainSpecifics.DomainSpecifics} as specified by the contents of
   * {@code args}.
   * 
   * @param args The first parameter should be a fully qualified class name that 
   * extends {@link jchrest.domainSpecifics.DomainSpecifics}.
   * <p>
   * The second parameter should specify the fully qualified classes and values
   * of parameters required to construct an instance of the class specified in 
   * the first parameter using a {@link org.nlogo.LogoList} containing <i>n</i>
   * {@link org.nlogo.LogoList LogoLists} where <i>n</i> = the number of 
   * parameters for the constructor.  Each of these inner {@link 
   * org.nlogo.LogoList LogoLists} should contain, at most, 2 elements: the 
   * fully qualified class of the parameter and the parameter value.  For 
   * example: if the class that extends {@link 
   * jchrest.domainSpecifics.DomainSpecifics} has a constructor that expects
   * three parameters, the first being an instance of {@link 
   * jchrest.architecture.Chrest}, the second being an instance of {@link 
   * java.lang.Integer} and the third being an instance of {@link 
   * java.lang.Boolean} then the {@link org.nlogo.LogoList} passed should be:
   * <p>
   * [<br/>
   * &nbsp;&nbsp;[("jchrest.architecture.Chrest") (chrest)]<br/>
   * &nbsp;&nbsp;[("java.lang.Integer") (2)]<br/>
   * &nbsp;&nbsp;[("java.lang.Boolean") (true)]<br/>
   * ]
   * @param cntxt
   * 
   * @throws ExtensionException
   * @throws LogoException 
   */
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
      ChrestExtension.getTurtlesChrestInstance(cntxt).setDomain(domain);
    
    } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
      throw new ExtensionException(ex.getMessage());
    }
  }
}
