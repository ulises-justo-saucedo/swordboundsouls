package com.SwordboundSouls.service;
import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import com.SwordboundSouls.entity.Users;
import com.SwordboundSouls.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository uRepo;
	
	public boolean verifyIfUserExists(String username,String pass) {
		return uRepo.verifyIfUserExists(username, pass) == 1;
	}
	
	public void registerNewUser(Users user) {
		uRepo.save(user);
	}
	
	public Users getUser(String username) {
		return uRepo.getUser(username);
	}
	
	public List<Users> getAllUsers(){
		return uRepo.findAll();
	}
}
