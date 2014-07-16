/** 
 *	$Id$
 *
 * <p>Copyright (c) 2014</p>
 * 
 * <b>Latest revision summary:</b><br/>
 * $LastChangedBy$<br/>
 * $LastChangedRevision$<br/>
 * $LastChangedDate$<br/>
 */
package net.entrofi.sportslogtracker.springaop.user.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * UserAspect<br/>
 * 
 * TODO Please document the type definition<br/>
 * 
 * <p>
 * In order to see the history of changes, please see the header on package
 * definition above.
 * </p>
 * 
 * @author hcomak
 * @created Jul 15, 2014
 * @version 0.0.1
 * @since Jul 15, 2014 training startup
 * @modified $LastChangedDate$
 */
@Aspect
public class UserAspectJoinPoint {

	/**
	 * 
	 * It is possible to pass {@link JoinPoint} to an advice method as an
	 * argument and use it to get the method signature, arguments, or the target
	 * object.
	 * 
	 * @param joinPoint
	 */
	@Before("execution(public void net.entrofi.sportslogtracker.model.persistence.entity..set*(*))")
	public void logginAdvice(JoinPoint joinPoint) {
		System.out
				.println("Before advice with joinPoint passed as an argument says, 'We are calling logginAdvice on the method:"
						+ joinPoint.toString() + "'");
		System.out
				.println("Also we can get the arguments passed using JoinPoint: "
						+ Arrays.toString(joinPoint.getArgs()));

	}
	
	/**
	 * 
	 * logStringArguments TODO Document the method
	 *
	 * @param name
	 */
	@Before("args(name)")
	public void logStringArguments(String name){
		System.out.println("String argument passed = " + name);
	}
	
	/**
	 * 
	 * logFirstStringArgument TODO Document the method
	 *
	 * @param string
	 */
	@Before("net.entrofi.sportslogtracker.springaop.user.aspect.UserAspectPointcut.allMethodsPointcut()&& args(firstname,..) ")
	public void logFirstStringArgument(String firstname){
		
	System.err.println("-------------------------------------------------------------");;
		
	}


}
