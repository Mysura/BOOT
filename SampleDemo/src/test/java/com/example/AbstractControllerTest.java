package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@WebAppConfiguration
public abstract class AbstractControllerTest extends AbstractTest {
	
	public MockMvc mockMvc;
	@Autowired
	public WebApplicationContext webApplicationContext;
	public void setUp(){
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
	}

}
