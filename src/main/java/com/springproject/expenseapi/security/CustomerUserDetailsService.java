package com.springproject.expenseapi.security;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.springproject.expenseapi.entity.User;
import com.springproject.expenseapi.repository.UserRepository;

@Service
public class CustomerUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
//	public UserDetails loadbyUserName(String email) throws UsernameNotFoundException {
//		
//		User existingUser=userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User not found for email"+email));
//		
//		return new org.springframework.security.core.userdetails.User(existingUser.getEmail(), existingUser.getPassword(), new ArrayList<>());
//	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
	User existingUser=userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User not found for email"+email));
		
		return new org.springframework.security.core.userdetails.User(existingUser.getEmail(), existingUser.getPassword(), new ArrayList<>());
	
	}
	
}
