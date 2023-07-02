package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connectionn {

	public static void main(String[] args) {
	Class.forName("oracle.jdbc.driver.OracleDriver");
	 Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
	 		if(con==null) 
	 			System.out.println("Connection is not Established");
	 		else 
	 			System.out.println("Connection is established");
		
	}// end of main

}// end of class
