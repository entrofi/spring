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
package net.entrofi.hrm.web.application;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * LoginBean<br/>
 * 
 * TODO Please document the type definition<br/>
 *
 * <p>In order to see the history of changes, please see the header on package
 * definition above.</p>
 *
 * @author hasan
 * @created Oct 26, 2014
 * @version TODO insert version number
 * @since TODO insert the product line in which file was created
 * @modified $LastChangedDate$
 */
@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean {

	Logger logger = LoggerFactory.getLogger(LoginBean.class);
	
	private Authentication authentication;
	
	public String login() throws IOException, ServletException{
		logger.debug("Logging in user");
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		RequestDispatcher dispatcher = ((ServletRequest)context.getRequest()).getRequestDispatcher("/static/j_spring_security_check");
		dispatcher.forward((ServletRequest)context.getRequest(), (ServletResponse)context.getResponse());
 
        FacesContext.getCurrentInstance().responseComplete();
        SecurityContext securityContext = SecurityContextHolder.getContext();
        logger.debug("Log in operation details\n User authenticated: " + securityContext.getAuthentication().isAuthenticated());
        logger.debug("User name: " + securityContext.getAuthentication().getName());
        this.authentication = securityContext.getAuthentication();
        
        return null;
	}
	
	public String logout() throws IOException, ServletException{
		SecurityContextHolder.clearContext();
		this.authentication = null;
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		logger.debug("application context path: " + context.getApplicationContextPath());
		context.redirect(context.getApplicationContextPath()+ "/index.xhtml");
		return null;
	}

	public Authentication getAuthentication() {
		return authentication;
	}

	public void setAuthentication(Authentication authentication) {
		this.authentication = authentication;
	}
	
	
	
}
