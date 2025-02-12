package com.example.spring_security_6.service;

import java.util.Objects;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.spring_security_6.CustomUserDetails;
import com.example.spring_security_6.entity.Users;
import com.example.spring_security_6.repository.UserRepository;

@Component
public class CustomerUserDetailsService implements UserDetailsService{
	
	private final UserRepository userRepository;
	
	public CustomerUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Users user = userRepository.findByUsername(username);
		
		if(Objects.isNull(user)) {
			System.out.println("User not found");
			throw new UsernameNotFoundException("USer not found with this username");
		}
		return new CustomUserDetails(user);
	}

}
