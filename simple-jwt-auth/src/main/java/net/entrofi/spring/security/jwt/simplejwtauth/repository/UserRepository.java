/*
 * 2017 Hasan COMAK
 */
package net.entrofi.spring.security.jwt.simplejwtauth.repository;

import net.entrofi.spring.security.jwt.simplejwtauth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
