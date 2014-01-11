package org.springer.spapp;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springer.model.Document;
import org.springer.service.ServiceWatermark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.support.BindingAwareModelMap;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

 

/**
 * Test mvc document 
 * @author ogarkov_sa
 * @since 08.01.2014
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/root-context.xml","/servlet-context.xml"})
@WebAppConfiguration("classpath:servlet-context.xml")
public class TestDocumentMvc {
	
	 ServiceWatermark serviceWatermark;
	
	 private MockMvc mockMvc;
	
	 @Autowired
	 DocumentsController documentsController;
	 
	 @Before
	 public void setup() { 
		 MockitoAnnotations.initMocks(this);	 	
		 this.mockMvc = MockMvcBuilders.standaloneSetup(documentsController).build();	
		 serviceWatermark = mock(ServiceWatermark.class);
	        
	}
	 
	
	@Test
	public void testGetListDocumentsNotFound() throws Exception {
	 	 mockMvc.perform(get("/list"))
		 	.andExpect(status().is(404));
	}
	
	@Test
	public void testGetListDocumentsIsOk() throws Exception {
		 BindingAwareModelMap model = new BindingAwareModelMap();		
		 List<Document> documents = new ArrayList<Document>();	
	     when(serviceWatermark.findAll()).thenReturn(documents);
	     String page = documentsController.documents(model);
	     assertEquals(DocumentsController.DOCUMENTS_PAGE, page);
	   		 	
	}
	
	
	
	
}
