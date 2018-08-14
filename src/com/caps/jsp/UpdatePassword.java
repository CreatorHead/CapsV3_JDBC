package com.caps.jsp;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import com.mysql.jdbc.Driver;

public class UpdatePassword {
	public static void main(String[] args) {
		
		System.out.println("Enter a New Password: ");
		Scanner in = new Scanner(System.in);
		String newPasswd1 = in.nextLine();
		System.out.println("Enter a New Password Again: ");
		String newPasswd2 = in.nextLine();
		System.out.println("Enter the regno: ");
		int sid = Integer.parseInt(in.nextLine());
		System.out.println("Enter the old Password: ");
		String oldPasswd = in.nextLine();
		
		if(!newPasswd1.equals(newPasswd2))
			throw new PasswordNotMatch();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			/*
			 * 1. Load the Driver
			 */
			java.sql.Driver driverRef = new Driver();
			DriverManager.registerDriver(driverRef);
			System.out.println("Driver Loaded...");
			
			/*
			 * 2. Get the DB Connection via Driver
			 */
			String dbUrl="jdbc:mysql://localhost:3306/capsV3_db";
			
			//3rd Way to get a DB Connection
			String filePath = "F:/Files/db.properties";
			FileReader reader = new FileReader(filePath);
			Properties prop = new Properties();
			prop.load(reader);
			
			con = DriverManager.getConnection(dbUrl, prop);
			
			
			System.out.println("Connected...");
			
			
			/*
			 * 3. Issue the SQL query via connection
			 */
			String sql = "update students_info set password=? where sid=? "
					+ " and password=?";
			
			int count = 0;
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, newPasswd1);
			pstmt.setInt(2,sid);
			pstmt.setString(3, oldPasswd);
			
			count = pstmt.executeUpdate();

			/*
			 * 4. Process the results
			 */

			if(count > 0) {
				System.out.println("Password Updated...");
			}else {
				System.out.println("Password Updation Failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
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
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
	}//end of main

}
