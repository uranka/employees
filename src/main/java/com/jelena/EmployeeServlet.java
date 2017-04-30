package com.jelena;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.jelena.business.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

//http://localhost:8080/employees/employee
// Na ovaj servlet se POST metodom dolazi preko forme klikom na submit dugme submit

public class EmployeeServlet extends HttpServlet {	

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String sex = request.getParameter("sex");
		String[] lang = request.getParameterValues("languages");		
		List<String> languages = Arrays.asList(lang);
		String degree = request.getParameter("degree");
		Employee emp = new Employee(firstName, lastName, sex, languages, degree);	
		
		request.setAttribute("employee", emp);
		
		RequestDispatcher view = request.getRequestDispatcher("/view.jsp");
		view.forward(request, response);
	}
}
		