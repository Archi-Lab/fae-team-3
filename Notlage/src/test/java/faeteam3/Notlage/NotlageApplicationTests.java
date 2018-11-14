package faeteam3.Notlage;

import org.junit.Assert;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest

@AutoConfigureMockMvc

@ComponentScan(basePackages = {"faeteam3.Notlage"} )
public class NotlageApplicationTests {
	
	@Autowired
	private MockMvc mvc;
	

	private static final Logger LOGGER = LoggerFactory.getLogger(NotlageApplicationTests.class);


	@Test
	public void contextLoads() {
	}

	
	@Test
	public void exampleTest() throws Exception {
		LOGGER.info("Test ---");

		
		String json_data = "{ \"header\": \"abc\" , \"payload\":\"qqw\", \"dvpid\":\"123\" }";
		
		// Send course as body to /students/Student1/courses
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/Notlage")
				.content(json_data)
				.contentType(MediaType.APPLICATION_JSON);

		this.mvc.perform(requestBuilder).andDo(MockMvcResultHandlers.print());
		
//		LOGGER.info(rs.toString());
	}
	
	@Test
	public void exampleTest2() throws Exception
	{
		LOGGER.info("Test ---");
	    String json_data = "{ \"header\": \"abc\" , \"payload\":\"qqw\", \"dvpid\":\"44\" }";
		
		// Send course as body to /students/Student1/courses
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/Notlage")
				.content(json_data)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = this.mvc.perform(requestBuilder).andReturn();
		

		MockHttpServletResponse response = result.getResponse();
		LOGGER.info(response.getContentAsString());
		
		requestBuilder = MockMvcRequestBuilders
				.delete("/Notlage/1")
				.contentType(MediaType.APPLICATION_JSON);

		result = this.mvc.perform(requestBuilder).andReturn();

				
	     
	}
	

	
	

	
	

	

}
