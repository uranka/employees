package com.jelena.business;

import java.util.List;
import java.util.ArrayList;
import javax.validation.constraints.*;

public class Employee {
	
	private Long id;

	@NotNull	
	@Size (min = 2, max = 30)
	private String firstName;
	
	@NotNull
	@Size (min = 2, max = 30)
	private String lastName;
	
	@NotNull
	private String sex;
	
	@NotNull
	@Size (min = 1)
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
	
	public Employee(Long id, String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;		
		this.id = id;
	}	
	
	public Employee(String firstName, String lastName, String sex, String degree) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.sex = sex;
		this.degree = degree;
		id = null;
	}
	
	public Employee(Long id, String firstName, String lastName, String sex, String degree) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.sex = sex;
		this.degree = degree;
		this.id = id;
	}
	
	public Employee(String firstName, String lastName, String sex, List<String> languages, String degree) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.sex = sex;
		this.languages = languages;
		this.degree = degree;
		id = null;
	}
	
	public Employee(Long id, String firstName, String lastName, String sex, List<String> languages, String degree) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.sex = sex;
		this.languages = languages;
		this.degree = degree;
		this.id = id;
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
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", sex=" + sex
				+ ", languages=" + languages + ", degree=" + degree + "]";
	}	
}	
	
	

