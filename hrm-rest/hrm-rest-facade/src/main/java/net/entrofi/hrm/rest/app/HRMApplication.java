/**
 * 
 */
package net.entrofi.hrm.rest.app;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

/**
 * @author hcomak
 *
 */
@ApplicationPath("/")
public class HRMApplication extends ResourceConfig {

	/**
	 * HRM REST facade application configuration. 
	 * !CHECKOUT is it possible to externalize this configuration. 
	 */
	public HRMApplication(){
		register(JacksonFeature.class);
		register(MultiPartFeature.class);
		
		// Enable Tracing support.
        property(ServerProperties.TRACING, "ALL");
        
        //Enable sending error in responses
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        
        String[] packages = {"net.entrofi.hrm.rest.resources", "net.entrofi.hrm.rest.app"};         
        property(ServerProperties.PROVIDER_PACKAGES, packages);
		
	}
	
}
