package com.springproject.expenseapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class ExpenseapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpenseapiApplication.class, args);
	}

}
