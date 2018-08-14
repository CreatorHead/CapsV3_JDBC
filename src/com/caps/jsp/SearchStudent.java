package com.caps.jsp;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SearchStudent {
	public static void main(String[] args) {
		Connection con = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		System.out.println("Enter a regno: ");
		Scanner in = new Scanner(System.in);
		int sid = Integer.parseInt(in.nextLine());
		try {
			//1. Load the Driver
			Class.forName("com.mysql.jdbc.Driver");
			
			//2. Get the DB connection via Driver
			String dbUrl = "jdbc:mysql://localhost:3306/capsV3_db?user=root&password=root";
			con = DriverManager.getConnection(dbUrl);
			
			//3. Issue the Sql query
			String sql = "call getStudentInfo2(?)";
			cstmt = con.prepareCall(sql);
			cstmt.setInt(1, sid);
			rs = cstmt.executeQuery();
			
			
			/*
			 * 4. Process the results
			 */

			if(rs.next()){
				int regno = rs.getInt("sid");
				String firstname = rs.getString("firstname");
				String lastname = rs.getString("lastname");
				String isAdmin = rs.getString("isadmin");
				String passwd = rs.getString("password");

				System.out.println(regno);
				System.out.println(firstname);
				System.out.println(lastname);
				System.out.println(isAdmin);
				System.out.println(passwd);
				System.out.println("*********************");
			}else {
				System.out.println("No Data is Present");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			/*
			 * 5. Close all the JDBC Objects
			 */

			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(cstmt != null){
				try {
					cstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		
	}

}
