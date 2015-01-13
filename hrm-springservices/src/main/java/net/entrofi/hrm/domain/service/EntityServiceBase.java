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

import org.springframework.data.repository.CrudRepository;

/**
 * EntityRepository<br/>
 * 
 * TODO Please document the type definition<br/>
 *
 * <p>In order to see the history of changes, please see the header on package
 * definition above.</p>
 *
 * @author hasan
 * @created Oct 29, 2014
 * @version TODO insert version number
 * @since TODO insert the product line in which file was created
 * @modified $LastChangedDate$
 */
public abstract class EntityServiceBase<T, K extends CrudRepository<?, ?>> implements PersistenceServiceBase<T, K> {

	private Object id;
	
	private final String UNSUPPORTED_OPERATION_MESSAGE = "This method is not supported EntityServiceBase instances";
	
	protected T instance;
	
	@Override
	public void setRepository(K k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public final List<T> findAll() {
		throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_MESSAGE);
	}

	@Override
	public T find(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T persist(T t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(T t) {
		// TODO Auto-generated method stub
		
	}

	public Object getId() {
		return id;
	}

	public void setId(Object id) {
		this.id = id;
	}

	public T getInstance() {
		return instance;
	}

	public void setInstance(T instance) {
		this.instance = instance;
	}
	
	
	

	
	
	
}
