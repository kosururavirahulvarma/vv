
package com.junit.unittesting.service;



import com.junit.unittesting.entity.Student;
import com.junit.unittesting.repository.StudentRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class StudentServiceImplementaion implements StudentService {
	@Autowired
	private StudentRepo repo;
	
	

	public StudentServiceImplementaion(StudentRepo repo) {
		this.repo = repo;
	}
	@Override
	public Student createStudent(Student student) {
	
		return repo.save(student);
	}
	@Override
	public Student retriveStudent(Integer studentId) {
	
		return repo.findById(studentId).get();
	}
	@Override
	public Student updateStudent(Student student, Integer studentId) {
		Student oldStudent = repo.findById(studentId).get();
		
			if (student.getName() != null) {
				oldStudent.setName(student.getName());
			}
			if (student.getMobileNumber() != null) {
				oldStudent.setMobileNumber(student.getMobileNumber());
			}
			if (student.getEmail() != null) {
				oldStudent.setEmail(student.getEmail());
			}
			if (student.getGender() != null) {
				oldStudent.setGender(student.getGender());
			}
			if (student.getAddress() != null) {
				oldStudent.setAddress(student.getAddress());
			}
		return repo.save(oldStudent);
	}
	@Override
	public String deleteStudent(Integer studentId) {

		 repo.deleteById(studentId);
		return "success";
	}

}
