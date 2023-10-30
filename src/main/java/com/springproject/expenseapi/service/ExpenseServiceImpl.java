package com.springproject.expenseapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springproject.expenseapi.entity.Expenses;
import com.springproject.expenseapi.exception.ExpenseNotFoundException;
import com.springproject.expenseapi.repository.ExpenseRepository;

@Service
public class ExpenseServiceImpl implements ExpenseService {

	@Autowired
	private ExpenseRepository expenseRepository;
	
	
    @Autowired
	private UserService userService;
    
	@Override
	public List<Expenses> getAllExpenses() {
	 
 		return expenseRepository.findAll();
	}

	public Expenses getExpenseById(long id)  throws ExpenseNotFoundException{
		Optional<Expenses> expense=expenseRepository.findById(id);
		if(expense.isPresent()) {
			return expense.get();
		}
		throw new ExpenseNotFoundException("Expense is not found for id "+id);
	}
	
	
	public void deleteExpenseById(long id) {
		expenseRepository.deleteById(id);
	}
	

	public Expenses saveExpensesDeatails(Expenses expenses) {
	 expenses.setUser(userService.getLoggedInUser());
		return expenseRepository.save(expenses);
	}

	
	@Override
	public Expenses updateExpensesDeatails(long id, Expenses expenses) {
		Expenses existingExpenses=getExpenseById(id);
		existingExpenses.setName(expenses.getName()!=null ? expenses.getName():existingExpenses.getName());
		existingExpenses.setDescription(expenses.getDescription()!=null ? expenses.getDescription():existingExpenses.getDescription());
		existingExpenses.setCategory(expenses.getCategory()!=null? expenses.getCategory():existingExpenses.getCategory());
		existingExpenses.setAmount(expenses.getAmount()!=null? expenses.getAmount():existingExpenses.getAmount());
		existingExpenses.setDate(expenses.getDate()!=null? expenses.getDate():existingExpenses.getDate());
		return expenseRepository.save(existingExpenses);
		
	}
}
