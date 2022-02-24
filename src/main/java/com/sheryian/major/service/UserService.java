package com.sheryian.major.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.sheryian.major.model.User;
import com.sheryian.major.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	
	public List<User> getAllUser(){return  userRepository.findAll();}

	public void removeUserById(int id) {userRepository.deleteById(id);}


	
	
	
}
