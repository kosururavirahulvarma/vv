package com.junit.unittesting.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.junit.unittesting.UnittestingApplication;
import com.junit.unittesting.entity.Student;
import com.junit.unittesting.repository.StudentRepo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UnittestingApplication.class)
class StudentServiceImplementaionTest {

	@Mock
	private StudentRepo repo;
	Student student;
	Student studentNew;
	@Autowired
	private StudentService service;
	AutoCloseable autoCloseable;

	@BeforeEach
	void setUp() throws Exception {
		autoCloseable = MockitoAnnotations.openMocks(this);
		service = new StudentServiceImplementaion(repo);
		student = new Student(1003, "ramu", "ramu@gmail.com", 9674542, "hyd", "male");

	}

	@AfterEach
	void tearDown() throws Exception {
		autoCloseable.close();
	}

	// success case
	@Test
	void testCreateStudent() {
		mock(Student.class);
		mock(StudentRepo.class);

		when(repo.save(student)).thenReturn(student);
		assertThat(service.createStudent(student)).isEqualTo(student);

	}

	// failure case
	@Test
	void testCreateStudentFail() {
		mock(Student.class);
		mock(StudentRepo.class);

		when(repo.save(studentNew)).thenReturn(studentNew);
		assertThat(service.createStudent(studentNew)).isEqualTo(student);

	}

	// success case
	@Test
	void testRetriveStudent() {
		mock(Student.class);
		mock(StudentRepo.class);
		when(repo.findById(1003)).thenReturn(Optional.ofNullable(student));
		assertThat(service.retriveStudent(1003).getAddress()).isEqualTo(student.getAddress());
	}

	// error case
	@Test
	void testRetriveStudentError() {
		mock(Student.class);
		mock(StudentRepo.class);
		when(repo.findById(1004)).thenReturn(Optional.ofNullable(student));
		assertThat(service.retriveStudent(1003).getAddress()).isEqualTo(student.getAddress());
	}

	// failure case
	@Test
	void testRetriveStudentFail() {
		mock(Student.class);
		mock(StudentRepo.class);
		when(repo.findById(1003)).thenReturn(Optional.ofNullable(student));
		assertThat(service.retriveStudent(1003).getAddress()).isEqualTo(student.getName());
	}

	// success case
	@Test
	void testUpdateStudent() {
		mock(Student.class);
		mock(StudentRepo.class);
		when(repo.findById(1)).thenReturn(Optional.of(student));
		when(repo.save(student)).thenReturn(student);
		assertThat(service.updateStudent(student, 1)).isEqualTo(student);
	}

	// Error case
	@Test
	void testUpdateStudentError() {
		mock(Student.class);
		mock(StudentRepo.class);
		when(repo.findById(2)).thenReturn(Optional.of(student));
		when(repo.save(student)).thenReturn(student);
		assertThat(service.updateStudent(student, 1)).isEqualTo(student);
	}

	// failure case
	@Test
	void testUpdateStudentFail() {
		mock(Student.class);
		mock(StudentRepo.class);
		when(repo.findById(1)).thenReturn(Optional.of(student));
		when(repo.save(student)).thenReturn(student);
		assertThat(service.updateStudent(student, 1)).isEqualTo(studentNew);
	}

	// success case
	@Test
	void testDeleteStudent() {
		mock(Student.class);
		mock(StudentRepo.class, Mockito.CALLS_REAL_METHODS);
		doAnswer(Answers.CALLS_REAL_METHODS).when(repo).deleteById(any());
		assertThat(service.deleteStudent(1)).isEqualTo("success");
	}

	
}
