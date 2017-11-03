/**
 * $Id$
 * <p>
 * <p>Copyright (c) 2014</p>
 * <p>
 * <b>Latest revision summary:</b><br/>
 * $LastChangedBy$<br/>
 * $LastChangedRevision$<br/>
 * $LastChangedDate$<br/>
 */
package net.entrofi.sportslogtracker.springaop.user.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * UserAspect<br/>
 * <p>
 * <ul>
 * <li>Aspect classes are required to have <code>@Aspect</code> annotation.</li>
 * <li>@Before annotation is used to create Before advice</li>
 * <li>The string parameter passed in the <code>@Before</code> annotation is the Pointcut expression</li>
 * <li>
 * <em>getNameAdvice()</em> advice will execute for any Spring Bean method with signature
 * <code>public String getFirstname()</code>. This is a very important point to remember, if we will create
 * Employee bean using new operator the advices will not be applied. Only when we will use ApplicationContext to get
 * the bean, advices will be applied.</li>
 * <li>We can use asterisk (*) as wild card in Pointcut expressions, <em>getAllAdvice()</em> will be applied for all
 * the classes in <code>net.entrofi.sportslogtracker.springaop.user.service</code> package whose name starts with
 * <code>get</code> and doesn’t take any arguments.</li>
 * </ul>
 * <p>
 * <p>In order to see the history of changes, please see the header on package
 * definition above.</p>
 *
 * @author hcomak
 * @version 0.0.1
 * @created Jul 15, 2014
 * @modified $LastChangedDate$
 * @since Jul 15, 2014 training startup
 */
@Aspect
public class UserAspect {

    @Before("execution(public String getFirstname())")
    public void getNameAdvice() {
        System.out.println("Executing advice on getFirstname()");
    }

    @Before("execution(* net.entrofi.sportslogtracker.springaop.user.service.*.get*())")
    public void getAllAdvice() {
        System.out.println("Service method getter called");
    }
}
