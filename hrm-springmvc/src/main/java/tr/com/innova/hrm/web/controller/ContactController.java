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
package tr.com.innova.hrm.web.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tr.com.innova.hrm.domain.persistence.entity.Contact;
import tr.com.innova.hrm.domain.service.ContactService;



/**
 * ContactController<br/>
 * 
 * TODO Please document the type definition<br/>
 *
 * <p>In order to see the history of changes, please see the header on package
 * definition above.</p>
 *
 * @author hcomak
 * @created Sep 28, 2014
 * @version TODO insert version number
 * @since TODO insert the product line in which file was created
 * @modified $LastChangedDate$
 */
@RequestMapping("/contacts")
@Controller
public class ContactController {

	final Logger logger = LoggerFactory.getLogger(ContactController.class);
	
	@Autowired
	private ContactService contactService;
	
	
	@RequestMapping(value={"", "/list"}, method = RequestMethod.GET)
	public String list(Model uiModel){
		logger.info("Listing contacts");
		
		List<Contact> contacts = contactService.findAll();
		uiModel.addAttribute("contacts", contacts);
		
		logger.info("Number of contacts " + contacts.size());

		return "contacts/list";
	}
	
	@RequestMapping(value={"/edit"},method = RequestMethod.GET)
	public String create(@RequestParam Map<String, String>params,  Model model){
		logger.info(params.get("new") + " all params: " + params.toString());
		model.addAttribute(new Contact());
		return "contacts/edit";
		
	}
}
