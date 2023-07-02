package com.nt.jdbc;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class SelectTest7 {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			  //load JDBC Driver Class (optinal)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			 // Establish the connection 
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","manager");
			// create statement obj
			if(con!=null)
				st=con.createStatement();
			// prepare the SQL Query 
			String query="SELECT COUNT(*)FROM EMP";
			System.out.println(query);
			// send and execute the QUERY
			if(st!=null) 
				rs=st.executeQuery(query);
				// process the resultset obj
				if(rs!=null) {
					rs.next();
					// Int count=getInt(1);
					int count=rs.getInt("count(*)");
					System.out.println("emp db table record count is::"+count);
					
				}// if 
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
		      }
		
			
		}
		 
	}


