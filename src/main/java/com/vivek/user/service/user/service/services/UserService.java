package com.vivek.user.service.user.service.services;

import java.util.List;

import com.vivek.user.service.user.service.entities.User;

public interface UserService {
	
	User saveUser(User user);
	
	List<User> getAllUser();
	
	User getUser(String userId);
	
	//delete
	//update
	//patch
}
