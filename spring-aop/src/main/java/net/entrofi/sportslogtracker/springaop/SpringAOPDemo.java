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
package net.entrofi.sportslogtracker.springaop;

import net.entrofi.sportslogtracker.springaop.user.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * SpringAOPDemo<br/>
 * <p>
 * TODO Please document the type definition<br/>
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
public final class SpringAOPDemo {

    private SpringAOPDemo() {
    }

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

        System.out.println("Hello user: " + userService.getUser().getFirstname() + " "
                + userService.getUser().getSurname());


        userService.buildUser("John", "DOE", 20L, "john.doe");


    }

}


