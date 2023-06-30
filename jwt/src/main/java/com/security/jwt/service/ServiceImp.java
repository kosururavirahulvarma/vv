package com.security.jwt.service;

import java.util.List;

import com.security.jwt.entity.Food;
import com.security.jwt.entity.User;
import com.security.jwt.repository.FoodRepo;
import com.security.jwt.repository.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ServiceImp implements UserService {
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private FoodRepo foodRepo;
	  @Override
	  public void saveUser(User user) {
	    userRepo.save(user);
	  }
	
	@Override
	public Food saveFood(Food food) {
	
		return foodRepo.save(food);
	}
	@Override
	public List<Food> getFood() {
		
		return foodRepo.findAll();
	}
	@Override
	public void deleteFood(Integer id) {
		foodRepo.deleteById(id);
		
	}

}
