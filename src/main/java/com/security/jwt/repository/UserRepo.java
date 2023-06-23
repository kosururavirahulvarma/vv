package com.security.jwt.repository;

import com.security.jwt.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepo extends JpaRepository<User, String> {
	public User findByUserName(String userName);
}
