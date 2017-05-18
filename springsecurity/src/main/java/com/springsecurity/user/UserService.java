package com.springsecurity.user;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
	public User save(User user);
	
	public User findOne(String id);
	
	public User findByUsername(String username);
}
