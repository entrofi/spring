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
package net.entrofi.sportslogtracker.springaop;

import net.entrofi.sportslogtracker.springaop.user.service.UserService;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * SpringAOPDemo<br/>
 * 
 * TODO Please document the type definition<br/>
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
public class SpringAOPDemo {

	/**
	 * main TODO Document the method
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		UserService userService = context.getBean("userService", UserService.class);
		
		System.out.println(userService.getUser().getFirstname());
		
		userService.getUser().setFirstname("Hasan");
		userService.getUser().setSurname("COMAK");
		
		System.out.println("Hello user: " + userService.getUser().getFirstname() + " " + userService.getUser().getSurname());

		
		userService.buildUser("John", "DOE", 20l, "john.doe");


	}

}
