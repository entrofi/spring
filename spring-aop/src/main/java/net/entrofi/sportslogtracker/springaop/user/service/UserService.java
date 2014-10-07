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
package net.entrofi.sportslogtracker.springaop.user.service;


import java.util.Calendar;
import java.util.Date;

import net.entrofi.sportslogtracker.model.persistence.entity.User;

/**
 * UserService<br/>
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
public class UserService {

	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	public void buildUser(String firstname, String surname,  Long id, String username){
		getUser().setBirthday(new Date(Calendar.getInstance().getTime().getTime() - 900));
		getUser().setFirstname(firstname);
		getUser().setSurname(surname);
		getUser().setUsername(username);
		getUser().setId(id);
	}

}