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
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.spel.support.ReflectionHelper.ArgumentsMatchInfo;

import tr.com.innova.hrm.domain.persistence.entity.User;
import tr.com.innova.hrm.domain.persistence.repository.UserRepository;
import tr.com.innova.hrm.domain.service.PersistenceServiceBase;
import tr.com.innova.hrm.domain.service.UserService;

/**
 * UserBean<br/>
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
@ManagedBean(name = "userBean")
@SessionScoped
public class UserBean {
	
	private static final Logger log = LoggerFactory.getLogger(UserBean.class);

	private User user;
	
	@ManagedProperty(value="#{userService}")
	private PersistenceServiceBase<User, UserRepository> userService;
	
//	@ManagedProperty("#{param.entiyId}")
	private Long entityId;
	
	private List<User> userList = new ArrayList<User>();
	
	
	
	public String edit(){
		getEntityIdParam(FacesContext.getCurrentInstance());
		if(this.entityId != null){
			this.user = userService.find(this.entityId);
		}else{
			this.user = new User();
		}
		return "edit";
	}
	
	public String save(){
		userService.persist(user);
		return "list";
	}

	private Long getEntityIdParam(FacesContext context){
		Map<String, String> requestParams = context.getExternalContext().getRequestParameterMap();
		entityId = Long.valueOf(requestParams.get("entityId"));
		log.debug("entityId parameter: " + entityId);
		return entityId;		
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public PersistenceServiceBase<User, UserRepository> getUserService() {
		return userService;
	}

	public void setUserService(
			PersistenceServiceBase<User, UserRepository> userService) {
		this.userService = userService;
	}

	
	
	
	
	
}
