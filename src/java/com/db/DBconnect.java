package com.db;

import java.sql.DriverManager;
import java.sql.Connection;

public class DBconnect {
	
	
	private static Connection conn;
	
	public static Connection getconn() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/hospital_2";
			String user = "root";
			String pass = "Rudra@1604";
			conn = DriverManager.getConnection(url,user,pass);
			if(conn!=null) {
				System.out.println("Connection established");
			}
			else {
				System.out.println("Connection not established");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

}
