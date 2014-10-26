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
package tr.com.innova.hrm.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.com.innova.hrm.domain.persistence.entity.Contact;
import tr.com.innova.hrm.domain.persistence.repository.ContactRepository;

import com.google.common.collect.Lists;

/**
 * ContactServiceImpl<br/>
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
@Service("contactService")
@Repository
@Transactional
public class ContactServiceImpl implements ContactService {
	
	

	@Autowired
	ContactRepository contactRepository;
	/**
	 * This method is overriding the findAll method.
	 * TODO Document the method
	 *
	 * @see tr.com.innova.hrm.domain.service.ContactService#findAll()
	 */
	@Transactional(readOnly = true)
	public List<Contact> findAll() {
		return Lists.newArrayList(contactRepository.findAll());
	}

	/**
	 * This method is overriding the findById method.
	 * TODO Document the method
	 *
	 * @see tr.com.innova.hrm.domain.service.ContactService#findById(java.lang.Long)
	 */
	@Transactional(readOnly = true)
	public Contact findById(Long id) {
		return contactRepository.findOne(id);
	}

	/**
	 * This method is overriding the save method.
	 * TODO Document the method
	 *
	 * @see tr.com.innova.hrm.domain.service.ContactService#save(tr.com.innova.hrm.domain.persistence.entity.Contact)
	 */
	public Contact save(Contact contact) {
		return contactRepository.save(contact);
	}

	/**
	 * This method is overriding the delete method.
	 * TODO Document the method
	 *
	 * @see tr.com.innova.hrm.domain.service.ContactService#delete(tr.com.innova.hrm.domain.persistence.entity.Contact)
	 */
	public void delete(Contact contact) {
		contactRepository.delete(contact);
	}

}
