package com.example.spring_security_6.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_security_6.entity.Users;
import com.example.spring_security_6.repository.UserRepository;
import com.example.spring_security_6.service.UserService;

@RestController
public class UserController {
	
	private final UserRepository userRepository;
	
	private final UserService userService;
	
	public UserController(UserRepository userRepository, UserService userService) {
		super();
		this.userRepository = userRepository;
		this.userService = userService;
	}

//	public UserController(UserRepository userRepository) {
//		this.userRepository = userRepository;
//	}
	
	@PostMapping("/register")
	public Users register(@RequestBody Users user) {
//		return userRepository.save(user);
		return userService.register(user);
	}
	
	@PostMapping("/login")
	public String login(@RequestBody Users user) {
		return userService.verify(user);

	}
}
