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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
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
		ResultActions rs = this.mvc.perform(post("/Notlage")
		.param("header", "hhhh")
        .param("payload", "pppp")
        .param("dvpid", "123"));
		
		LOGGER.info(rs.toString());
	}

	

}
