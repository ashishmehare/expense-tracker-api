package com.springproject.expenseapi.config;


import java.util.Collections;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.authentication.AuthenticationManagerFactoryBean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.springproject.expenseapi.security.CustomerUserDetailsService;

@Configuration
public class WebSecuirtyConfig  {
	

	@Autowired
	private CustomerUserDetailsService userDetailsService;

	    @SuppressWarnings("removal")
		@Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        http
	           .csrf().disable()
	           .authorizeHttpRequests()
	            .requestMatchers("/local","/register").permitAll()
	             .anyRequest().authenticated()
	             .and()
	               .httpBasic();
	        return http.build();
	    }

//	 @Bean
//	 public InMemoryUserDetailsManager createUserDetailsManager() {
//		 UserDetails userDetails=createNewUser("Ashish","Pass");
//		 UserDetails userDetails2=createNewUser("Mehare", "pass");
//		 return new InMemoryUserDetailsManager(userDetails,userDetails2);
//	 }
//
//	private UserDetails createNewUser(String username, String password) {
//		  Function<String, String> passwordEncoder
//	  		= input -> passwordEncoder().encode(input);
//
//	  		UserDetails userDetails = User.builder()
//							            .passwordEncoder(passwordEncoder)
//						            	.username(username)
//						          	    .password(password)
//							            .roles("USER","ADMIN")
//							            .build();
//	          return userDetails;
//	}

		@Bean
		public DaoAuthenticationProvider authenticationProvider() {
			DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
			
			authProvider.setUserDetailsService(userDetailsService);
			authProvider.setPasswordEncoder(passwordEncoder());
			
			return authProvider;
		}
		
		
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}
 
}
