/**
 * 
 */
package net.entrofi.hrm.domain.persistence.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

/**
 * @author hcomak
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Employee extends User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1968113711706858192L;
	
	@ManyToOne
	private Department department;

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
	
	
}
