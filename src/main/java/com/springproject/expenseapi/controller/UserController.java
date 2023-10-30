package com.springproject.expenseapi.controller;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springproject.expenseapi.service.UserServiceImp;
import com.springproject.expenseapi.dto.UserDTO;
import com.springproject.expenseapi.entity.User;
import com.springproject.expenseapi.entity.UserModel;
import com.springproject.expenseapi.exception.ItemAlreadyExistsException;
import com.springproject.expenseapi.repository.UserRepository;
import com.springproject.expenseapi.service.UserService;
import com.springproject.expenseapi.util.UserUtil;

import jakarta.validation.Valid;

@RestController
public class UserController  {
	

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder bcryEncoder;
	
	@PostMapping("/register")
	public ResponseEntity<User > create( @Valid @RequestBody User user){
	      //System.out.println("User MOdel"+user);
		if(userRepository.existsByEmail(user.getEmail())) {
			throw new ItemAlreadyExistsException("User is already register with email "+ user.getEmail());
		}
		user.setPassword(bcryEncoder.encode(user.getPassword()));
		return new ResponseEntity<>(userService.save(user),HttpStatus.CREATED);
	}
	
	@GetMapping("/getusers")
	public List<User> getAllUsers(){
		return userService.getUser();
	}
	
	@GetMapping("/getuser/{id}")
	public User getuserById(@PathVariable("id")Long id) {
		return userService.read(id);
	}
	
	@GetMapping("/getUser/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id")Long id){
		return new ResponseEntity<User>(userService.read(id),HttpStatus.OK);
	}
	
	
	@PostMapping("/user")
	public ResponseEntity<UserDTO> userCreate(@RequestBody UserDTO userDTO) {
		try {
			User user = UserUtil.convert2Entity(userDTO);
			user = userService.save(user);
			userDTO = UserUtil.convert2DTO(user);

			return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
		} catch (Exception e) {
			//LOGGER.error("Exception occurred: "+e.getMessage(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PutMapping("/users/{id}")
	public ResponseEntity<User> update(@RequestBody User user,@PathVariable Long id){
		User mUser=userService.updateUser(user, id);
		return new ResponseEntity<User>(mUser,HttpStatus.OK);
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<User> delete(@PathVariable Long id){
		userService.DeleteUser(id);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}
}
