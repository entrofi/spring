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
package net.entrofi.hrm.web.application.info;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 * ApplicationInfo<br/>
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
@ManagedBean(name="applicationInfo")
@ApplicationScoped
public class ApplicationInfo {
	
	private String name;

	private String version;
	
	private String copyrightNotice;
	
	public ApplicationInfo(){
		this.name = "Tutor's HRM";
		this.version = "0.0.1-SNAPSHOT";
		this.copyrightNotice = "&copy; ACME" + "Suspendisse id ultrices sapien, a suscipit nibh. Nam posuere metus sapien, id mollis risus mollis eget. ";
	}
	
	

	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getCopyrightNotice() {
		return copyrightNotice;
	}

	public void setCopyrightNotice(String copyrightNotice) {
		this.copyrightNotice = copyrightNotice;
	}
	
	
}
