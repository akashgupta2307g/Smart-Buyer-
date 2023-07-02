package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest8_mysql {

	

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			// load JDBC driver class (optinal)
			Class.forName("com.mysql.cj.jdbc.Driver");
			// establish the connection 
			con=DriverManager.getConnection("jdbc:mysql:///ntaj915db","root","password");
			//create Statement objs
			if(con!=null)
				st=con.createStatement();
			//preapre the SQl Query
			String query="SELECT*FROM STUDENT";
			// execute the query
			if(st!=null) 
				rs=st.executeQuery(query);
				// process the ResultSet objc
				if(rs!=null) {
					boolean isEmptied=true;
					while(rs.next()) {
						isEmptied=false;
						System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
					}// while 
					if(isEmptied) {
						System.out.println("record not found");
					}//if
					else {
						System.out.println("record found and displayed");
						
					}//else
				}//if
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
	    		  if(rs!=null)
	    			    rs.close();
	    	}
	    	catch(SQLException se ) {
	    		se.printStackTrace();
	    	}
	    	try {
	    		if(st!=null)
	    			st.close();
	    	}
	    	catch(SQLException se) {
	    		se.printStackTrace();
	    	}
			
			try {
				if(con!=null)
					con.close();
			}
			catch(SQLException se) { 
				se.printStackTrace();
			
				
			}
	      }//finaly
	
		
	}//main
			
		

	}//class


