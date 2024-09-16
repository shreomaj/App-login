package com.login.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.login.app.Repository.StudentRepository;
import com.login.app.entity.Student;
@Service
public class StudentServiceImpl implements StudentService {

	private StudentRepository stdRepo;
	
	public StudentServiceImpl(StudentRepository stdRepo) {
		super();
		this.stdRepo = stdRepo;
	}

	@Override
	public Student save(Student student) {
		// TODO Auto-generated method stub
		return stdRepo.save(student);
	}

	@Override
	public Student findById(Long id) {
		// TODO Auto-generated method stub
		return stdRepo.findById(id).orElse(null);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		stdRepo.deleteById(id);;
	}

	@Override
	public List<Student> findAll() {
		// TODO Auto-generated method stub
		return stdRepo.findAll();
	}

}
