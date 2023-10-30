package com.springproject.expenseapi.controller;

import java.util.Optional;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springproject.expenseapi.entity.AuthModel;
import com.springproject.expenseapi.entity.User;
import com.springproject.expenseapi.repository.UserRepository;
import com.springproject.expenseapi.security.CustomerUserDetailsService;
import com.springproject.expenseapi.service.UserService;

import jakarta.validation.Valid;

@RestController
@Controller
public class AuthController{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CustomerUserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/local")
	public ResponseEntity<HttpStatus> login(@RequestBody AuthModel authModel) throws Exception{
		
		Authenticate(authModel.getEmail(),authModel.getPassword());
		
		final UserDetails userDetails=userDetailsService.loadUserByUsername(authModel.getEmail());
		System.out.println("login");
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	private void Authenticate(String email, String password) throws Exception {
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		} catch (DisabledException e) {
			throw new Exception("User Disabled");
		}catch (BadCredentialsException e) {
			throw new Exception("Bad Credentials");
		}
	}

	@PostMapping("/load")
	public ResponseEntity<HttpStatus> log(@RequestBody AuthModel authModel) {
	
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authModel.getEmail(), authModel.getPassword()));
		System.out.println(authentication);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	
//	@PostMapping("/register")
//	public ResponseEntity<User > create( @Valid @RequestBody User user){
//	      //System.out.println("User MOdel"+user);
//		return new ResponseEntity<>(userService.save(user),HttpStatus.CREATED);
//	}
//	

	
}
