package com.nt.jdbc;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.ResultSet; 
import java.sql.SQLException;
import java.sql.Statement;
public class SelectTest6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
         Connection con=null;
         Statement st=null;
         ResultSet rs=null;
         try {
        	 //register JDBC driver class
        	 Class.forName("oracle.jdbc.driver.OracleDriver");
        	 //Establish the connection
        	 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","manager");
        	 // create Statement object
        	 if(con!=null)
        	    st=con.createStatement();
        	 //prepare SQL Query
        	 String query="SELECT *FROM EMP WHERE SAL=(SELECT SAL FROM EMP)";
        	 System.out.println(query);
        	 //send and execute SQL Query
        	 if(st!=null)
        		 rs=st.executeQuery(query);
        	 //process the ResultSet obj
        	 if(rs!=null) {
        		 boolean isRSEmpty=true;
        	     while(rs.next()) {
        	    	 isRSEmpty=false;
        	    	 System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
        	     }
        	 if(isRSEmpty)
        		 System.out.println("No Records Found ");
        	 else
        		  System.out.println("Records Found and Displayed");
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
    		if(con!=null)
    			con.close();
    	}
    	catch(SQLException se) {
    		se.printStackTrace();
    	}
    }//finally
}//main
}//class
