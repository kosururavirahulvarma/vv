
package com.cglia.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cglia.user.model.User;
import com.cglia.user.service.UserService;

@RestController
@CrossOrigin("*")
@RequestMapping("/com/cglia/user")
public class UserController {
	@Autowired
	private UserService service;

	@PostMapping("/save")
	public void addUser(@RequestBody User u) {
		service.createUser(u);

	}

	@GetMapping("/get/{userId}")
	public User getOneUser(@PathVariable Integer userId) {
	
		return service.retriveUser(userId);

	}

	@PutMapping("/update/{userId}")
	public void updateUser(@RequestBody User u, @PathVariable Integer userId) {
		service.updateUser(u, userId);
		

	}

	@GetMapping("/getall")
	public List<User> getAllUsers() {
		return service.getAllUsers();

	}

	@DeleteMapping("/delete/{userId}")
	public void deleteUser(@PathVariable Integer userId) {
		
		service.deleteUser(userId);
			
	}
	@GetMapping("/getbyname/{userName}")
	public List<User> getOneUserByName(@PathVariable String userName) {
	
		return service.retriveUserByName(userName);

	}

}
