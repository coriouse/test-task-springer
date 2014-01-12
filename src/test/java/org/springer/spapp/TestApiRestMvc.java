package org.springer.spapp;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springer.service.ServiceWatermark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


/**
 * Test rest api mvc 
 * @author ogarkov_sa
 * @since 11.01.2014
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/root-context.xml","/servlet-context.xml"})
@WebAppConfiguration("classpath:servlet-context.xml")
public class TestApiRestMvc {
	
	 ServiceWatermark serviceWatermark;
		
	 private MockMvc mockMvc;
	
	 @Autowired
	 ApiController apiController;
	 
	 @Before
	 public void setup() { 
		 MockitoAnnotations.initMocks(this);	 	
		 this.mockMvc = MockMvcBuilders.standaloneSetup(apiController).build();	
		 serviceWatermark = mock(ServiceWatermark.class);
	}
	
	
	@Test
	public void testGetStatus() {
		Long id = 1L;
		Integer codeClean = apiController.cleanWatermark(id);
		assertTrue(0 == codeClean);
		String testRresult = "{\"topic\":\"Science\",\"content\":\"book\",\"title\":\"The Dark Code\",\"author\":\"Bruce Wayne\"}";
		//without watermark
		String statusNull = apiController.getStatus(id);
		System.out.println("statusNull="+statusNull);
		assertNull(statusNull);		
		Integer code = apiController.setWatermarkById(id, "book");		
		assertTrue(0 == code);
		//with watermark
		String statusIsNotNull = apiController.getStatus(id);		
		assertEquals(testRresult, statusIsNotNull);		
	}
	
	@Test
	public void testSetWatermark() {
		Long id = 1L;
		Long idWrong = 10L;
		Integer codeRight = apiController.setWatermarkById(id, "book");		
		assertTrue(0 == codeRight);		
		Integer codeWrong = apiController.setWatermarkById(idWrong, "book");	
		assertTrue(1 == codeWrong);		
		//id is null
		Integer codeIdIsNull = apiController.setWatermarkById(null, "book");	
		assertTrue(1 == codeIdIsNull);		
		//type is null		
		Integer codeTypeIsNull = apiController.setWatermarkById(id, null);
		assertTrue(1 == codeTypeIsNull);	
		//type is wrong
		Integer codeTypeIsWrong = apiController.setWatermarkById(id, "books");
		assertTrue(1 == codeTypeIsWrong);	
	}
	

}
