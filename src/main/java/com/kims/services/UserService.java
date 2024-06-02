package com.kims.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kims.entites.User;
import com.kims.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository uRepository;
	
	public User login(String email, String password) {
		return uRepository.findByEmailAndPassword(email, password);
	}
	
	
	public User createNewUser(User user) {
		return uRepository.save(user);
	}
	
	public User findByEmail(String email) {
		return uRepository.findByEmail(email);
	}

}
