package com.springproject.expenseapi.service;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;

import com.springproject.expenseapi.entity.Expenses;

public interface ExpenseService {

	List <Expenses> getAllExpenses();
	
	Expenses getExpenseById(long id);
	
	void deleteExpenseById(long id);

	Expenses saveExpensesDeatails(Expenses expenses);
	
	Expenses updateExpensesDeatails(long id, Expenses expenses);


}
