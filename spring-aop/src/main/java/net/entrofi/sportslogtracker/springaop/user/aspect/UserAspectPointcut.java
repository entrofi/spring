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

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * UserAspect<br/>
 * 
 * <p>
 * A <b>Pointcut</b> is a predicate comprising a set of joinpoints, which
 * identifies where advice applies. For instance,
 * <ul>
 * 		<li>methods of a class</li>
 * 		<li>methods of all classes under a package</li>
 * 		<li>methods of a class starting with set prefix</li>
 * 		<li>methods of methods returning void</li>
 * </ul>
 * </p>
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
public class UserAspectPointcut {

	/**
	 * 
	 * Sometimes we have to use same Pointcut expression at multiple places, we
	 * can create an empty method with @Pointcut annotation and then use it as
	 * expression in advice<br/>
	 * This method is an example of this situation.
	 * 
	 */
	@Pointcut("execution(public String getFirstname())")
	public void getNamePointcut() {
	}

	/**
	 * 
	 * Sometimes we have to use same Pointcut expression at multiple places, we
	 * can create an empty method with @Pointcut annotation and then use it as
	 * expression in advice<br/>
	 * This method is an example of this situation.
	 * 
	 */
	@Pointcut("within(net.entrofi.sportslogtracker.springaop.user.service.*)")
	public void allMethodsPointcut() {
	}
	

	@Before("getNamePointcut()")
	public void logNameAccessAdvice() {
		System.out
				.println("UserAspectPointcut: \nlogNameAccessAdvice says: 'Name will be accessed!'");
	}

	@Before("allMethodsPointcut()")
	public void allSeviceMethodsAdvice() {
		System.out.println("UserAspectPointcut: Before executing service method...");
	}
}
