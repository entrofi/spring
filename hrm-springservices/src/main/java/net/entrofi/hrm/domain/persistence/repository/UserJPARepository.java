/**
 * 
 */
package net.entrofi.hrm.domain.persistence.repository;

import net.entrofi.hrm.domain.persistence.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author hcomak
 *
 */
public interface UserJPARepository extends JpaRepository<User, Long> {

}
