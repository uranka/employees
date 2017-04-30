package com.jelena.business;

import java.util.List;
import java.util.ArrayList;

public class Employee {

	private String firstName;
	private String lastName;
	private String sex;
	List<String> languages; 
	
	public Employee() {
		firstName = "";
		lastName = "";
		sex = "";
		languages = new ArrayList<String>();
	}
	
	public Employee(String firstName, String lastName, String sex) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.sex = sex;
	}
	
	public Employee(String firstName, String lastName, String sex, List<String> languages) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.sex = sex;
		this.languages = languages;
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
	
	public String getSex() {
		return sex;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}

	public List<String> getLanguages() {
		return languages;
	}

	public void setLanguages(List<String> languages) {
		this.languages = languages;
	}	
}	
	
	

