package com.katta.Authorization.contoller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.katta.Authorization.AuthorizationApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AuthorizationApplication.class})
@WebAppConfiguration
public class AuthorizationControllerIntegrationTest {
	
	private static final Object VALID = "Valid";
	private MockHttpServletRequestBuilder reqeustBuilder;
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext context;
	
	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void ShouldReturn_200WhenAuthTokenIsValid() throws Exception {
		reqeustBuilder = get("/v1/validateAuth").header("Authorization", VALID);
		MvcResult  mvcResult = mockMvc.perform(reqeustBuilder)
			.andExpect(status().isOk())
			.andExpect(result -> {assertThat(result.getResponse().getContentType()).contains(MediaType.APPLICATION_JSON_VALUE);})
			.andReturn();
		 assertThat(mvcResult.getResponse().getContentAsString()).contains("Valid");
		
	}
	

}
