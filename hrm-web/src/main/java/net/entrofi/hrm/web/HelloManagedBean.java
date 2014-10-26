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
package net.entrofi.hrm.web;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import tr.com.innova.hrm.domain.persistence.entity.Contact;
import tr.com.innova.hrm.domain.service.ContactService;

/**
 * HelloManagedBean<br/>
 * 
 * A short introduction to JSF managed beans<br/>
 *
 * <p>In order to see the history of changes, please see the header on package
 * definition above.</p>
 *
 * @author hcomak
 * @created Jun 24, 2014
 * @version 0.0.0
 * @since draft
 * @modified $LastChangedDate$
 */

public class HelloManagedBean {
	

	@ManagedProperty(value="#{contactService}")
	private ContactService contactService;
	
	private String greetWho;
	
	private String greetingMessage = "";
	
	private final String INDEX_PAGE = "";
	
	private List<Contact> contactList = new ArrayList<Contact>();

	
	
	public String sayHello(){
		if(greetWho != null && !greetWho.isEmpty()){
			System.out.println("We can say hello now");
			greetingMessage = "We are studying JSF and gradually experiencing how easy web development is using JSF";
		}else{
			System.out.println("We will not say hello... :(");
			greetingMessage = "You are so rude! Why didn't you provide your name?";
		}
		
		return INDEX_PAGE;
	}
	
	
	public String reset(){
		System.out.println("Resetting values...");
		greetWho = null;
		greetingMessage = null;
		return INDEX_PAGE;
	}
	
	/**
	 * The getter method of the field greetWho
	 * TODO Give field description
	 * @return the greetWho
	 */
	public String getGreetWho() {
		return greetWho;
	}

	/**
	 * setGreetWho is the setter method of the field greetWho
	 * TODO document the method
	 * @param greetWho the greetWho to set
	 */
	public void setGreetWho(String greetWho) {
		this.greetWho = greetWho;
	}


	/**
	 * The getter method of the field greetingMessage
	 * TODO Give field description
	 * @return the greetingMessage
	 */
	public String getGreetingMessage() {
		return greetingMessage;
	}


	/**
	 * setGreetingMessage is the setter method of the field greetingMessage
	 * TODO document the method
	 * @param greetingMessage the greetingMessage to set
	 */
	public void setGreetingMessage(String greetingMessage) {
		this.greetingMessage = greetingMessage;
	}


	public ContactService getContactService() {
		return contactService;
	}


	public void setContactService(ContactService contactService) {
		this.contactService = contactService;
	}
	
	public List<Contact> getContactList(){
		contactList =  contactService.findAll();
		return contactList;
	}
	
	
}