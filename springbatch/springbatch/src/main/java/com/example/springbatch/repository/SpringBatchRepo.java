package com.example.springbatch.repository;

import com.example.springbatch.entity.Books;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface SpringBatchRepo extends JpaRepository<Books, Integer> {

}