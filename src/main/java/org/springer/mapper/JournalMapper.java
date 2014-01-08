package org.springer.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springer.model.Journal;
import org.springframework.jdbc.core.RowMapper;

/**
 * class mapper of Journal
 * @author ogarkov_sa 
 * @since 08.01.2014
 */
public class JournalMapper   implements RowMapper<Journal> {
	@Override
	public Journal mapRow(ResultSet resultSet, int i) throws SQLException {
		Journal document = new Journal();
			document.setContent(resultSet.getString("content"));
			document.setAuthor(resultSet.getString("author"));	
			document.setTitle(resultSet.getString("title"));
		return document;
	}
}
