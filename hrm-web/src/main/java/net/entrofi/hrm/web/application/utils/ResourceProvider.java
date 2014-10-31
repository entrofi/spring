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
package net.entrofi.hrm.web.application.utils;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

/**
 * FacesUtil<br/>
 * 
 * TODO Please document the type definition<br/>
 *
 * <p>In order to see the history of changes, please see the header on package
 * definition above.</p>
 *
 * @author hcomak
 * @created Oct 31, 2014
 * @version TODO insert version number
 * @since TODO insert the product line in which file was created
 * @modified $LastChangedDate$
 */
public class ResourceProvider {
	
	public static final String MESSAGES_BUNDLE_NAME = "messages";
	
	public static final String LABELS_BUNDLE_NAME = "labels";
	
	public static final String INFO_BUNDLE_NAME = "info";
	
	private ResourceProvider(){};

	/**
	 * 
	 * getResourceValue TODO Document the method
	 *
	 * @param bundleName
	 * @param key
	 * @return
	 */
	public static String getResourceValue(String bundleName, String key){
		String value = null;
		try {
			value = getResourceBundle(bundleName).getString(key);
		} catch (MissingResourceException e) {
			value = "???" + key + "??? not found";
		}
		return value;
	}
	
	
	/**
	 * 
	 * getResourceBundle TODO Document the method
	 *
	 * @param bundleName
	 * @return
	 */
	public static ResourceBundle getResourceBundle(String bundleName){
		FacesContext context = FacesContext.getCurrentInstance();
		return context.getApplication().getResourceBundle(context, bundleName);
	}
	
	/**
	 * 
	 * getMessage TODO Document the method
	 *
	 * @param key
	 * @return
	 */
	public static String getMessage(String key){
		return getResourceValue(MESSAGES_BUNDLE_NAME, key);
	}
	
	/**
	 * 
	 * getInfo TODO Document the method
	 *
	 * @param key
	 * @return
	 */
	public static String getInfo(String key){
		return getResourceValue(INFO_BUNDLE_NAME, key);
	}
	
	/**
	 * 
	 * getLabel TODO Document the method
	 *
	 * @param key
	 * @return
	 */
	public static String getLabel(String key){
		return getResourceValue(LABELS_BUNDLE_NAME, key);
	}
}
