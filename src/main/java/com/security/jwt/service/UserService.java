package com.security.jwt.service;

import java.util.List;

import com.security.jwt.entity.Food;
import com.security.jwt.entity.User;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
	public void saveUser(User user);

	public Food saveFood(Food food);
	public List<Food> getFood();
	public void deleteFood(Integer id);
}
