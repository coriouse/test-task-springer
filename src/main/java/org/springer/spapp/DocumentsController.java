package org.springer.spapp;

import org.springer.service.ServiceWatermark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * controller of handle documents
 * @author ogarkov_sa 
 * @since 11.01.2014
 * 
 */

@Controller
@RequestMapping("document")
public class DocumentsController {
	
	final static String DOCUMENTS_PAGE = "documents";
	
	@Autowired
	ServiceWatermark serviceWatermark;
	
	/**
	 * List of documents
	 * @author ogarkov_sa
	 * @since 11.01.2014 
	 * @param model
	 * @return list of document
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String documents(Model model) {
		model.addAttribute("documents", serviceWatermark.findAll());
		return DocumentsController.DOCUMENTS_PAGE;
	}

}
