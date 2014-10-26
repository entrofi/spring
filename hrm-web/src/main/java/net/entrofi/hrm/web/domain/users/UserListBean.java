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
package net.entrofi.hrm.web.domain.users;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.com.innova.hrm.domain.persistence.entity.User;
import tr.com.innova.hrm.domain.persistence.repository.UserRepository;
import tr.com.innova.hrm.domain.service.PersistenceServiceBase;

/**
 * UserListBean<br/>
 * 
 * TODO Please document the type definition<br/>
 *
 * <p>In order to see the history of changes, please see the header on package
 * definition above.</p>
 *
 * @author hasan
 * @created Oct 25, 2014
 * @version TODO insert version number
 * @since TODO insert the product line in which file was created
 * @modified $LastChangedDate$
 */
@ManagedBean(name = "userList")
@SessionScoped
public class UserListBean {
	
	private static final Logger logger = LoggerFactory.getLogger(UserListBean.class);
	
	private List<User> list = new ArrayList<User>();
	
	@ManagedProperty(value="#{userService}")
	private PersistenceServiceBase<User, UserRepository> userService;

	
	public void refreshList(){
		//TODO we should add filtering support here
		list = userService.findAll();
		logger.debug("Refreshing the user list...........");
		
	}

	public List<User> getList() {
		return list;
	}

	public void setList(List<User> list) {
		this.list = list;
	}

	public PersistenceServiceBase<User, UserRepository> getUserService() {
		return userService;
	}

	public void setUserService(PersistenceServiceBase<User, UserRepository> userService) {
		this.userService = userService;
	}

	
	
	
	
	
	
	
}
