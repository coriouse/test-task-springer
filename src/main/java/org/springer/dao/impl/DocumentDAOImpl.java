package org.springer.dao.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springer.dao.DocumentDAO;
import org.springer.mapper.BookMapper;
import org.springer.mapper.DocumentMapper;
import org.springer.mapper.JournalMapper;
import org.springer.model.Book;
import org.springer.model.Journal;
import org.springer.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

/**
 * Class implement of Document dao
 * @author ogarkov_sa
 * @since 08.01.2014
 *
 */
@Service
public class DocumentDAOImpl implements DocumentDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(DocumentDAOImpl .class);
	
	@Autowired
	JdbcTemplate jdbcTemplate; 
	
	@Resource
	@Qualifier("query")
	private Map<String, String> query;
	
	/**
	 * method get list of Documents
	 * @author ogarkov_sa
	 * @since 08.01.2014
	 */
	public List<Document> findAll() {
		try {			
			List<Document> list = jdbcTemplate.query(query.get("documents"),  new DocumentMapper());
			if(list != null && list.size() > 0) {
				return list;
			} else {
				Collections.emptyList();
			}	
		} catch (Exception e) {			
			logger.info("List of all users:",e);		
		}
		return Collections.emptyList();
	}
	
	
	/**
	 * method set watermark to document
	 * @author ogarkov_sa
	 * @since 08.01.2014
	 * @param id - document
	 * @return code 0 - watermark is , 1 - problem with server (i think it's primitive decision)
	 * 
	 */
	public Integer updateDocument(Long id, String type) {
			return  generateWaterMark(id, type);
	}
	
	/**
	 * generate 
	 * @param id
	 * @param type
	 * @return
	 */
	private Integer generateWaterMark(Long id, String type) {
		Document result = null;
		if(type.equalsIgnoreCase(TypeDocument.BOOK.toString())) {
			try{
				result = (Book)jdbcTemplate.queryForObject(query.get(type), new Object[]{type, id}, new BookMapper());
				Gson g = new Gson();
				logger.info("Generate Book="+g.toJson(result));
				result.setWatermark(g.toJson(result));
				save(result,id);
			} catch(Exception e) {
				logger.info("Book exception:", e );
				return 1;
			}
		} else if(type.equalsIgnoreCase(TypeDocument.JOURNAL.toString())) {
			
			try{
				result = (Journal)jdbcTemplate.queryForObject(query.get(type), new Object[]{type, id}, new JournalMapper());
				Gson g = new Gson();
				logger.info("Generate Journal="+g.toJson(result));
				result.setWatermark(g.toJson(result));
				save(result, id);
			} catch(Exception e) {
				logger.info("Journal exception:", e );
				return 1;
			}			
		}		
		return 0;		
	}
	
	/**
	 * Method update watermark
	 * @author ogarkov_sa
	 * @since 08.01.2014
	 * @param document
	 * @throws Exception
	 */
	private void save(Document document, Long id) throws Exception {		
		jdbcTemplate.update(query.get("updateWatermark"), document.getWatermark(), id);
	}
	
	
	/**
	 * get document by Id
	 * @author ogarkov_sa
	 * @since 08.01.2014
	 * @param id - document
	 * @return Document
	 * 
	 */
	public Document getDocumentById(Long id) {
		try {			
			Document result = (Document)jdbcTemplate.queryForObject(query.get("document"), new Object[]{id}, new DocumentMapper());
			return result;
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch(Exception e) {
			logger.info("get Document by id:",e);
		}
		return null;
	}

}
