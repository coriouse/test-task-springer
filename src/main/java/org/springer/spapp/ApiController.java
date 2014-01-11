package org.springer.spapp;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springer.service.ServiceWatermark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

/**
 * api controller of rest service
 * @author ogarkov_sa 
 * @since 08.01.2014
 * 
 */
@Controller
@RequestMapping("api")
public class ApiController {
	
	@Autowired
	ServiceWatermark serviceWatermark;
	
	private static final Logger logger = LoggerFactory.getLogger(ApiController.class);
	
	
	/**
	 * Methos of set watermark to document
	 * 
	 * @author ogarkov_sa
	 * @since 08.01.2014	 * 
	 * @param id - document id
	 * @return code
	 */
	 @RequestMapping(value = "put/{id}/{type}", method = RequestMethod.PUT)
	 @ResponseBody
	 public Integer setWatermarkById(@PathVariable Long id, @PathVariable String type) {
		 return serviceWatermark.updateDocument(id, type);
	 }

	/**
	 * Method of get status
	 * 
	 * @author ogarkov_sa
	 * @since 08.01.2014
	 * @param id
	 * @return json
	 */
	 @RequestMapping(value = "status/{id}", method = RequestMethod.GET)
	 @ResponseBody
	 public String getStatus(@PathVariable Long id) {		
		 return serviceWatermark.getDocumentById(id).getWatermark() ;
	 }
			
}
