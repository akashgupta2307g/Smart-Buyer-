package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteTest {

	

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		Scanner sc=null;
		try {
			// read inputs 
			sc=new Scanner(System.in);
			float startAvg=0.0f,endAvg=0.0f;
			if(sc!=null) {
				System.out.println("Enter start avg range::");
				startAvg=sc.nextFloat();
				System.out.println("Enter end avg range::");
				endAvg=sc.nextFloat();
			}
			// load JDBC driver class (optinal)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// establish the connection 
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","manager");
			//create Statement objs
			if(con!=null)
				st=con.createStatement();
			//preapre the SQl Query
			// DELETE FROM STUDENT WHERE AVG>=67.88 AND <=90.77;
			
			String query="SELECT*FROM STUDENT WHERE AVG>="+startAvg+" AND AVG<="+endAvg;
			System.out.println(query);
	
			// sent execute the non-select SQl query
			int count=0;
			if(st!=null) 
				count=st.executeUpdate(query);
			// process the Result 
			if(count==0)
				System.out.println("no records Found for deletion");
			else
				System.out.println(count+"records found and deleted");
			
			}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
	    catch(ClassNotFoundException cnf){
	    	cnf.printStackTrace();
	    }
	    catch(Exception e) {
	    	e.printStackTrace();
	    }
	    finally {
	    	// close jdbc objs
	    	try {
	    		  if(st!=null)
	    			    st.close();
	    	}
	    	catch(SQLException se ) {
	    		se.printStackTrace();
	    	}
	    	try {
	    		if(con!=null)
	    			con.close();
	    	}
	    	catch(SQLException se) {
	    		se.printStackTrace();
	    	}
			
			try {
				if(sc!=null)
					sc.close();
			}
			catch(Exception e) { 
				e.printStackTrace();
			
				
			}
	      }//finaly
	
		
	}//main
			
		

	}//class





