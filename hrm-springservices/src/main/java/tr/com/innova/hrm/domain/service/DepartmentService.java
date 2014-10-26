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

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.com.innova.hrm.domain.persistence.entity.Department;
import tr.com.innova.hrm.domain.persistence.repository.DepartmentRepository;

import com.google.common.collect.Lists;

/**
 * DepartmentService<br/>
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
@Service("departmentService")
@Repository
@Transactional
public class DepartmentService implements PersistenceServiceBase<Department, DepartmentRepository>{

	DepartmentRepository departmentRepository;
	/**
	 * This method is overriding the getRepository method.
	 * TODO Document the method
	 *
	 * @see tr.com.innova.hrm.domain.service.PersistenceServiceBase#getRepository()
	 */
	@Override
	public void setRepository(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}

	/**
	 * This method is overriding the findAll method.
	 * TODO Document the method
	 *
	 * @see tr.com.innova.hrm.domain.service.PersistenceServiceBase#findAll()
	 */
	@Override
	public List<Department> findAll() {
		return Lists.newArrayList(departmentRepository.findAll());
	}

	/**
	 * This method is overriding the find method.
	 * TODO Document the method
	 *
	 * @see tr.com.innova.hrm.domain.service.PersistenceServiceBase#find(java.lang.Long)
	 */
	@Override
	public Department find(Long id) {
		return departmentRepository.findOne(id);
	}

	/**
	 * This method is overriding the persist method.
	 * TODO Document the method
	 *
	 * @see tr.com.innova.hrm.domain.service.PersistenceServiceBase#persist(java.lang.Object)
	 */
	@Override
	public Department persist(Department t) {
		return departmentRepository.save(t);
	}

	/**
	 * This method is overriding the delete method.
	 * TODO Document the method
	 *
	 * @see tr.com.innova.hrm.domain.service.PersistenceServiceBase#delete(java.lang.Object)
	 */
	@Override
	public void delete(Department t) {
		departmentRepository.delete(t);
		
	}

}
