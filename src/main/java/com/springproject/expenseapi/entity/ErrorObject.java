package com.springproject.expenseapi.entity;

import java.sql.Date;

import lombok.Data;

@Data
public class ErrorObject {

	private Integer statusCode;
	private String message;
	private long timestamp;
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	
}
