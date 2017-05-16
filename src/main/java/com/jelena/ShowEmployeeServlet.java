package com.jelena;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.jelena.business.*;
import com.jelena.data.*;
import com.jelena.data.jdbc.*;


public class ShowEmployeeServlet extends HttpServlet {	

@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		JdbcEmployeeRepository jdbcEmployeeRepository = new JdbcEmployeeRepository();		
		
		System.out.println("In the ShowEmployeeServlet servlet, doPost method");
		
		int employeeId = Integer.parseInt(request.getParameter("employeeId"));		
		Employee emp = jdbcEmployeeRepository.findEmployee(employeeId);

		request.setAttribute("employee", emp);		
		
		RequestDispatcher view = request.getRequestDispatcher("/employee.jsp");
		view.forward(request, response);
	}
}