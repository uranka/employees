package com.jelena.business;

public class Employee {

	private String firstName;
	private String lastName;
	
	public Employee() {
		firstName = "";
		lastName = "";
	}
	
	public Employee(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String LastName) {
		this.lastName = lastName;
	}
}	
	
	

