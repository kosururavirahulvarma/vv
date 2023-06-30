package com.junit.unittesting.controller;

import java.util.List;

import com.junit.unittesting.entity.Student;
import com.junit.unittesting.repository.StudentRepo;
import com.junit.unittesting.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
	@Autowired
	private StudentService service;
	@Autowired
	private StudentRepo repo;
	@PostMapping("/save")
	public String addStudent(@RequestBody Student student) {
		Student newStudent = service.createStudent(student);
		String msg = null;
		if (newStudent != null) {
			msg = "new student created successfully";
		} else {
			msg = "student creation failed";
		}

		return msg;

	}
	@GetMapping("/get/{studentId}")
	public Student getOneStudent(@PathVariable Integer studentId) {
	
		return service.retriveStudent(studentId);

	}

	@PutMapping("/update/{studentId}")
	public String updateStudent(@RequestBody Student student, @PathVariable Integer studentId) {
		Student newStudent = service.updateStudent(student, studentId);
		String msg = null;
		if (newStudent != null) {
			msg = "Student details modified successfully";
		} else {
			msg = "Student modification failed";
		}

		return msg;

	}


	@DeleteMapping("/delete/{studentId}")
	public String deleteStudent(@PathVariable Integer studentId) {
	 
		return service.deleteStudent(studentId);
	}
}
