package com.security.jwt.repository;

import com.security.jwt.entity.AuthRequest;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AuthRequestRepo extends JpaRepository<AuthRequest, String>{
	public AuthRequest findByUserName(String userName);
}
