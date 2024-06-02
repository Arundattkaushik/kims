package com.kims.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kims.entites.User;


public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("SELECT u FROM User u Where u.email=:x AND u.password=:y")
	public User findByEmailAndPassword(@Param("x") String email, @Param("y") String password);
	
	@Query("SELECT u FROM User u Where u.email=:x")
	public User findByEmail(@Param("x") String email);
}
