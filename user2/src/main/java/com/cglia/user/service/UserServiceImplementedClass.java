
package com.cglia.user.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cglia.user.model.User;
import com.cglia.user.repository.UserRepo;

@Service
public class UserServiceImplementedClass implements UserService {
	@Autowired
	private UserRepo repo;

	@Override
	public User createUser(User u) {
		return repo.save(u);
	}

	@Override
	public User retriveUser(Integer userId) {
		User user = repo.findById(userId).get();
	
			return user;
		} 

	@Override
	public User updateUser(User u, Integer userId) {
		User oldUser = repo.findById(userId).get();
		
			if (u.getUserName() != null) {
				oldUser.setUserName(u.getUserName());
			}
			if (u.getMobileNumber() != null) {
				oldUser.setMobileNumber(u.getMobileNumber());
			}
			if (u.getEmail() != null) {
				oldUser.setEmail(u.getEmail());
			}
		
			if (u.getAddress() != null) {
				oldUser.setAddress(u.getAddress());
			}
			
			return repo.save(oldUser);
		}
	

	@Override
	public void deleteUser(Integer userId) {
		repo.deleteById(userId);
	}
	

	@Override
	public List<User> getAllUsers() {
		return  repo.findAll();
	}

	@Override
	public List<User> retriveUserByName(String userName) {
			return repo.findByUserName(userName);
		}
	}


