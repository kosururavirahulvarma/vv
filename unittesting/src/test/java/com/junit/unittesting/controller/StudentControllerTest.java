package com.junit.unittesting.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.junit.unittesting.entity.Student;
import com.junit.unittesting.repository.StudentRepo;
import com.junit.unittesting.service.StudentService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;
	@MockBean
	@Autowired
	private StudentService studentService;
	Student studentOne;
	Student studentTwo;
	MockMvcRequestBuilders requestBuilder;
	MockMvcResultHandlers handler;
	MockMvcResultMatchers result;
	List<Student> students = new ArrayList<>();
	@MockBean
	@Autowired
	private StudentRepo repo;

	@BeforeEach
	void setUp() throws Exception {
		studentOne = new Student(1002, "ravi", "ravi@gmail.com", 9672, "hyd", "male");
		studentTwo = new Student(1003, "ramu", "ramu@gmail.com", 9674542, "hyd", "male");
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();

		repo.save(studentTwo);

	}

	@Test
	void testAddStudent() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(studentOne);

		
		this.mockMvc.perform(requestBuilder.post("/save").contentType(MediaType.APPLICATION_JSON).content(requestJson))
				.andDo(handler.print()).andExpect(result.status().isOk());

	}

//Success
	@Test
	void testGetOneStudent() throws Exception {
		when(studentService.retriveStudent(1002)).thenReturn(studentOne);
		this.mockMvc.perform(requestBuilder.get("/get/1002")).andDo(handler.print()).andExpect(result.status().isOk());
	}

	// failure
	@Test
	void testGetOneStudentFail() throws Exception {
		when(studentService.retriveStudent(1)).thenReturn(studentOne);
		this.mockMvc.perform(requestBuilder.get("/get")).andDo(handler.print()).andExpect(result.status().isOk());
	}

	@Test
	void testUpdateStudent() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(studentOne);

		when(studentService.updateStudent(studentOne, 1003)).thenReturn(studentTwo);
		this.mockMvc
				.perform(
						requestBuilder.put("/update/1003").contentType(MediaType.APPLICATION_JSON).content(requestJson))
				.andDo(handler.print()).andExpect(result.status().isOk());

	}

	// delete success
	@Test
	void testDeleteStudent() throws Exception {
		when(studentService.deleteStudent(1)).thenReturn("success");
		this.mockMvc.perform(requestBuilder.delete("/delete/1")).andDo(handler.print())
				.andExpect(result.status().isOk());

	}

	@AfterEach
	void tearDown() {
		studentOne = null;
		studentTwo = null;
		repo.deleteAll();
	}
}
