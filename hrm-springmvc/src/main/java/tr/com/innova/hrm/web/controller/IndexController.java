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
package tr.com.innova.hrm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * IndexController<br/>
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
@RequestMapping(value ={"/", "/home", "index"})
@Controller
public class IndexController {

	
	@RequestMapping(value ={"/", "/home", "/index"})
	public String showIndex(Model model){
		model.addAttribute("pageTitle", "Welcome InnovaHRM");
		model.addAttribute("pageHeader", "Innova HRM");
		return "index";
	}
}
