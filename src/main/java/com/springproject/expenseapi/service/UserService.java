package com.springproject.expenseapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springproject.expenseapi.entity.User;
import com.springproject.expenseapi.entity.UserModel;


public interface UserService {

	User createUser(UserModel user);

	User save(User user);
	
	List<User> getUser();
	
	User read(Long id);
	
	User updateUser (User user,Long id);
	
	void DeleteUser(Long id);
	
	User getLoggedInUser();
}
