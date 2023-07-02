package com.nt.jdbc;

//SelectTest4.java
/* App to get emp details based on the given 3 destination (like CLERK, MANAGAER,SALESHMAN)in 
the ascending order of job 

author :: Team-J

version:: 1.0
*/

import java.util.Scanner;// Explicit pkg import
import java.sql.DriverManager;
import java.sql.Connection ;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectTest4
{
public static void main(String[] arg)
{

    Scanner sc=null;
    Connection con=null;
  ResultSet rs=null;
    Statement st=null;
     try{
     // read inputs
    sc=new Scanner(System.in);
    String desg1=null,desg2=null,desg3=null;
    if(sc!=null){
           System.out.println("Enter desg1:");
           desg1=sc.next().toUpperCase();// gives CLERK
           System.out.println("Enter desg2:");
            desg2=sc.next().toUpperCase();// gives MANAGER;
           System.out.println("Enter desg3:");
           desg3=sc.next().toUpperCase();// gives SALESHMAN;
        }
  // convert inputs values as required for SQL Query condition ('CLERK','MANAGER','SAlESHMAN')
String cond="('"+desg1+"','"+desg2+"','"+desg3+"')"; //gives('CLERK','MANAGER','SALESHMAN')
      System.out.println(cond);
     // register JDBC driver by loading jdbc driver class(optinal from jdbc4)
         Class.forName("oracle.jdbc.driver.OracleDriver");
     // Establish the connection 
        con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","manager");
     // create Statement object
         if(con!=null)
              st=con.createStatement();
       // prepare SQL Query with condition framed by variables 
          // SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE JOB IN('CLERK','MANAGER''SALESMAN') ORDER BY JOB";
          String query="SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE JOB IN('CLERK','MANAGER','SALESMAN') ORDER BY JOB";
          System.out.println(query);
   
     // send and execute SQL Query 
           if(st!=null)
                rs=st.executeQuery(query);
         // process the ResultSet obj
        if(rs!=null){

         boolean isRSEmpty=true;
         while(rs.next()){
   isRSEmpty=false;
             System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
   }// while
    if(isRSEmpty)
 System.out.println("No records Found");
  else
  System.out.println("The Above records are found");
   }//if
}//try

catch(SQLException se){ // to handle known excepion 
  se.printStackTrace();
}// catch 
catch(ClassNotFoundException cnf){// to handle known exception
cnf.printStackTrace();
}// catch
catch(Exception e){ // to handle unknown exception
e.printStackTrace();
}// catch

finally{

/* bad way of writing code to close objs

try{

if(rs!=null & & st!=null & & con!=null & & sc!=null){

 rs.close(); // if exception is raised in rs.close() method the control goes
 st.close(); // to catch(-) directly ie w are not fair chance to close 
 con.close(); // st,con,sc objs... so it bad practice 


sc.close();
   }//if

}// try

catch(SQLException se){
se.printStackTrace();
}*/


//good code to close jdbc objs

try{
    if(rs!=null)
         rs.close();
}
catch(SQLException se){
    se.printStackTrace();
}

try{

  if(st!=null)
      st.close();

}//try

catch(SQLException se){
   se.printStackTrace();
}// catch

try{

      if(con!=null)
          con.close();
 }//try

catch(Exception e){
   e.printStackTrace();

  }//catch

}//finally

}//main

}// class
