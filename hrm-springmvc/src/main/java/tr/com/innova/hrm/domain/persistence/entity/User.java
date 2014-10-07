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
package tr.com.innova.hrm.domain.persistence.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 * User<br/>
 * 
 * TODO Please document the type definition<br/>
 *
 * <p>In order to see the history of changes, please see the header on package
 * definition above.</p>
 *
 * @author hcomak
 * @created Sep 29, 2014
 * @version TODO insert version number
 * @since TODO insert the product line in which file was created
 * @modified $LastChangedDate$
 */
@Entity
public class User implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Version
	private int version;
	
	private String username;
	
}
