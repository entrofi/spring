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

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;

/**
 * JobDefinition<br/>
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
public class Vacancy implements Serializable{

	/** 
	 * serialVersionUID TODO document the field 
	 */
	private static final long serialVersionUID = 5448676934708737871L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Version
	private int version;
	
	
	private String name;
	
	
	private String definition;
	

	@ManyToOne
	@JoinColumn(name = "job_definition_id")
	private JobDefinition jobDefinition;

	
	@OneToMany(mappedBy="vacancy")
	private Set<Application> applications = new HashSet<Application>();

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
	 * The getter method of the field definition
	 * TODO Give field description
	 * @return the definition
	 */
	public String getDefinition() {
		return definition;
	}


	/**
	 * setDefinition is the setter method of the field definition
	 * TODO document the method
	 * @param definition the definition to set
	 */
	public void setDefinition(String definition) {
		this.definition = definition;
	}


	/**
	 * The getter method of the field jobDefinition
	 * TODO Give field description
	 * @return the jobDefinition
	 */
	public JobDefinition getJobDefinition() {
		return jobDefinition;
	}


	/**
	 * setJobDefinition is the setter method of the field jobDefinition
	 * TODO document the method
	 * @param jobDefinition the jobDefinition to set
	 */
	public void setJobDefinition(JobDefinition jobDefinition) {
		this.jobDefinition = jobDefinition;
	}
	
	
	
	
	
	
}
