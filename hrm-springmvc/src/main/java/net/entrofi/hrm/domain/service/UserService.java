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
package net.entrofi.hrm.domain.service;

import java.util.List;

import net.entrofi.hrm.domain.persistence.entity.User;
import net.entrofi.hrm.domain.persistence.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * UserService<br/>
 * 
 * TODO Please document the type definition<br/>
 *
 * <p>In order to see the history of changes, please see the header on package
 * definition above.</p>
 *
 * @author hasan
 * @created Oct 11, 2014
 * @version TODO insert version number
 * @since TODO insert the product line in which file was created
 * @modified $LastChangedDate$
 */
@Service("userService")
@Repository
@Transactional
public class UserService implements PersistenceServiceBase<User, UserRepository> {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public void setRepository(UserRepository userRepository) {
		this.setRepository(userRepository);
		
	}

	@Override
	public List<User> findAll() {
		return (List<User>) this.userRepository.findAll();
	}

	@Override
	public User find(Long id) {
		return this.userRepository.findOne(id);
	}

	@Override
	public User persist(User user) {
		return this.userRepository.save(user);
	}

	@Override
	public void delete(User user) {
		this.userRepository.delete(user);
		
	}

}
