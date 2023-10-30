package com.springproject.expenseapi.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.springproject.expenseapi.entity.User;
import com.springproject.expenseapi.entity.UserModel;
import com.springproject.expenseapi.exception.ItemAlreadyExistsException;
import com.springproject.expenseapi.repository.UserRepository;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

@Service
public class UserServiceImp implements UserService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImp.class);


	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User createUser(UserModel uModel) {
		
		User user=new User();
		System.out.println("User Umodel"+uModel);
		BeanUtils.copyProperties(uModel, user);
		System.out.println("User info"+user);
		return userRepository.save(user);
	}
	
	public User save(User user) {
		
		if(userRepository.existsByEmail(user.getEmail())) {
			throw new ItemAlreadyExistsException("Email already exist");
		}
		LOGGER.info("Saving the Product for productId: ["+user.getId()+"]");
		return userRepository.save(user);	
	}
	
	public List<User> getUser() {
		return userRepository.findAll();
	}
	
	
	public User read(Long id)  {
		Optional<User> userOptional=userRepository.findById(id);
	
		return userOptional.get();
		
	}
	
	public User updateUser(User user,Long id) {
		User existingUser=read(id);
		existingUser.setName(user.getName()!=null ? user.getName():existingUser.getName());
		existingUser.setEmail(user.getEmail()!=null?user.getEmail():existingUser.getEmail());
		existingUser.setPassword(user.getPassword()!=null? user.getPassword():existingUser.getPassword());
		existingUser.setAge(user.getAge());
		return userRepository.save(existingUser);
	}

	@Override
	public void DeleteUser(Long id) {
		userRepository.deleteById(id);	
	}

	@Override
	public User getLoggedInUser() {
	
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			
			String email = authentication.getName();
			
			return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found for the email"+email));
	
	}
}
