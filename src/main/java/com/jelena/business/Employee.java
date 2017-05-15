package com.jelena.business;

import java.util.List;
import java.util.ArrayList;

public class Employee {
	
	private Long id;

	private String firstName;
	private String lastName;
	private String sex;
	List<String> languages; 
	private String degree;
	
	public Employee() {
		firstName = "";
		lastName = "";
		sex = "";
		languages = new ArrayList<String>();
		degree="";
		id = null;		
	}
	
	public Employee(String firstName, String lastName, String sex) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.sex = sex;
		id = null;
	}
	
	public Employee(String firstName, String lastName, String sex, List<String> languages, String degree) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.sex = sex;
		this.languages = languages;
		this.degree = degree;
		id = null;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
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
	
	public void setLastName(String lastName) {
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
	
	public String getDegree() {
		return degree;
	}
	
	public void setDegree(String degree) {
		this.degree = degree;
	}		
}	
	
	

