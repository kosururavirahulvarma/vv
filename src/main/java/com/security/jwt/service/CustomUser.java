package com.security.jwt.service;

import java.util.ArrayList;

import com.security.jwt.entity.AuthRequest;
import com.security.jwt.repository.AuthRequestRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUser implements UserDetailsService {
	@Autowired
	private AuthRequestRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		AuthRequest user = userRepo.findByUserName(name);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid id and password");
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				new ArrayList<>());
	}
}
