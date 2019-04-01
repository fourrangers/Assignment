package com.test.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@WebMvcTest(SampleController.class)
public class SampleControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testGetNthFibonnaci() throws Exception {
		mvc.perform(get("/assignment/api/v1/fibonacci/{number}", 10)).andExpect(status().isOk());
	}

	@Test
	public void testReverseWords() throws Exception {
		mvc.perform(get("/assignment/api/v1/ReverseWords/{sentence}", "Hello World")).andExpect(status().isOk());
	}

	@Test
	public void testTriangleType() throws Exception {
		mvc.perform(get("/assignment/api/v1/TriangleType/{lengthA}/{lengthB}/{lengthC}", 1, 1, 1))
				.andExpect(status().isOk());
	}

	@Test
	public void testMakeOneArray() throws Exception {
		mvc.perform(get("/assignment/api/v1/makeOneArray")).andExpect(status().isOk());
	}

}
