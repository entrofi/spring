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
package tr.com.innova.hrm.domain.persistence.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 * Hobby<br/>
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
@Entity
public class Hobby implements Serializable {

	/** 
	 * serialVersionUID TODO document the field 
	 */
	private static final long serialVersionUID = 1242167562760150602L;


	@Id
	@Column(name = "HOBBY_ID")
	private String hobbyId;
	
	
	private String name;
	
	
	private String description;

	@ManyToMany
	@JoinTable(name = "CONTACT_HOBBY_DETAIL", 
		joinColumns = @JoinColumn(name = "HOBBY_ID"), 
		inverseJoinColumns = @JoinColumn(name ="CONTACT_ID"))
	private Set<Contact> contacts = new HashSet<Contact>();
	
	
	/**
	 * The getter method of the field hobbyId
	 * TODO Give field description
	 * @return the hobbyId
	 */
	public String getHobbyId() {
		return hobbyId;
	}

	/**
	 * setHobbyId is the setter method of the field hobbyId
	 * TODO document the method
	 * @param hobbyId the hobbyId to set
	 */
	public void setHobbyId(String hobbyId) {
		this.hobbyId = hobbyId;
	}

	/**
	 * The getter method of the field contacts
	 * TODO Give field description
	 * @return the contacts
	 */
	public Set<Contact> getContacts() {
		return contacts;
	}

	/**
	 * setContacts is the setter method of the field contacts
	 * TODO document the method
	 * @param contacts the contacts to set
	 */
	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}

	/**
	 * The getter method of the field name
	 * TODO Give field description
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * setName is the setter method of the field name
	 * TODO document the method
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * The getter method of the field description
	 * TODO Give field description
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * setDescription is the setter method of the field description
	 * TODO document the method
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
