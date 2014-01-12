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
	
	

	public Integer updateDocument(Long id, String type) {
			return  generateWaterMark(id, type);
	}
	
	/**
	 * generate of watermark
	 * @param id
	 * @param type
	 * @return
	 */
	private Integer generateWaterMark(Long id, String type) {
		Document result = null;		
		if(id == null || type == null) {
			logger.info("id or typw is null: id ="+id+", type="+type  );
			return 1;
		}
			try{
				if(type.equalsIgnoreCase(TypeDocument.BOOK.toString())) {
					result = (Book)jdbcTemplate.queryForObject(query.get(type), new Object[]{type, id}, new BookMapper());
				} else if(type.equalsIgnoreCase(TypeDocument.JOURNAL.toString())) {
					result = (Journal)jdbcTemplate.queryForObject(query.get(type), new Object[]{type, id}, new JournalMapper());
				} else {
					logger.info("Document type is wrong:"+type );
					return 1;
				}
			} catch (EmptyResultDataAccessException e) {		
				logger.info("Document is not find:"+id );
				return 1;
			} catch(Exception e) {
				logger.info("Generator of watermark exception:", e );
				return 1;
			}	
			
			Gson g = new Gson();
			logger.info("Generate="+g.toJson(result));			
			result.setWatermark(g.toJson(result));
			result.setId(id);
			try {
				save(result);
			} catch(Exception e) {
				logger.info("Save of watermark exception:", e );
				return 1;
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
	private void save(Document document) throws Exception {		
		jdbcTemplate.update(query.get("updateWatermark"), document.getWatermark(), document.getId());
	}
	
	public void cleanWatermark(Long id) {
		jdbcTemplate.update(query.get("updateWatermark"), null, id);
	}
	
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
