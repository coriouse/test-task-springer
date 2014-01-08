package org.springer.dao.impl;

import java.util.Collections;
import java.util.List;
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
	
	/**
	 * method get list of Documents
	 * @author ogarkov_sa
	 * @since 08.01.2014
	 */
	public List<Document> findAll() {
		try {
			String sql = "select id, author, title, watermark from t_document";
			List<Document> list = jdbcTemplate.query(sql,  new DocumentMapper());
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
		if("book".equals(type)) {
			String sql = "select  c.content, d.title, d.author, t.topic from t_document d inner join t_type_content c on d.type_content = c.id " +  
						" inner join  t_document_topic dt on dt.document = d.id "+ 
						" inner join t_topic t on dt.topic=t.id where c.content =? and d.id=? ";
			try{
				result = (Book)jdbcTemplate.queryForObject(sql, new Object[]{type, id}, new BookMapper());
				Gson g = new Gson();
				logger.info("Generate Book="+g.toJson(result));
				result.setWatermark(g.toJson(result));
				save(result,id);
			} catch(Exception e) {
				logger.info("Book exception:", e );
				return 1;
			}
		} else if("journal".equals(type)) {
			String sql = "select  c.content, d.title, d.author from "+ 
						 " t_document d inner join t_type_content c on d.type_content = c.id "+  
						 " where c.content = ? and d.id= ?"; 
			try{
				result = (Journal)jdbcTemplate.queryForObject(sql, new Object[]{type, id}, new JournalMapper());
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
		jdbcTemplate.update("update t_document t set t.watermark = ?  where t.id = ?", document.getWatermark(), id);
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
			String sql = "select id, author, title, watermark from t_document where id=?";
			Document result = (Document)jdbcTemplate.queryForObject(sql, new Object[]{id}, new DocumentMapper());
			return result;
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch(Exception e) {
			logger.info("get Document by id:",e);
		}
		return null;
	}

}
