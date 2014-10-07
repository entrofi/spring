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
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Version;

/**
 * Contact<br/>
 * 
 * TODO Please document the type definition<br/>
 *
 * <p>In order to see the history of changes, please see the header on package
 * definition above.</p>
 *
 * @author hcomak
 * @created Sep 27, 2014
 * @version TODO insert version number
 * @since TODO insert the product line in which file was created
 * @modified $LastChangedDate$
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "Contact.findAll", query = "select c from Contact c"),
		@NamedQuery(name = "Contact.findById", query = "select distinct c from Contact c left join fetch c.contactDetails t left join fetch c.hobbies h where c.id = :id"),
		@NamedQuery(name = "Contact.findAllWithDetail", query = "select distinct c from Contact c left join fetch c.contactDetails t left join fetch c.hobbies h") })
@SqlResultSetMapping(name = "contactResult", entities = @EntityResult(entityClass = Contact.class))
public class Contact implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Version
	private int version;

	private String firstName;
	
	private String lastName;
	
	private Date birthDate;

	private String description;

	@Basic(fetch = FetchType.LAZY)
	@Lob
	private byte[] photo;
	
	@OneToMany(mappedBy ="contact", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<ContactPhoneDetail> contactDetails = new HashSet<ContactPhoneDetail>();
	
	@ManyToMany
	@JoinTable(name = "CONTACT_HOBBY_DETAIL", 
		joinColumns = @JoinColumn(name = "CONTACT_ID"), 
		inverseJoinColumns = @JoinColumn(name ="HOBBY_ID"))
	private Set<Hobby> hobbies = new HashSet<Hobby>();

	/**
	 * The getter method of the field id
	 * TODO Give field description
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * setId is the setter method of the field id
	 * TODO document the method
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * The getter method of the field version
	 * TODO Give field description
	 * @return the version
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * setVersion is the setter method of the field version
	 * TODO document the method
	 * @param version the version to set
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * The getter method of the field firstName
	 * TODO Give field description
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * setFirstName is the setter method of the field firstName
	 * TODO document the method
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * The getter method of the field lastName
	 * TODO Give field description
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * setLastName is the setter method of the field lastName
	 * TODO document the method
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * The getter method of the field birthDate
	 * TODO Give field description
	 * @return the birthDate
	 */
	public Date getBirthDate() {
		return birthDate;
	}

	/**
	 * setBirthDate is the setter method of the field birthDate
	 * TODO document the method
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
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

	/**
	 * The getter method of the field photo
	 * TODO Give field description
	 * @return the photo
	 */
	public byte[] getPhoto() {
		return photo;
	}

	/**
	 * setPhoto is the setter method of the field photo
	 * TODO document the method
	 * @param photo the photo to set
	 */
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	/**
	 * The getter method of the field contactDetails
	 * TODO Give field description
	 * @return the contactDetails
	 */
	public Set<ContactPhoneDetail> getContactDetails() {
		return contactDetails;
	}

	/**
	 * setContactDetails is the setter method of the field contactDetails
	 * TODO document the method
	 * @param contactDetails the contactDetails to set
	 */
	public void setContactDetails(Set<ContactPhoneDetail> contactDetails) {
		this.contactDetails = contactDetails;
	}

	/**
	 * The getter method of the field hobbies
	 * TODO Give field description
	 * @return the hobbies
	 */
	public Set<Hobby> getHobbies() {
		return hobbies;
	}

	/**
	 * setHobbies is the setter method of the field hobbies
	 * TODO document the method
	 * @param hobbies the hobbies to set
	 */
	public void setHobbies(Set<Hobby> hobbies) {
		this.hobbies = hobbies;
	}
	
	
	
	@Override
	public String toString(){
		return "First name: " + firstName + " \nSurname: " + lastName;
	}
	
	
	
}
