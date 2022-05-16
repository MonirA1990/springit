package com.vega.springit.service;

import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vega.springit.domain.User;
import com.vega.springit.repository.RoleRepository;
import com.vega.springit.repository.UserRepository;

@Service
public class UserService {
	
	private final Logger logger = LoggerFactory.getLogger(UserService.class);
	private final  UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final BCryptPasswordEncoder encoder;
	private final MailService mailService;
	
	@Autowired
	public UserService(UserRepository userRepository, RoleRepository roleRepository, MailService mailService) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.mailService = mailService;
		encoder = new BCryptPasswordEncoder();
	}
	
	public User register(User user) {
		String secret = "{bcrypt}" + encoder.encode(user.getPassword());
		user.setPassword(secret);
		user.setConfirmPassword(secret);
		user.addRole(roleRepository.findByName("ROLE_USER"));
		user.setActivationCode(UUID.randomUUID().toString());
		save(user);
		
		sendActivationEmail(user);
		return user;
	}
	
	public User save(User user) {
		return userRepository.save(user);
	}
	
	public Optional<User> findByEmailAndActivationCode(String email, String activationCode){
		return userRepository.findByEmailAndActivationCode(email, activationCode);
	}
	
	public void sendActivationEmail(User user) {
        mailService.sendActivationEmail(user);
    }

    public void sendWelcomeEmail(User user) {
        mailService.sendWelcomeEmail(user);
    }
}
