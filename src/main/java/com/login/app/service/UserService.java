package com.login.app.service;

import com.login.app.controller.dto.UserRegistrationDto;
import com.login.app.entity.User;

public interface UserService {
	User save(UserRegistrationDto registrationDto);
	User findByEmail(String email);

}
