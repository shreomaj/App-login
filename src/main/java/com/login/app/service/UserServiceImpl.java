package com.login.app.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.login.app.Repository.UserRepository;
import com.login.app.controller.dto.UserRegistrationDto;
import com.login.app.entity.Role;
import com.login.app.entity.User;

@Service
public class UserServiceImpl implements UserService{
	private UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public User save(UserRegistrationDto registrationDto) {
		User user=new User(registrationDto.getFirstName(),
				registrationDto.getLastName(),
				registrationDto.getEmail(),
				registrationDto.getPassword(),Arrays.asList(new Role("ROLE_USER")));
		return userRepository.save(user);
	}

	@Override
	public User findByEmail(String email) {
		return  userRepository.findByEmail(email);
	}
	
	

}
