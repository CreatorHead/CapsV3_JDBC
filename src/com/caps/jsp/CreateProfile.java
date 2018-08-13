package com.caps.jsp;

import java.util.Scanner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateProfile {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter regno : ");
		int regno= Integer.parseInt(in.nextLine());
		System.out.println("Enter First Name: ");
		String fname = in.nextLine();
		System.out.println("Enter Last Name: ");
		String lname = in.nextLine();
		System.out.println("Enter is Admin: ");
		String isAdmin = in.nextLine();
		System.out.println("Enter Password: ");
		String passwd = in.nextLine();
		in.close();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			//1. Load the Driver
			Class.forName("com.mysql.jdbc.Driver");
			
			//2. Get the DB connection via Driver
			String dbUrl = "jdbc:mysql://localhost:3306/capsV3_db?user=root&password=root";
			con = DriverManager.getConnection(dbUrl);
			
			//3. Issue the Sql query
			String sql = "insert into students_info values(?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, regno);
			pstmt.setString(2,fname);
			pstmt.setString(3, lname);
			pstmt.setString(4, isAdmin.toUpperCase());
			pstmt.setString(5, passwd);
			
			int count = pstmt.executeUpdate();
			
			//4. Process the result
			if(count > 0) {
				System.out.println("Profile Created");
			}else {
				System.out.println("Profile Creation Failed");
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
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		
	}
}
