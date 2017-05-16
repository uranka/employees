package com.jelena.data.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Types;
import javax.servlet.http.*;
import java.io.*;

import java.sql.Blob;

import com.jelena.business.*;


public class JdbcEmployeeRepository {
/*	
	public Employee save(final Employee employee) {
		if (employee.getId() != null) {
			// update
			System.out.println("Employee update");
		}
		else{
			// insert
			
			System.out.println("Employee insert");			
		}
	}
*/

	
	public void insertEmployee(Employee employee, Part filePart) throws IOException{		
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			InputStream fileContent = null; // input stream of the upload file
			
			try {
				conn = JDBCUtil.getConnection();
				
				try {
					// INSERT EMPLOYEE
					pstmt = getInsertEmployeeSQL(conn);
					
					// Set all the input parameters
					pstmt.setLong(1, employee.getId());// u bazi mi int a u java objektu long
					pstmt.setString(2, employee.getFirstName());
					pstmt.setString(3, employee.getLastName());
					pstmt.setString(4, employee.getSex());
					// degree can be null
					if (employee.getDegree() == null) {
						pstmt.setNull(5, Types.VARCHAR);
					}
					else {
						pstmt.setString(5, employee.getDegree());
					}	
					fileContent = filePart.getInputStream();
					if (fileContent != null) {
						pstmt.setBlob(6, fileContent);
					}
					// Execute the statement
					pstmt.executeUpdate();	
					
/************************** INSERT EMPLOYEE'S LANGUAGES********************************************/											
					
					pstmt = getInsertEmployeeLanguagesSQL(conn);					
			
					for (String language : employee.getLanguages()) {
					
						pstmt.setLong(1, employee.getId());
						// prvo proveri da nije convert vratio 0
						pstmt.setInt(2, convertLanguageNameToId(conn, language)); 
						pstmt.executeUpdate();
					}
					
					JDBCUtil.commit(conn);
					System.out.println("Employee and employee's languages inserted successfully.");
				}
/*****************************************************************************************************/					
				catch (SQLException e) {
					System.out.println(e.getMessage());
					JDBCUtil.rollback(conn);
				}
				finally {					
					JDBCUtil.closeStatement(pstmt);				
				}	
				
			}
			catch (SQLException e) {
				System.out.println(e.getMessage());				
			}
			finally {				
				JDBCUtil.closeConnection(conn);
			}		
	}	
	
	public PreparedStatement getInsertEmployeeSQL(Connection conn) throws SQLException {
			String SQL = "INSERT INTO employees " +
						"(employee_id, first_name, last_name, sex, degree, picture) " +
						"VALUES " +
						"(?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			return pstmt;
	}
	
	public PreparedStatement getInsertEmployeeLanguagesSQL(Connection conn) throws SQLException {
			String SQL = "INSERT INTO employees_languages " +
						"(employee_id, language_id) " +
						"VALUES " +
						"(?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			return pstmt;
	}	
/**************************************************************************************************************/
	public int convertLanguageNameToId(Connection conn, String language) throws SQLException {	
		Statement stmt = null;
		String SQL = "SELECT languages.language_id " +
					"FROM languages " +
					"WHERE languages.name = " + "'" + language + "'";
		int retVal = 0;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			// izvadi iz result seta 
			if (rs.first()) {
				retVal = rs.getInt(1);
			}
			return retVal;			
		}		
		finally {
			JDBCUtil.closeStatement(stmt);
		}
	}		
/**************************************************************************************************************/		
	
	public Employee findEmployee(int employeeId) {
		Connection conn = null;
		PreparedStatement pstmt = null;		
		Employee emp = null;					
			try {		
				conn = JDBCUtil.getConnection();			
				pstmt = getSelectEmployeeSQL(conn);								
				pstmt.setLong(1, employeeId);
				ResultSet rs = pstmt.executeQuery();
				// upakuj rs u objekat Employee
				// Moves the cursor to the first row in this ResultSet object
				// true if the cursor is on a valid row; false if there are no rows in the result set					
				if (rs.first()) {
					int id = rs.getInt("employee_id");
					String firstName = rs.getString("first_name");
					String lastName = rs.getString("last_name");					
					String sex = rs.getString("sex");
					String degree = rs.getString("degree");
					emp = new Employee(Long.valueOf(id), firstName,lastName, sex, degree);
					
					//kontrola
					System.out.print("Employee ID: " + id);
					System.out.print(", First Name: " + firstName);
					System.out.println(", Last Name: " + lastName);
					System.out.println("Employee selected successfully.");					
				}								
				JDBCUtil.commit(conn);				
				//return emp;
			}
			catch (SQLException e) {
				System.out.println(e.getMessage());	
				JDBCUtil.rollback(conn);	
			}
			finally {		
				JDBCUtil.closeStatement(pstmt);
				JDBCUtil.closeConnection(conn);
			}
			return emp;			
	}
	
	public PreparedStatement getSelectEmployeeSQL(Connection conn) throws SQLException {
		String SQL = "SELECT employee_id, first_name, last_name, sex, degree " +
					"FROM employees " +
					"WHERE employee_id = ?";					
		PreparedStatement pstmt = conn.prepareStatement(SQL);
		return pstmt;		
	}
	
	
	public Blob findEmployeePhoto(int employeeId) {
		Connection conn = null;
		PreparedStatement pstmt = null;		
		Blob photo = null;					
		try {		
			conn = JDBCUtil.getConnection();			
			pstmt = getSelectEmployeePhotoSQL(conn);								
			pstmt.setLong(1, employeeId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.first()) {
				photo = rs.getBlob("picture");					
				System.out.println("Employee's photo selected successfully.");
			}
			JDBCUtil.commit(conn);
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());	
			JDBCUtil.rollback(conn);	
		}
		finally {		
			JDBCUtil.closeStatement(pstmt);
			JDBCUtil.closeConnection(conn);
		}
		return photo;
	}
			
	
	
	public PreparedStatement getSelectEmployeePhotoSQL(Connection conn) throws SQLException {
		String SQL = "SELECT picture " +
					"FROM employees " +
					"WHERE employee_id = ?";					
		PreparedStatement pstmt = conn.prepareStatement(SQL);
		return pstmt;		
	}	
}