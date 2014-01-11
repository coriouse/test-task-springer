package org.springer.spapp;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springer.dao.impl.DocumentDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springer.model.Document;

/**
 * Test dao implementation
 * @author ogarkov_sa
 * @since 08.01.2014
 *
 */

@ContextConfiguration(locations = {"/root-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestWatermarkDao {

	@Autowired
	DocumentDAOImpl documentDAO;
	
	/**
	 * Test of get all document
	 * @author ogarkov_sa
	 * @since 08.01.2014
	 * 
	 * 
	 */
	@Test
	public void testFindAll() {		
		assertTrue(documentDAO.findAll().size() > 0);
		
		Document doc = null;
		for(Document d : documentDAO.findAll()) {
			if(d.getId() == 1) {
				doc = d;
				break;
			}
		}	
		assertEquals("The Dark Code", doc.getTitle());
		assertEquals("Bruce Wayne", doc.getAuthor());
		assertEquals("book",doc.getType());
	}
	
	//Book test
	@Test
	public void testSetWatermarkBook() {
		//book test
		Long id = 1L;
		String type = "book";
		//system return code 0-ok, 1-server error,  exception intercept logger and write to file or concole
		Integer code = documentDAO.updateDocument(id, type);
		//ok
		assertTrue( 0 == code);		
		assertNotNull( documentDAO.getDocumentById(id).getWatermark());
	
	}
	
	//Journal test
	@Test
	public void testSetWatermarkJournal() {
		//book test
		Long id = 3L;
		String type = "journal";
		//system return code 0-ok, 1-server error,  exception intercept logger and write to file or concole
		Integer code = documentDAO.updateDocument(id, type);
		//ok
		assertTrue( 0 == code);			
		assertNotNull( documentDAO.getDocumentById(id).getWatermark());
		
	}

}
