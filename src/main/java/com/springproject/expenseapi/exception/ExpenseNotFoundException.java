package com.springproject.expenseapi.exception;

public class ExpenseNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5385296876347127736L;

	public ExpenseNotFoundException(String message) {
		super(message);
	}
}
