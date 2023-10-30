package com.springproject.expenseapi.exception;

public class ItemAlreadyExistsException extends RuntimeException {

	public ItemAlreadyExistsException(String message) {
		super(message);
	}
}
