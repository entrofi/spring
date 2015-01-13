/**
 * 
 */
package net.entrofi.hrm.domain.persistence.repository.querydsl;

import net.entrofi.hrm.domain.persistence.entity.Department;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * @author hcomak
 *
 */
public interface DepartmentDSLJPARepository extends JpaRepository<Department, Long>, QueryDslPredicateExecutor<Department>{

}
