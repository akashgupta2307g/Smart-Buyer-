//insertTest.java
package com.nt.jdbc;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;
public class insertTest {

	public static void main(String[] args) throws ClassNotFoundException {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		try {
			//read input values
			sc=new Scanner(System.in);
			int no=0;
			String name=null,addrs=null;
			float avg=0.0f;
			if(sc!=null) {
				System.out.println("Enter Student Number");
				no=sc.nextInt();//gives 1001
				System.out.println("Enter Student Name");
				name=sc.next();//gives raja
				System.out.println("Enter Student Address");
				addrs=sc.next();//gives hyd
				System.out.println("Enter Student avg");
				avg=sc.nextFloat();//gives 90.6
				
			}//if
			//convert input values as required for the SQL Query
			name="'"+name+"'";// gives 'raja'
			addrs="'"+addrs+"'";//gives 'hyd'
			// ragister JDBC driver s/w (optinal)
			//class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection 
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","manager");
			//create Statement object
			if(con!=null)
				st=con.createStatement();
			// prepare SQL Query
			  // insert into student values(100,'raja','hyd',90.6);
			String query="INSERT INTO STUDENT VALUES("+no+","+name+","+addrs+","+avg+")";
			System.out.println(query);
			
			// send and execute the SQL Query in Db s/w
			 if(st!=null) {
				 int count=st.executeUpdate(query);
				 // process 
				 if(count==0)
					 System.out.println("Record not inserted");
				 else
					 System.out.println("Record inserted");
			 }//if
		
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
        catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//class jdbc objs
			try {
				if(st!=null)
					st.cancel();
				
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
