
package com.vivek.user.service.user.service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vivek.user.service.user.service.entities.User;
import com.vivek.user.service.user.service.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user){
		var user1 = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user1);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<User> getUser(@PathVariable String userId){
		var user = userService.getUser(userId);
		return ResponseEntity.ok(user);
		
	}
	
	@GetMapping
	public ResponseEntity<List<User>> getAllUser(){
		List<User> users = userService.getAllUser();
		return ResponseEntity.ok(users);
	}
	
	
}
