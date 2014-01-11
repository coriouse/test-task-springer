package org.springer.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;


import org.springer.model.Document;
import org.springframework.jdbc.core.RowMapper;

/**
 * class mapper of all Document
 * @author ogarkov_sa 
 * @since 08.01.2014
 */
public class DocumentMapper implements RowMapper<Document> {
	@Override
	public Document mapRow(ResultSet resultSet, int i) throws SQLException {
		Document document = new Document();
			document.setId(resultSet.getLong("id"));
			document.setAuthor(resultSet.getString("author"));	
			document.setTitle(resultSet.getString("title"));
			document.setWatermark(resultSet.getString("watermark"));	
			document.setType(resultSet.getString("content"));
		return document;
	}
}
