package org.springer.dao;

import java.util.List;

import org.springer.model.Document;

/**
 * DAO for document handle
 * @author ogarkov_sa
 * @since 08.01.2014
 *
 */
public interface DocumentDAO {
	
	
	public List<Document> findAll();
	
	public Integer updateDocument(Long id, String type);
	
	public Document getDocumentById(Long id);

}
