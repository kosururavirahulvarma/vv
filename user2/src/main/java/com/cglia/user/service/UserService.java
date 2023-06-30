
package com.cglia.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cglia.user.model.User;

@Service
public interface UserService {
	public User createUser(User u);

	public User retriveUser(Integer userId);

	public User updateUser(User u, Integer userId);

	public void deleteUser(Integer userId);

	public List<User> getAllUsers();

	public List<User> retriveUserByName(String userName);
}	
