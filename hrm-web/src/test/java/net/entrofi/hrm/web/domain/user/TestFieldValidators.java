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
package net.entrofi.hrm.web.domain.user;

import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

/**
 * FieldValidatorTests<br/>
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
public class TestFieldValidators {
	
	@Test
	public void testPasswordMatcher(){
		String password = "Qwer$123";
		String passwordFail = "qwer$123";
		String passwordSuccess = "Qwer+123";
		assertTrue(validatePassPattern(password));
		assertTrue(validatePassPattern(passwordSuccess));
		assertFalse(validatePassPattern(passwordFail));
	}
	
	
	private boolean validatePassPattern(String password){
		String quoted = Pattern.quote("@#$%+=-_!");
		String passPattern = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*["+quoted+"]).{6,20})";
		Pattern pattern = Pattern.compile(passPattern);
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();
	}

}
