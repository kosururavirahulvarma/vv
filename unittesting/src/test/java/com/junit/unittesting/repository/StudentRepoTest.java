package com.junit.unittesting.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.junit.unittesting.UnittestingApplication;
import com.junit.unittesting.entity.Student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UnittestingApplication.class)
public class StudentRepoTest {

	@Autowired
	private StudentRepo repo;
	Student student;

	@BeforeEach
	void setUp() {
		student = new Student(1002, "ravi", "ravi@gmail.com", 9672, "hyd", "male");
		repo.save(student);
	}

	// success case
	@org.junit.jupiter.api.Test
	void findByStudentId() {
		Student s = repo.findById(1002).get();
		assertThat(s.getName()).isEqualTo(student.getName());
		assertThat(s.getMobileNumber()).isEqualTo(student.getMobileNumber());
	}

	// failure case
	@org.junit.jupiter.api.Test
	void findByStudentIdFail() {
		Student s = null;
		assertThat(s).isNotNull();

	}

	// success case
	@org.junit.jupiter.api.Test
	void findByStudentName() {
		Student s = repo.findByName("ravi");
System.out.println(s.getName());
		assertThat(s.getName()).isEqualTo(student.getName());

	}

	// error case
	@org.junit.jupiter.api.Test
	void findByStudentNameError() {
		Student s = repo.findByName("rahul");

		assertThat(s.getName()).isEqualTo(student.getName());

	}

	// failure case
	@org.junit.jupiter.api.Test
	void findByStudentNameFail() {
		Student s = repo.findByName("ravi");

		assertThat(s.getName()).isEqualTo(student.getEmail());

	}

	@AfterEach
	void tearDown() {
		student = null;
		repo.deleteAll();
	}
}
