package com.springproject.expenseapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.springproject.expenseapi.dto.ExpenseDTO;
import com.springproject.expenseapi.entity.Expenses;
import com.springproject.expenseapi.service.ExpenseService;
import com.springproject.expenseapi.util.ExpenseUtil;

import jakarta.validation.Valid;

@RestController
public class ExpenseController {
	
	@Autowired
	private ExpenseService expenseService;
	
	
	@GetMapping("/expenses")
	public List <Expenses> getAllExpenses(org.springframework.data.domain.Pageable page) {
		System.out.println(expenseService.getAllExpenses());
		return expenseService.getAllExpenses();
	}
	
	@GetMapping("/expenses/{id}")
	public Expenses getExpensesById(@PathVariable("id")long id) {
		return expenseService.getExpenseById(id);
	}
	
	
	@DeleteMapping("/expenses")
	public void deleteExpenseById(@RequestParam("id")long id){
		expenseService.deleteExpenseById(id);
	}
	
	@PostMapping("/expenses")
	public Expenses saveExpensesDetails(@Valid @RequestBody Expenses expenses) {
		return expenseService.saveExpensesDeatails(expenses);
	}
	
	@PostMapping("/ex")
	public void saveExpense(@RequestBody Expenses expenses) {
		System.out.println("Printing the expense details:"+expenses);
	}
	
	@PutMapping("/expenses/{id}")
	public Expenses updatExpensesDetails(@RequestBody Expenses expenses,@PathVariable long id) {
		return expenseService.updateExpensesDeatails(id, expenses);
	}
	
	@GetMapping("/hello")
	public String hello() {
		return "hello motol";
	}
	
}
