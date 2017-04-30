package com.jelena;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.jelena.business.*;

//import java.util.LinkedHashMap;
//import java.util.Map;


//http://localhost:8080/employees/employee
// Na ovaj servlet se POST metodom dolazi preko forme klikom na submit dugme submit

public class EmployeeServlet extends HttpServlet {	

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		Employee emp = new Employee(firstName, lastName);	
		
		request.setAttribute("employee", emp);
		
		RequestDispatcher view = request.getRequestDispatcher("/view.jsp");
		view.forward(request, response);
	}
}
		