package com.junit.unittesting.repository;


import com.junit.unittesting.entity.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {
	public Student findByName(String name);
}
