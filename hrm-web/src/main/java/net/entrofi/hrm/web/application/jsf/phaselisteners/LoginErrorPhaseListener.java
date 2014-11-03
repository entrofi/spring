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
package net.entrofi.hrm.web.application.jsf.phaselisteners;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import net.entrofi.hrm.web.application.utils.ResourceProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.WebAttributes;

/**
 * LoginErrorPhaseListener<br/>
 * 
 * TODO Please document the type definition<br/>
 *
 * <p>In order to see the history of changes, please see the header on package
 * definition above.</p>
 *
 * @author hasan
 * @created Nov 2, 2014
 * @version TODO insert version number
 * @since TODO insert the product line in which file was created
 * @modified $LastChangedDate$
 */
public class LoginErrorPhaseListener implements PhaseListener {
	
	private static Logger log = LoggerFactory.getLogger(LoginErrorPhaseListener.class);

	/** 
	 * serialVersionUID TODO document the field 
	 */
	private static final long serialVersionUID = 6786089742879309875L;

	@Override
	public void afterPhase(final PhaseEvent event) {
		log.debug(LoginErrorPhaseListener.class.getName() + "afterPhase called");
		
	}

	@Override
	public void beforePhase(final PhaseEvent event) {
		
		FacesContext context = FacesContext.getCurrentInstance();
		 Exception e = (Exception) context.getExternalContext().getSessionMap().get(
	                WebAttributes.AUTHENTICATION_EXCEPTION);
		 log.debug(LoginErrorPhaseListener.class.getName() + " found exception: ", e);
	 
	        if (e instanceof BadCredentialsException)
	        {
	            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(
	                   WebAttributes.AUTHENTICATION_EXCEPTION, null);
	            context.addMessage(null, new FacesMessage(ResourceProvider.getMessage("commons.credentials.invalid")));
	        }
		
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}

}
