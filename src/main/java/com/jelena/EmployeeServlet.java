package com.jelena;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
//import murach.business.*;

//import java.util.LinkedHashMap;
//import java.util.Map;


//http://localhost:8080/employees/employee
// Na ovaj servlet se POST metodom dolazi preko forme klikom na submit dugme submit

public class EmployeeServlet extends HttpServlet {	

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String name = request.getParameter("username");
		request.setAttribute("name", name);
		RequestDispatcher view = request.getRequestDispatcher("/view.jsp");
		view.forward(request, response);
	}
}
		