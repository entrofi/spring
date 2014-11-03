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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import net.entrofi.hrm.web.application.utils.ResourceProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.com.innova.hrm.domain.persistence.entity.User;
import tr.com.innova.hrm.domain.persistence.repository.UserRepository;
import tr.com.innova.hrm.domain.service.PersistenceServiceBase;

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
		log.debug("Saving user: " + user.getId());
		userService.persist(user);
		return "list";
	}
	
	
	public String delete(){
		getEntityIdParam(FacesContext.getCurrentInstance());
		userService.delete(userService.find(entityId));
		return null;
	}

	private Long getEntityIdParam(FacesContext context){
		Map<String, String> requestParams = context.getExternalContext().getRequestParameterMap();
		entityId = requestParams.get("entityId") != null ? Long.valueOf(requestParams.get("entityId")) : null;
		log.debug("entityId parameter: " + entityId);
		return entityId;		
	}
	
	
	
	public void validatePassword(FacesContext context, UIComponent component, Object value) throws ValidatorException{
		UIComponent parentComponent = component.getParent();
		
		UIInput inputPassword = (UIInput) component;
		UIInput inputConfirmPassword = (UIInput)parentComponent.findComponent("confirmPassword");
		
		String password = inputPassword.getSubmittedValue() != null ? inputPassword.getSubmittedValue().toString() : "";
		String confirmPassword = inputConfirmPassword.getSubmittedValue() != null ? inputConfirmPassword.getSubmittedValue().toString(): "";
		log.debug("Password: " + password + ", Confirmation: " + confirmPassword);
		//We do not check whether the values are empty or not here because required attribute will handle it
		FacesMessage message;
		if(!password.equals(confirmPassword)){
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, ResourceProvider.getMessage("commons.password.confirm.notEqual"), "");
			throw new ValidatorException(message);
		}
		if(validatePasswordFormat(password) == false){
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, ResourceProvider.getMessage("commons.password.format"), "");
			throw new ValidatorException(message);
		}
	}
	
	
	private boolean validatePasswordFormat(String password){
		String quoted = Pattern.quote("@#$%+=-_!");
		String passPattern = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*["+quoted+"]).{6,20})";
		Pattern pattern = Pattern.compile(passPattern);
		Matcher matcher = pattern.matcher(password);
		log.debug("Password rule matches: " + matcher.matches());
		return matcher.matches();
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
