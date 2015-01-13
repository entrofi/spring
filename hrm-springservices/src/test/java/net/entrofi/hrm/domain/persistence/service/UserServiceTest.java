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
package net.entrofi.hrm.domain.persistence.service;

import static org.junit.Assert.*;
import net.entrofi.hrm.domain.persistence.entity.User;
import net.entrofi.hrm.domain.persistence.repository.UserRepository;
import net.entrofi.hrm.domain.service.PersistenceServiceBase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * UserServiceTest<br/>
 * 
 * TODO Please document the type definition<br/>
 *
 * <p>In order to see the history of changes, please see the header on package
 * definition above.</p>
 *
 * @author hasan
 * @created Nov 6, 2014
 * @version TODO insert version number
 * @since TODO insert the product line in which file was created
 * @modified $LastChangedDate$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/application-config.xml"})
public class UserServiceTest {
	
	@Autowired
	PersistenceServiceBase<User, UserRepository> userService;
	
	@Test
	public void testCreateUser(){
		User user = new User();
		user.setName("test_user");
		user.setEmail("testuser@email.com");
		user.setLastName("lastname");
		user.setPassword("test123");
		user.setUsername("testUsername"+Math.random());
		User persistedUser = userService.persist(user);
		assertNotNull(persistedUser);
	}

}
