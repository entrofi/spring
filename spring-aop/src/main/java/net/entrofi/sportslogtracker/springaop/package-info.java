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
/**
 * package-info<br/>
 * 
 *	<h3>Aspect Oriented Programming Overview</h3>
 *	<p>Most of the enterprise applications have some common crosscutting concerns that is applicable for different types of Objects and modules. Some of the common crosscutting concerns are logging, transaction management, data validation etc. In Object Oriented Programming, modularity of application is achieved by Classes whereas in Aspect Oriented Programming application modularity is achieved by Aspects and they are configured to cut across different classes. </p>
 *	<p>AOP takes out the direct dependency of crosscutting tasks from classes that we can’t achieve through normal object oriented programming model. For example, we can have a separate class for logging but again the functional classes will have to call these methods to achieve logging across the application.</p>
 *	<h3>Aspect Oriented Programming Core Concepts</h3>
 *	<p>Before we dive into implementation of AOP in Spring Framework, we should understand the core concepts of AOP.</p>
 *	<ol>
 *		<li>
 *			<strong>Aspect</strong>: An aspect is a class that implements enterprise application concerns that cut across multiple classes, such as transaction management. Aspects can be a normal class configured through Spring XML configuration or we can use Spring AspectJ integration to define a class as Aspect using <code>@Aspect</code> annotation.</li>
 *		<li>
 *			<strong>Join Point</strong>: A join point is the specific point in the application such as method execution, exception handling, changing object variable values etc. In Spring AOP a join points is always the execution of a method.</li>
 *		<li>
 *			<strong>Advice</strong>: Advices are actions taken for a particular join point. In terms of programming, they are methods that gets executed when a certain join point with matching pointcut is reached in the application. You can think of Advices as <a href="http://www.journaldev.com/2210/struts-2-interceptor-tutorial-with-custom-authentication-interceptor-example" title="Struts 2 Interceptor Tutorial with Custom Authentication Interceptor Example">Struts2 interceptors</a> or <a href="http://www.journaldev.com/1933/java-servlet-filter-example-tutorial" title="Java Servlet Filter Example Tutorial">Servlet Filters</a>.</li>
 *		<li>
 *			<strong>Pointcut</strong>: Pointcut are expressions that is matched with join points to determine whether advice needs to be executed or not. Pointcut uses different kinds of expressions that are matched with the join points and Spring framework uses the AspectJ pointcut expression language.</li>
 *		<li>
 *			<strong>Target Object</strong>: They are the object on which advices are applied. Spring AOP is implemented using runtime proxies so this object is always a proxied object. What is means is that a subclass is created at runtime where the target method is overridden and advices are included based on their configuration.</li>
 *		<li>
 *			<strong>AOP proxy</strong>: Spring AOP implementation uses JDK dynamic proxy to create the Proxy classes with target classes and advice invocations, these are called AOP proxy classes. We can also use CGLIB proxy by adding it as the dependency in the Spring AOP project.</li>
 *		<li>
 *			<strong>Weaving</strong>: It is the process of linking aspects with other objects to create the advised proxy objects. This can be done at compile time, load time or at runtime. Spring AOP performs weaving at the runtime.</li>
 *	</ol>
 *	<h3>AOP Advice Types</h3>
 *	<p>Based on the execution strategy of advices, they are of following types.</p>
 *	<ol>
 *		<li>
 *			<strong>Before Advice</strong>: These advices runs before the execution of join point methods. We can use <code>@Before</code> annotation to mark an advice type as Before advice.</li>
 *		<li>
 *			<strong>After (finally) Advice</strong>: An advice that gets executed after the join point method finishes executing, whether normally or by throwing an exception. We can create after advice using <code>@After</code> annotation.</li>
 *		<li>
 *			<strong>After Returning Advice</strong>: Sometimes we want advice methods to execute only if the join point method executes normally. We can use <code>@AfterReturning</code> annotation to mark a method as after returning advice.</li>
 *		<li>
 *			<strong>After Throwing Advice</strong>: This advice gets executed only when join point method throws exception, we can use it to rollback the transaction declaratively. We use <code>@AfterThrowing</code> annotation for this type of advice.</li>
 *		<li>
 *			<strong>Around Advice</strong>: This is the most important and powerful advice. This advice surrounds the join point method and we can also choose whether to execute the join point method or not. We can write advice code that gets executed before and after the execution of the join point method. It is the responsibility of around advice to invoke the join point method and return values if the method is returning something. We use <code>@Around</code> annotation to create around advice methods.</li>
 *	</ol>
 *
 * <p>In order to see the history of changes, please see the header on package
 * definition above.</p>
 *
 * @author hcomak
 * @created Jul 15, 2014
 * @version 0.0.1
 * @since Jul 15, 2014 training startup
 * @modified $LastChangedDate$
 */
package net.entrofi.sportslogtracker.springaop;