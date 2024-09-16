package com.login.app.service;

import java.util.List;

import com.login.app.entity.Student;

public interface StudentService {
	 Student save(Student student); // Create or update
	 Student  findById(Long id); // Find a user by ID
	 void deleteById(Long id); // Delete a user
	 List<Student> findAll(); // Retrieve all users

}
