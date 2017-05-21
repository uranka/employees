package com.jelena;


import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.jelena.business.*;
import com.jelena.data.*;
import com.jelena.data.jdbc.*;
import java.util.List;


public class ShowEmployeesServlet extends HttpServlet {	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		System.out.println("In the ShowEmployeesServlet servlet, doGet method ");
		
		JdbcEmployeeRepository jdbcEmployeeRepository = new JdbcEmployeeRepository();
		
		int pageNumber = Integer.parseInt(request.getParameter("page"));
		int pageSize = 10;
		System.out.println("Page #" + pageNumber);
		List<Employee> empList = jdbcEmployeeRepository.retrievePageSizeEmployees(pageNumber, pageSize); 
		for (Employee employee : empList) {
			System.out.println(employee);
		}
		
		request.setAttribute("employees", empList);		
		
		// total number of employees in the employees table
		int total = jdbcEmployeeRepository.getEmployeeCount();
		System.out.println("tatal = " + total);
		
		int numberOfPages = (int)Math.ceil((double)total/pageSize);
		request.setAttribute("numberOfPages", numberOfPages);
		
		RequestDispatcher view = request.getRequestDispatcher("/employees.jsp");
		view.forward(request, response);	
	}
}
		