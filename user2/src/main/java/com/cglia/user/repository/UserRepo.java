
package com.cglia.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cglia.user.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
	public List<User> findByUserName(String userName);
}
