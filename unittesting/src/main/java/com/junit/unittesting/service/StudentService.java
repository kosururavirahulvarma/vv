package com.junit.unittesting.service;


import com.junit.unittesting.entity.Student;

import org.springframework.stereotype.Service;

@Service
public interface StudentService {

	public Student createStudent(Student student);
	public Student retriveStudent(Integer studentId);

	public Student updateStudent(Student student, Integer studentId);

	public String deleteStudent(Integer studentId);
}
