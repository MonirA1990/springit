package com.vega.springit.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vega.springit.domain.User;
import com.vega.springit.repository.RoleRepository;
import com.vega.springit.repository.UserRepository;

@Service
public class UserService {
	
	private final Logger logger = LoggerFactory.getLogger(UserService.class);
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	
	@Autowired
	public UserService(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}
	
	public User register(User user) {
		return user;
	}
	
	public User save(User user) {
		return userRepository.save(user);
	}

}
