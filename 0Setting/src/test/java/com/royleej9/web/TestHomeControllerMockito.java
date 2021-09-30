package com.royleej9.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.royleej9.web.HomeController;

@RunWith(MockitoJUnitRunner.class)
public class TestHomeControllerMockito {
	
	@InjectMocks
	private HomeController homeController;

	private MockMvc mockMvc;

	@Before
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
	}

	@Test
	public void tsetController() throws Exception {
		this.mockMvc.perform(get("/"))
					.andExpect(status().isOk()).andDo(print());
	}
}
