package faeteam3.Notlage.app;

import static org.hamcrest.Matchers.containsString;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import faeteam3.Notlage.controller.NotlageController;

@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureMockMvc
//@EnableWebMvc 

public class TestIntegration 

{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TestIntegration.class);

	@Autowired
	private MockMvc mockMvc;
	

	

//	@MockBean
//	private NotlageController NotlageController;

	@Test
    public void mock_all_test() throws Exception
    {
		String json_data = "{ \"payload\":\"qqw\", \"dvpid\":\"44\" }";
		
//		RequestBuilder requestBuilder = MockMvcRequestBuilders
//		.post("/notlage")
//		.content(json_data)
//		.contentType(MediaType.APPLICATION_JSON)
//		.accept(
//				MediaType.APPLICATION_JSON);


		
			mockMvc.perform(post("/notlage")
			        .content(json_data)
			        .contentType(MediaType.APPLICATION_JSON))
					.andDo(print())
			        .andExpect(status().isOk())
			        .andExpect(MockMvcResultMatchers.model().attribute("dvpID", "44"))
//			        .andExpect(content().string(containsString("44")))
			        .andDo(mvcResult -> {
			            //Verrify Response here
			        });

    }

}
