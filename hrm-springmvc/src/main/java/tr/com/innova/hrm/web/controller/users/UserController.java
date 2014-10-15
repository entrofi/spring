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
package tr.com.innova.hrm.web.controller.users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tr.com.innova.hrm.domain.persistence.entity.Department;
import tr.com.innova.hrm.domain.persistence.entity.User;
import tr.com.innova.hrm.domain.persistence.repository.DepartmentRepository;
import tr.com.innova.hrm.domain.persistence.repository.UserRepository;
import tr.com.innova.hrm.domain.service.ContactService;
import tr.com.innova.hrm.domain.service.PersistenceServiceBase;

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
	
	
	@RequestMapping(value = { "", "/list" }, method = RequestMethod.GET)
	public String list(Model uiModel) {
		logger.debug("Listing users....");
		
		return "users/list";
	}

}
