package com.springsecurity.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService{
	@Autowired private UserRepository userRepo;
	
	@Transactional
	public User save(User user){
		
		return userRepo.save(user);
	}

	@Override
	public User findOne(String id) {
		return userRepo.findOne(id);
	}

	@Override
	public User findByUsername(String username) {
		return userRepo.findByUsername(username);
	}
}
