package com.jelena;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.jelena.business.*;
import com.jelena.data.*;
import com.jelena.data.jdbc.*;
import java.util.List;


public class SearchEmployeeServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		System.out.println("In the SearchEmployeeServlet servlet, doPost method ");
		
		JdbcEmployeeRepository jdbcEmployeeRepository = new JdbcEmployeeRepository();
		
		String language = request.getParameter("language");
		
		List<Employee> empList = jdbcEmployeeRepository.retrieveEmployeesByLanguage(language);
		for (Employee employee : empList) { 
			System.out.println(employee);
		}
		
		request.setAttribute("employees", empList);
		request.setAttribute("language", language);
		
		RequestDispatcher view = request.getRequestDispatcher("/search.jsp");
		view.forward(request, response);		
	}
}