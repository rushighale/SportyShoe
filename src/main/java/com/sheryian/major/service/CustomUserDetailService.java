package com.sheryian.major.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sheryian.major.model.CustomUserDetail;
import com.sheryian.major.model.User;
import com.sheryian.major.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> user=userRepository.findUserByEmail(email);
		user.orElseThrow(() -> new UsernameNotFoundException("USER is Not Found"));
		
		return user.map(CustomUserDetail::new).get();
	}

	
	
}


// userrepostory se fetch kro aur return kro