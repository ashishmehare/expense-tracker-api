package com.springproject.expenseapi.dto;

public class UserDTO {
	

	private long id;
	
	private String emailString;
	


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmailString() {
		return emailString;
	}

	public void setEmailString(String emailString) {
		this.emailString = emailString;
	}

	public String getPasswordString() {
		return passwordString;
	}

	public void setPasswordString(String passwordString) {
		this.passwordString = passwordString;
	}

	public long getAge() {
		return age;
	}

	public void setAge(long age) {
		this.age = age;
	}

	private String passwordString;
	
	private long age;
	
	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", emailString=" + emailString + ", passwordString=" + passwordString + ", age="
				+ age + "]";
	}

}
