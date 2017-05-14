package com.jelena.data.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class PreparedStatementTest {
	
	public static void insertTwoEmployeesTest() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JDBCUtil.getConnection();
			pstmt = getInsertSQL(conn);
			
			// Insert two employees			
			insertEmployee(pstmt, 10,"Marko","Markov", "m", "PhD");			
			insertEmployee(pstmt, 11,"Jelena","Gavanski", "f", "Master");	
						
			JDBCUtil.commit(conn);
			System.out.println("Employees inserted successfully.");
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			JDBCUtil.rollback(conn);
		}
		finally {
			JDBCUtil.closeStatement(pstmt);
			JDBCUtil.closeConnection(conn);
		}
	}
	
	
	public static void insertEmployee(PreparedStatement pstmt,
			int employeeId, String firstName, String lastName,
			String sex, String degree)
			throws SQLException {		
			// Set all the input parameters
			pstmt.setInt(1, employeeId);
			pstmt.setString(2, firstName);
			pstmt.setString(3, lastName);
			pstmt.setString(4, sex);
			// degree can be null
			if (degree == null) {
				pstmt.setNull(5, Types.VARCHAR);
			}
			else {
				pstmt.setString(5, degree);
			}				
			// Execute the statement
			pstmt.executeUpdate();
	}
	
	
	public static PreparedStatement getInsertSQL(Connection conn) throws SQLException {
			String SQL = "INSERT INTO employees " +
						"(employee_id, first_name, last_name, sex, degree) " +
						"VALUES " +
						"(?, ?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			return pstmt;
	}
}
