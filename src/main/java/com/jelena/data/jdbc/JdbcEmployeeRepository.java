package com.jelena.data.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Types;
import javax.servlet.http.*;
import java.io.*;
import javax.imageio.ImageIO;

import java.sql.Blob;

import com.jelena.business.*;
import com.jelena.exceptions.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;

import javax.validation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;


public class JdbcEmployeeRepository {
	
	private ValidatorFactory vf = Validation.buildDefaultValidatorFactory();	
	private Validator validator = vf.getValidator();
	private Set<ConstraintViolation<Employee>> violations;
	
	
	public void insertEmployee(Employee employee, Part filePart) throws IOException{		
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			InputStream fileContent = null; // input stream of the upload file
			
			// trigger validation
			violations = validator.validate(employee);
			if (violations.size() > 0) {
				for (ConstraintViolation<Employee> violation : violations){
					System.out.println(
							violation.getRootBeanClass().getSimpleName() +
						"." + violation.getPropertyPath() +
						"-" + violation.getInvalidValue() + 
						"-" + violation.getMessage()						
							);
				}
			}
			else {
				
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
						
						// picture
						// if picture is not jpeg format everything else about employee will be inserted
						// into the database and picture will be set to NULL
						fileContent = filePart.getInputStream();	
							
						boolean isJpeg = false;									
						try {
							isJpeg = isImageFormatJpeg(fileContent); // isImageFormatJpeg pokvari input stream 								
							fileContent = filePart.getInputStream();// uzmi ponovo input stream 
							System.out.println("ending try jpeg");
						}
						catch(NoImageReaderException e) {
							System.out.println(e.getMessage());	
							System.out.println("ending no image reader catch jpeg");						
						}
						
						System.out.println("continue..");
						System.out.println("isJpeg: " + isJpeg);
											
						if (isJpeg) {
							pstmt.setBlob(6, fileContent);
						}
						else {
							pstmt.setNull(6, Types.BLOB);
						}							
							
						// Execute the statement
						pstmt.executeUpdate();	
						// close mora posle executeUpdate, ne moze pre
						fileContent.close();
						 
						
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
					/***********************/
					List<String> languages = findEmployeeLanguages(employeeId);					
					/***********************/
					emp = new Employee(Long.valueOf(id), firstName,lastName, sex, languages, degree);
					
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
	
/***************************************************************************************************/
	public PreparedStatement getSelectEmployeeLanguagesSQL(Connection conn) throws SQLException {
		String SQL = "SELECT l.name " +
					"FROM employees e JOIN employees_languages el " +
					"USING(employee_id) " +
					"JOIN languages l " +
					"USING(language_id) " +
					"WHERE e.employee_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(SQL);
		return pstmt;
	}
	
	public List<String> findEmployeeLanguages(int employeeId) {
		Connection conn = null;
		PreparedStatement pstmt = null;		
		List<String> empLanguages = new ArrayList<String>();					
		try {		
			conn = JDBCUtil.getConnection();			
			pstmt = getSelectEmployeeLanguagesSQL(conn);								
			pstmt.setLong(1, employeeId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String language = rs.getString(1);
				empLanguages.add(language);
			}
			JDBCUtil.commit(conn);
			System.out.println("Employee's languages selected successfully.");
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());	
			JDBCUtil.rollback(conn);	
		}
		finally {		
			JDBCUtil.closeStatement(pstmt);
			JDBCUtil.closeConnection(conn);
		}
		return empLanguages;			
	}
	
	public boolean isImageFormatJpeg(InputStream is) throws IOException, NoImageReaderException  {
		ImageInputStream iis = ImageIO.createImageInputStream(is);
		// get all currently registered readers that recognize the image format		
		Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
		if (!iter.hasNext()) {
			throw new NoImageReaderException ("No readers found says isImageFormatJpeg function!");			
		}
		
		System.out.println("Readers found");
		// get the first reader
		ImageReader reader = iter.next();
		String format = reader.getFormatName();		
		//iis.close();	
		return format.equalsIgnoreCase("JPEG")? true : false;		
	}

/**************************** PAGINATION *********************************************/	
	// Vraca listu zaposlenih za stranicu ciji je redni broj pageNumber, 
	// a velicine pageSize. Velicina se izrazava u broju zaposlenih.
	public List<Employee> retrievePageSizeEmployees(int pageNumber, int pageSize) {
		Connection conn = null;
		PreparedStatement pstmt = null;		
		Employee emp = null;
		List<Employee> empList = new ArrayList<Employee>();
		try {		
			conn = JDBCUtil.getConnection();			
			pstmt = getSelectPageSizeEmployeesSQL(conn);								
			pstmt.setInt(1, (pageNumber - 1) * pageSize);
			pstmt.setInt(2, pageSize);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int employeeId = rs.getInt("employee_id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");					
				String sex = rs.getString("sex");
				String degree = rs.getString("degree");
					
				List<String> languages = findEmployeeLanguages(employeeId);				
				emp = new Employee(Long.valueOf(employeeId), firstName,lastName, sex, languages, degree);
				empList.add(emp);
			}				
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());	
			JDBCUtil.rollback(conn);	
		}
		finally {		
			JDBCUtil.closeStatement(pstmt);
			JDBCUtil.closeConnection(conn);
		}			
		return empList;
	}
	
	
	public PreparedStatement getSelectPageSizeEmployeesSQL(Connection conn) throws SQLException {
		String SQL = "SELECT employee_id, first_name, last_name, sex, degree " +
					"FROM employees " +
					"ORDER BY employee_id " +
					"LIMIT ?, ? ";
		PreparedStatement pstmt = conn.prepareStatement(SQL);
		return pstmt;		
	}
}