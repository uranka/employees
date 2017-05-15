package com.jelena;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.jelena.business.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import com.jelena.data.*;
import com.jelena.data.jdbc.*;

//http://localhost:8080/employees/employee
// Na ovaj servlet se POST metodom dolazi preko forme klikom na submit dugme submit

public class EmployeeServlet extends HttpServlet {	
	private InMemoryService inMemoryService = new InMemoryService(); 
	private JdbcEmployeeRepository jdbcEmployeeRepository = new JdbcEmployeeRepository();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String sex = request.getParameter("sex");
		System.out.println("kontrola parametra sex koji dolazi sa forme, sex = " + sex);
		String[] lang = request.getParameterValues("languages");		
		List<String> languages = Arrays.asList(lang);
		String degree = request.getParameter("degree");
		
		// dodaj i id koji ne dolazi preko requsta nego o njemu vodi racuna klasa InMemoryService 
		// servlet ce preko klase InMemoryService da pravi id i da snima u mapu u memoriji (kasnije u bazu)		
		Employee emp = new Employee(firstName, lastName, sex, languages, degree);			
		Employee empSavedToMap = inMemoryService.save(emp); // ovo vraca objekat klase employee sa podesenim id
		// snimi ga i u bazu
		// insert ako ga je novi (nema jos dodoljen id), a update ako ga ima (ima dodeljen id)		
		// napravi klasu JdbcEmployeeRepository koja radi save objekta Employee u bazu
		// ali i findOne prema id-u, findAll,..
		// https://gitlab.levi9.com/d.gajic/code9/tree/master/tutorial/workshop3/lab11-end/src/main/java/rs/code9/taster/repository
		// pravim samo insert, ko god da je dosao sa forme taj je za insert
		
		jdbcEmployeeRepository.insertEmployee(empSavedToMap); // ovaj employee je prilikom snimanja u mapu dobio id
		// ovo zasad snima samo zaposlenog ali ne i jezike koje on govori u tabelu employees_languages!!!!!!!!!!
		
		// za prikaz mu ne treba id pa mogu i emp da saljem
		request.setAttribute("employee", emp);
		
		// postaviti kao atribut i listu svih zaposlenih do sada unetih (i snimljenih u mapu)
		List<Employee> listEmployees = inMemoryService.findAll();
		// kontrola u konzoli
		System.out.println("-------------------------------------------");
		for (Employee e : listEmployees) {
			System.out.println(e.getFirstName() + ", " + e.getLastName() + ", " +  e.getSex());
		}		
		System.out.println("-------------------------------------------");
		request.setAttribute("listEmployees", listEmployees);
		
		// test baze
		// PreparedStatementTest.insertTwoEmployeesTest();
		
		RequestDispatcher view = request.getRequestDispatcher("/view.jsp");
		view.forward(request, response);
	}
}
		