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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 * Position<br/>
 * 
 * TODO Please document the type definition<br/>
 *
 * <p>In order to see the history of changes, please see the header on package
 * definition above.</p>
 *
 * @author hcomak
 * @created Sep 29, 2014
 * @version TODO insert version number
 * @since TODO insert the product line in which file was created
 * @modified $LastChangedDate$
 */
@Entity
public class Position implements Serializable {

	
	private static final long serialVersionUID = -3868288393987996821L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Version
	private int version;
	
	@Column(columnDefinition = "TEXT", length=500)
	private String title;
	
	@Column(columnDefinition = "TEXT", length = 1000)
	private String description;

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
	 * The getter method of the field title
	 * TODO Give field description
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * setTitle is the setter method of the field title
	 * TODO document the method
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
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
