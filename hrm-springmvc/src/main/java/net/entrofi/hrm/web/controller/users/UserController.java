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
package net.entrofi.hrm.web.controller.users;

import java.util.List;
import java.util.Map;

import net.entrofi.hrm.domain.persistence.entity.Department;
import net.entrofi.hrm.domain.persistence.entity.User;
import net.entrofi.hrm.domain.persistence.repository.DepartmentRepository;
import net.entrofi.hrm.domain.persistence.repository.UserRepository;
import net.entrofi.hrm.domain.service.PersistenceServiceBase;
import net.entrofi.hrm.web.controller.FormActions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * UserController<br/>
 * 
 * TODO Please document the type definition<br/>
 *
 * <p>In order to see the history of changes, please see the header on package
 * definition above.</p>
 *
 * @author hasan
 * @created Oct 11, 2014
 * @version TODO insert version number
 * @since TODO insert the product line in which file was created
 * @modified $LastChangedDate$
 */
@Controller
@RequestMapping("/users")
public class UserController {
	
	
	final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	@Qualifier("userService")
	private PersistenceServiceBase<User, UserRepository> userService;
	

	@Autowired
	@Qualifier("departmentService")
	private PersistenceServiceBase<Department, DepartmentRepository> departmentService;
	
	
	@RequestMapping(value = { "", ContextTags.CONTEXT_DELIMITER + ContextTags.LIST_SUFFIX }, method = RequestMethod.GET)
	public String list(Model uiModel) {
		logger.debug("Listing users....");
		List<User> userList = userService.findAll();
		uiModel.addAttribute(userList);
		return ContextTags.LIST_CONTEXT;
	}

	
	@RequestMapping(value={ContextTags.CONTEXT_DELIMITER + ContextTags.EDIT_SUFFIX},method = RequestMethod.GET)
	@ModelAttribute
	public String create(@RequestParam Map<String, String>params,  Model model){
		logger.info(params.get("new") + " all params: " + params.toString());
		Long id = params.get("id") != null ? Long.valueOf(params.get("id")) : null;
		User user = fetchUser(id);
		model.addAttribute(user);
		return ContextTags.EDIT_CONTEXT;
		
	}
	
	@RequestMapping(value={ContextTags.CONTEXT_DELIMITER + ContextTags.EDIT_SUFFIX},method = RequestMethod.POST)
	public String handleButtonFormAction(@RequestParam String buttonFormAction, @ModelAttribute final User user, BindingResult bindingResult ){
		if(bindingResult.hasErrors()){//TODO add if not cancel
			return ContextTags.EDIT_CONTEXT;
		}else{
			if(buttonFormAction.equalsIgnoreCase(FormActions.SAVE.toString())){
				userService.persist(user);
			}
		}
		return "redirect:"+ContextTags.LIST_SUFFIX;
	}
	
	@ModelAttribute("user")
	public User fetchUser(Long id){
		User user = null;
		if(id == null){
			user = new User();
		}else{
			user = userService.find(id);
		}
		return user;
	}
	
	
	public static class ContextTags{
		public static final String CONTEXT_BASE = "users";
		public static final String CONTEXT_DELIMITER = "/";
		public static final String EDIT_SUFFIX = "edit";
		public static final String LIST_SUFFIX = "list";
		public static final String EDIT_CONTEXT = CONTEXT_BASE + CONTEXT_DELIMITER + EDIT_SUFFIX;
		public static final String LIST_CONTEXT = CONTEXT_BASE + CONTEXT_DELIMITER + LIST_SUFFIX;
	}
}
