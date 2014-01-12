package org.springer.spapp;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springer.dao.impl.DocumentDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@ContextConfiguration(locations = {"/root-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestBase {
	
	@Autowired
	DocumentDAOImpl documentDAO;
	
	@Test
	public void testBase() {
		assertTrue(true);
	}

}
