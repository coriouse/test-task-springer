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
	
	/**
	 * method get list of Documents
	 * @author ogarkov_sa
	 * @since 08.01.2014
	 */
	public List<Document> findAll();
	
	/**
	 * method set watermark to document
	 * @author ogarkov_sa
	 * @since 08.01.2014
	 * @param id - document
	 * @return code 0 - watermark is , 1 - problem with server (i think it's primitive decision)
	 * 
	 */
	public Integer updateDocument(Long id, String type);
	
	/**
	 * get document by Id
	 * @author ogarkov_sa
	 * @since 08.01.2014
	 * @param id - document
	 * @return Document
	 * 
	 */
	public Document getDocumentById(Long id);
	
	/**
	 * clecn of watermark
	 * @author ogarkov_sa
	 * @since 12.01.2014
	 * @param id - document id
	 */
	public void cleanWatermark(Long id);

}
