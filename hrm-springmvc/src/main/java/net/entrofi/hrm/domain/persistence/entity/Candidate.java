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
package net.entrofi.hrm.domain.persistence.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Version;

/**
 * Candidate<br/>
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
@Inheritance(strategy = InheritanceType.JOINED)
public class Candidate extends User implements Serializable{

	/** 
	 * serialVersionUID TODO document the field 
	 */
	private static final long serialVersionUID = 6791178332870315847L;

	
	@OneToMany(mappedBy = "candidate")
	private List<Application> applications = new ArrayList<Application>();


	/**
	 * The getter method of the field applications
	 * TODO Give field description
	 * @return the applications
	 */
	public List<Application> getApplications() {
		return applications;
	}


	/**
	 * setApplications is the setter method of the field applications
	 * TODO document the method
	 * @param applications the applications to set
	 */
	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}
	
	
	
	
}
