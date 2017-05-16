package com.jelena;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.jelena.business.*;
import com.jelena.data.*;
import com.jelena.data.jdbc.*;
import java.sql.Blob;
import java.sql.SQLException;


public class PhotoServlet extends HttpServlet {	

@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		JdbcEmployeeRepository jdbcEmployeeRepository = new JdbcEmployeeRepository();		
		
		System.out.println("In the PhotoServlet servlet, doGet method");
						
		int employeeId = Integer.parseInt(request.getParameter("id"));	
		Blob photo = jdbcEmployeeRepository.findEmployeePhoto(employeeId);
		
		if (photo != null ) {
			try {
				int length = (int)photo.length();
				int position = 1;			
				byte[] photoBytes = photo.getBytes(position, length);
				photo.free();
				response.setContentType("image/jpg");
				response.setContentLength(length);
				response.getOutputStream().write(photoBytes);
			}
			catch(SQLException e) {
				System.out.println(e.getMessage());	
			}
		}
		// sta da uradim ako nema slike, da li mogu da posaljem neki prazan response
	}
}