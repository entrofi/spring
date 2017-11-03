/**
 * $Id$
 * <p>
 * <p>Copyright (c) 2014</p>
 * <p>
 * <b>Latest revision summary:</b><br/>
 * $LastChangedBy$<br/>
 * $LastChangedRevision$<br/>
 * $LastChangedDate$<br/>
 * <p>
 * package-info<br/>
 * <p>
 * <ol>
 * <li>
 * <strong>Before Advice</strong>: These advices runs before the execution of join point methods. We can use
 * <code>@Before</code> annotation to mark an advice type as Before advice.</li>
 * <li>
 * <strong>After (finally) Advice</strong>: An advice that gets executed after the join point method finishes executing,
 * whether normally or by throwing an exception. We can create after advice using <code>@After</code> annotation.</li>
 * <li>
 * <strong>After Returning Advice</strong>: Sometimes we want advice methods to execute only if the join point method
 * executes normally. We can use <code>@AfterReturning</code> annotation to mark a method as after returning advice.
 * </li>
 * <li>
 * <strong>After Throwing Advice</strong>: This advice gets executed only when join point method throws exception,
 * we can use it to rollback the transaction declaratively. We use <code>@AfterThrowing</code> annotation for this type
 * of advice.</li>
 * <li>
 * <strong>Around Advice</strong>: This is the most important and powerful advice. This advice surrounds the join
 * point method and we can also choose whether to execute the join point method or not. We can write advice code
 * that gets executed before and after the execution of the join point method. It is the responsibility of around
 * advice to invoke the join point method and return values if the method is returning something.
 * We use <code>@Around</code> annotation to create around advice methods.</li>
 * </ol>
 * <p>
 * <p>In order to see the history of changes, please see the header on package
 * definition above.</p>
 *
 * @author hcomak
 * @created Jul 15, 2014
 * @version 0.0.1
 * @since Jul 15, 2014 training startup
 * @modified $LastChangedDate$
 */
package net.entrofi.sportslogtracker.springaop.user.aspect;



