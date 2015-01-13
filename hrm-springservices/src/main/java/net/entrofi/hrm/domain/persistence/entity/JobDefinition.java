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
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
public class JobDefinition implements Serializable{

	/** 
	 * serialVersionUID TODO document the field 
	 */
	private static final long serialVersionUID = -3596136252102220137L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Version
	private int version;
	
	private String name;
	
	@Lob
	@Column(columnDefinition = "TEXT")
	private String description;
	
	@OneToMany(mappedBy = "jobDefinition", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Vacancy> vacancies = new HashSet<Vacancy>();

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
	 * The getter method of the field vacancies
	 * TODO Give field description
	 * @return the vacancies
	 */
	public Set<Vacancy> getVacancies() {
		return vacancies;
	}

	/**
	 * setVacancies is the setter method of the field vacancies
	 * TODO document the method
	 * @param vacancies the vacancies to set
	 */
	public void setVacancies(Set<Vacancy> vacancies) {
		this.vacancies = vacancies;
	}
	
	
	
	
}
