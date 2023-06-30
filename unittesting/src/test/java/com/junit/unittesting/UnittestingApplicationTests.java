package com.junit.unittesting;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebAppConfiguration
@SpringBootTest
class UnittestingApplicationTests {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMVC;

	@Test
	void contextLoads() {
	}

	@BeforeEach
	private void setup() throws Exception {
		this.mockMVC = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	}

// success save
	@Test
	public void addStudentTest() throws Exception {

		String payload = "{ \"studentId\": 1, \"name\": \"ravi\", \"email\": \"ravi@gmail.com\", \"mobileNumber\": 965, \"address\": \"hyd\", \"gender\": \"male\" }";

		mockMVC.perform(post("/save").contentType(MediaType.APPLICATION_JSON).content(payload)).andDo(print())
				.andExpect(status().isOk()).andReturn();

	}

	// failure
	@Test
	public void addStudentTestFail() throws Exception {

		String payload = "{ \"studentId\": 1, \"name\": \"ravi\", \"email\": \"ravi@gmail.com\", \"mobileNumber\": 965, \"address\": \"hyd\", \"gender\": \"male\" }";

		mockMVC.perform(post("/sve").contentType(MediaType.APPLICATION_JSON).content(payload)).andDo(print())
				.andExpect(status().isOk()).andReturn();

	}
	
	// success

	@Test
	public void getOneStudentTest() throws Exception {
		String payload = "{ \"studentId\": 1, \"name\": \"ravi\", \"email\": \"ravi@gmail.com\", \"mobileNumber\": 965, \"address\": \"hyd\", \"gender\": \"male\" }";

		mockMVC.perform(post("/save").contentType(MediaType.APPLICATION_JSON).content(payload)).andDo(print())
				.andExpect(status().isOk()).andReturn();

		mockMVC.perform(get("/get/{studentId}", 1)).andDo(print()).andExpect(status().isOk()).andReturn();
	}

	//failure 
	@Test
	public void getOneStudentTestFail() throws Exception {
		String payload = "{ \"studentId\": 1, \"name\": \"ravi\", \"email\": \"ravi@gmail.com\", \"mobileNumber\": 965, \"address\": \"hyd\", \"gender\": \"male\" }";

		mockMVC.perform(post("/save").contentType(MediaType.APPLICATION_JSON).content(payload)).andDo(print())
				.andExpect(status().isOk()).andReturn();

		mockMVC.perform(get("/get/{studentId}", 2)).andDo(print()).andExpect(status().isOk()).andReturn();
	}
	//success
	@Test
	public void upadateStudentTest() throws Exception {
		String payload = "{ \"studentId\": 1, \"name\": \"ravi\", \"email\": \"ravi@gmail.com\", \"mobileNumber\": 965, \"address\": \"hyd\", \"gender\": \"male\" }";

		String newpayload = "{  \"name\": \"rahul\", \"email\": \"rahul@gmail.com\", \"mobileNumber\": 7848, \"address\": \"hyd\", \"gender\": \"male\" }";

		mockMVC.perform(post("/save").contentType(MediaType.APPLICATION_JSON).content(payload)).andDo(print())
				.andExpect(status().isOk()).andReturn();

		mockMVC.perform(put("/update/{studentId}", 1).contentType(MediaType.APPLICATION_JSON).content(newpayload))
				.andDo(print()).andExpect(status().isOk()).andReturn();

		mockMVC.perform(get("/get/{studentId}", 1)).andDo(print()).andExpect(status().isOk()).andReturn();
	}
	//failure
		@Test
		public void upadateStudentTestFail() throws Exception {
			String payload = "{ \"studentId\": 1, \"name\": \"ravi\", \"email\": \"ravi@gmail.com\", \"mobileNumber\": 965, \"address\": \"hyd\", \"gender\": \"male\" }";

			String newpayload = "{  \"name\": \"rahul\", \"email\": \"rahul@gmail.com\", \"mobileNumber\": 7848, \"address\": \"hyd\", \"gender\": \"male\" }";

			mockMVC.perform(post("/save").contentType(MediaType.APPLICATION_JSON).content(payload)).andDo(print())
					.andExpect(status().isOk()).andReturn();

			mockMVC.perform(put("/updae/{studentId}", 1).contentType(MediaType.APPLICATION_JSON).content(newpayload))
					.andDo(print()).andExpect(status().isOk()).andReturn();
}

	@Test
	public void deleteStudentTest() throws Exception {
		String payload = "{ \"studentId\": 1, \"name\": \"ravi\", \"email\": \"ravi@gmail.com\", \"mobileNumber\": 965, \"address\": \"hyd\", \"gender\": \"male\" }";

		mockMVC.perform(post("/save").contentType(MediaType.APPLICATION_JSON).content(payload)).andDo(print())
				.andExpect(status().isOk()).andReturn();

		mockMVC.perform(delete("/delete/{studentId}", 1)).andDo(print()).andExpect(status().isOk()).andReturn();
	}

}
