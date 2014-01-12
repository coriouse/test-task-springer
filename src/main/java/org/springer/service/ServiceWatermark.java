package org.springer.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springer.dao.impl.DocumentDAOImpl;
import org.springer.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service to DAO and utilis class and etc
 * @author ogarkov_sa
 * @since 08.01.2014
 */

@Service
public class ServiceWatermark {
	
	private static final Logger logger = LoggerFactory.getLogger(ServiceWatermark .class);
	
	@Autowired
	DocumentDAOImpl documentDAO;
	
	
	public List<Document> findAll() {
		return documentDAO.findAll();
	}
	
	public Integer updateDocument(Long id, String type) {
		return documentDAO.updateDocument(id, type);
	}
	
	public Document getDocumentById(Long id) {
		return documentDAO.getDocumentById(id);
	}
	
	public Integer cleanWatermark(Long id) {
		try {
			documentDAO.cleanWatermark(id);
		} catch(Exception e) {
			return 1;
		}	
		return 0;
	}
	
	

}
