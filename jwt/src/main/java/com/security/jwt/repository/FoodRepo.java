package com.security.jwt.repository;

import com.security.jwt.entity.Food;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface FoodRepo extends JpaRepository<Food, Integer> {

}
