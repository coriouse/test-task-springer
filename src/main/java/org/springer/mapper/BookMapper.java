package org.springer.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springer.model.Book;
import org.springframework.jdbc.core.RowMapper;

/**
 * class mapper of Book
 * @author ogarkov_sa 
 * @since 08.01.2014
 */
public class BookMapper  implements RowMapper<Book> {
	@Override
	public Book mapRow(ResultSet resultSet, int i) throws SQLException {
		Book document = new Book();
			document.setContent(resultSet.getString("content"));
			document.setAuthor(resultSet.getString("author"));	
			document.setTitle(resultSet.getString("title"));
			document.setTopic(resultSet.getString("topic"));
		return document;
	}
}
