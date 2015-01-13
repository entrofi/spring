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
package net.entrofi.hrm.web.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import net.entrofi.hrm.domain.persistence.entity.Contact;
import net.entrofi.hrm.domain.service.ContactService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;



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
//@SessionAttributes
public class ContactController {

	final Logger logger = LoggerFactory.getLogger(ContactController.class);
	
	@Autowired
	private ContactService contactService;
	
	private Contact contact;
	
	@RequestMapping(value = { "", "/list" }, method = RequestMethod.GET)
	public String list(Model uiModel) {
		logger.info("Listing contacts");

		List<Contact> contacts = contactService.findAll();
		uiModel.addAttribute("contacts", contacts);

		logger.info("Number of contacts " + contacts.size());

		return "contacts/list";
	}
	
	@RequestMapping(value={"/edit"},method = RequestMethod.GET)
	@ModelAttribute
	public String create(@RequestParam Map<String, String>params,  Model model){
		logger.info(params.get("new") + " all params: " + params.toString());
		Long contactId = Long.valueOf(params.get("contactId"));
		contact = fetchContact(contactId);
		model.addAttribute(contact);
		return "contacts/edit";
		
	}
	
	
	@RequestMapping(value="/{username}",method = RequestMethod.POST)
	public String save(@ModelAttribute final Contact contact, @PathVariable String username, BindingResult bindingResult){
		logger.info("Save2 method called... <<2>>");
		if(bindingResult.hasErrors()){
			return "contacts/edit";
		}
		contactService.save(contact);
		return "contacts/list";
	}
	
	@ModelAttribute("contact")
	public Contact fetchContact(Long contactId){
		if(contactId != null && contactId != 0){
			contact = contactService.findById(contactId);
		}else{
			contact = new Contact();
		}
		return contact;
	}
}
