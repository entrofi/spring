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
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Version;

/**
 * Department<br/>
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
@NamedQueries({
	@NamedQuery(name="Department.findAll", query = "select d from Department d"),
	@NamedQuery(name = "Department.findById", query = "select distinct d from Department d  where d.id = :id")
	})
@SqlResultSetMapping(name = "departmentResult", entities = @EntityResult(entityClass = Department.class))
public class Department implements Serializable {

	private static final long serialVersionUID = -1348183178264684018L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Version
	private int version;
	
	
	private String name;
	
	
	private String description;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	private Department parent;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy="parent")
	private Set<Department> departments = new HashSet<Department>();

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


	public Department getParent() {
		return parent;
	}


	public void setParent(Department parent) {
		this.parent = parent;
	}


	public Set<Department> getDepartments() {
		return departments;
	}


	public void setDepartments(Set<Department> departments) {
		this.departments = departments;
	}
	
	
	
	
	
	
}
