package com.SwordboundSouls.service;
import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import com.SwordboundSouls.entity.User;
import com.SwordboundSouls.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository uRepo;

	public void registerNewUser(User user) {
		uRepo.save(user);
	}
	
	public User getUser(String username) {
		return uRepo.findByUsername(username).orElse(null);
	}
	
	public List<User> getAllUsers(){
		return uRepo.findAll();
	}
}
