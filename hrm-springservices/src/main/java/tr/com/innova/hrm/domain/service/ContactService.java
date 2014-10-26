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

import tr.com.innova.hrm.domain.persistence.entity.Contact;

/**
 * ContactService<br/>
 * 
 * TODO Please document the type definition<br/>
 *
 * <p>In order to see the history of changes, please see the header on package
 * definition above.</p>
 *
 * @author hcomak
 * @created Sep 27, 2014
 * @version TODO insert version number
 * @since TODO insert the product line in which file was created
 * @modified $LastChangedDate$
 */
public interface ContactService {
	
	public List<Contact> findAll();
	
	public Contact findById(Long id);
	
	public Contact save(Contact contact);
	
	public void delete(Contact contact);
}
