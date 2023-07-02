package com.knit.pricecomarision.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.knit.pricecomarision.model.Image;
import com.knit.pricecomarision.model.MappingIds;
import com.knit.pricecomarision.model.MinProduct;
import com.knit.pricecomarision.model.Product;

@Repository
public class ProductRepository {
	
	public Product getProductById(String id) {
		         Product product = null;

		         Connection con=null;
		         Statement st=null;
		         ResultSet rs=null;
		         try {
		        	 //register JDBC driver class
		        	 Class.forName("com.mysql.jdbc.Driver");
		        	 //Establish the connection
		        	 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/price_comparison","root","password");
		        	 // create Statement object
		        	 if(con!=null)
		        	    st=con.createStatement();
		        	 //prepare SQL Query
		        	 String query="SELECT * FROM PRODUCT_MAPPING WHERE p_id = "  + id;
		        	 System.out.println(query);
		        	 //send and execute SQL Query
		        	 if(st!=null)
		        		 rs=st.executeQuery(query);
		        	 //process the ResultSet obj
		        	 if(rs!=null) {
		        		 product = new Product();
		        	     while(rs.next()) {
		        	    	 product.setId(rs.getInt(1));
		        	    	 product.setFlipUrl("https://www.flipkart.com/infinix-smart-5a-quetzal-cyan-32-gb/p/itma0d1e81b14743?pid="+ rs.getString(2));
		        	    	 product.setFlipPrice(rs.getFloat(3));
		        	    	 product.setFlipReview(rs.getFloat(4));
		        	    	 product.setAmozUrl("https://www.amazon.in/OnePlus-Nord-Sierra-128GB-Storage/dp/" + rs.getString(5));
		        	    	 product.setAmozPrice(rs.getFloat(6));
		        	    	 product.setAmozReview(rs.getFloat(7));
		        	    	 product.setP_name(rs.getString(8));
		        	    	 product.setP_price(rs.getFloat(9));
		        	    	 product.setImage(getImagesById(id));
		        	    	 product.setRelUrl("https://www.reliancedigital.in/motorola-moto-g52-64-gb-4-gb-ram-white-mobile-phone/p/" + rs.getString(10));
		        	    	 product.setRelPrice(rs.getFloat(11));
		        	    	 product.setRelReview(rs.getFloat(12));
		        	    	 product.setCromaUrl("https://www.croma.com/redmi-note-10t-5g-128gb-rom-6gb-ram-mzb09e0in-graphite-black-/p/" + rs.getString(13));
		        	    	 product.setCromaPrice(rs.getFloat(14));
		        	    	 product.setCromaReview(rs.getFloat(15));
		        	    	 product.setTataUrl("https://www.tatacliq.com/redmi-note-10t-128-gb-chromium-white-6-gb-ram-dual-sim-5g/" +rs.getString(16));
		        	    	 product.setTataPrice(rs.getFloat(17));
		        	    	 product.setTataReview(rs.getFloat(18));
		        	    	 
		        	    	 
		        	     }
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
		//}//main
		//}//class
		return product;
	}
	
	public List<Image> getImagesById(String id){
		
   	 List<Image> imageList = new ArrayList();

         Connection con=null;
         Statement st=null;
         ResultSet rs=null;
         try {
        	 //register JDBC driver class
        	 Class.forName("com.mysql.jdbc.Driver");
        	 //Establish the connection
        	 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/price_comparison","root","password");
        	 // create Statement object
        	 if(con!=null)
        	    st=con.createStatement();
        	 //prepare SQL Query
        	 String query="SELECT * FROM product_images WHERE p_id = "  + id;
        	 System.out.println(query);
        	 //send and execute SQL Query
        	 if(st!=null)
        		 rs=st.executeQuery(query);
        	 //process the ResultSet obj
        	 if(rs!=null) {
        	     while(rs.next()) {
        	    	 Image image = new Image(rs.getString(2));
        	    	 imageList.add(image);
        	     }
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
//}//main
//}//class

return imageList;
	}
	
	public String getAmazonId(String flipkartId) {
		
         Connection con=null;
         Statement st=null;
         ResultSet rs=null;
         String amazonId = null;
         try {
        	 //register JDBC driver class
        	 Class.forName("com.mysql.jdbc.Driver");
        	 //Establish the connection
        	 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/price_comparison","root","password");
        	 // create Statement object
        	 if(con!=null)
        	    st=con.createStatement();
        	 //prepare SQL Query
        	 String query="select amozon_id from product_mapping where flipkart_id = '"  + flipkartId +"'";
        	 System.out.println(query);
        	 //send and execute SQL Query
        	 if(st!=null)
        		 rs=st.executeQuery(query);
        	 //process the ResultSet obj
        	 if(rs!=null) {
        	     while(rs.next()) {
        	    	 amazonId = rs.getString(1) ;
        	     }
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
    }
         return amazonId;
	}

	public String getFlipkartId(String amazonId) {
		
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        String flipkartId = null;
        try {
       	 //register JDBC driver class
       	 Class.forName("com.mysql.jdbc.Driver");
       	 //Establish the connection
       	 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/price_comparison","root","password");
       	 // create Statement object
       	 if(con!=null)
       	    st=con.createStatement();
       	 //prepare SQL Query
       	 String query="select flipkart_id from product_mapping where amozon_id = '"  + amazonId + "'";
       	 System.out.println(query);
       	 //send and execute SQL Query
       	 if(st!=null)
       		 rs=st.executeQuery(query);
       	 //process the ResultSet obj
       	 if(rs!=null) {
       	     while(rs.next()) {
       	    	flipkartId = rs.getString(1) ;
       	     }
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
   }
        return flipkartId;
	}
	
public MappingIds  getMappedIds(String siteName, String id) {
	 MappingIds mappingIds = new MappingIds();
    	
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        
        try {
       	 //register JDBC driver class
       	 Class.forName("com.mysql.jdbc.Driver");
       	 //Establish the connection
       	 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/price_comparison","root","password");
       	 // create Statement object
       	 if(con!=null)
       	    st=con.createStatement();
       	 //prepare SQL Query
       	 String query="select flipkart_id,amozon_id,Rel_id, Croma_id, Tata_id from product_mapping where " +siteName+" = '"  + id + "'";
       	 System.out.println(query);
       	 //send and execute SQL Query
       	 if(st!=null)
       		 rs=st.executeQuery(query);
       	 //process the ResultSet obj
       	 if(rs!=null) {
       	     while(rs.next()) {
       	    	 mappingIds.setFlipkart_id(rs.getString(1));
       	    	 mappingIds.setAmazon_id(rs.getString(2));
       	    	 mappingIds.setRel_id(rs.getString(3));
       	    	 mappingIds.setCroma_id(rs.getString(4));
       	    	 mappingIds.setTata_id(rs.getString(5));
       	    	 
       	     }
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
   }
        return mappingIds;
	}
	

	public MinProduct getMinProductByflipkartIdOrAmazonId(String flipkartId, String amazonId, String relId, String cromaId, String tataId) {
        MinProduct minProduct = null;
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        try {
       	 //register JDBC driver class
       	 Class.forName("com.mysql.jdbc.Driver");
       	 //Establish the connection
       	 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/price_comparison","root","password");
       	 // create Statement object
       	 if(con!=null)
       	    st=con.createStatement();
       	 //prepare SQL Query
       	 String query="select p_id, p_name,p_price from price_comparison.product_mapping where amozon_id = '" + amazonId + "' or flipkart_id = '" + flipkartId +"' or Croma_id = '"+ cromaId + "' or Tata_id = '" +tataId + "' or Rel_id = '"+ relId + "'";
       	 System.out.println(query);
       	 //send and execute SQL Query
       	 if(st!=null)
       		 rs=st.executeQuery(query);
       	 //process the ResultSet obj
       	 if(rs!=null) {
       		minProduct = new MinProduct();
       	     while(rs.next()) {
       	    	minProduct.setProd_url("http://localhost:8000?id="+ rs.getInt(1));
       	 	    minProduct.setProd_name(rs.getString(2));
       	    	minProduct.setPrice(rs.getFloat(3));
       	     }
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
//}//main
//}//class
return minProduct;
}
	
	public void updateLatestPrice(float bestPrice,float flipkartPrice, float amazonPrice, String flipkartId, String amazonId) {
        Connection con=null;
        Statement st=null;
        try {
       	 //register JDBC driver class
       	 Class.forName("com.mysql.jdbc.Driver");
       	 //Establish the connection
       	 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/price_comparison","root","password");
       	 // create Statement object
       	 if(con!=null)
       	    st=con.createStatement();
       	 //prepare SQL Query
       	 String query="update product_mapping  set flipkart_price = "+ flipkartPrice +", amozon_price = "+ amazonPrice + ", p_price = "+ bestPrice +" where amozon_id = '" + amazonId + "' or flipkart_id = '" + flipkartId +"'";
       	 System.out.println(query);
       	 //send and execute SQL Query
       	 if(st!=null)
       		 st.executeUpdate(query);
       	 //process the ResultSet obj
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
   		if(con!=null)
   			con.close();
   	}
   	catch(SQLException se) {
   		se.printStackTrace();
   	}
   }//finally
//}//main
//}//class
        
}
	public Product getAllPrice(String flipkartId, String amazonId, String relId, String cromaId, String tataId) {
        Product product = null;
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        try {
       	 //register JDBC driver class
       	 Class.forName("com.mysql.jdbc.Driver");
       	 //Establish the connection
       	 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/price_comparison","root","password");
       	 // create Statement object
       	 if(con!=null)
       	    st=con.createStatement();
       	 //prepare SQL Query
       	 String query="select Flipkart_price, Amozon_Price,Rel_price,Croma_price,Tata_price from price_comparison.product_mapping where amozon_id = '" + amazonId + "' or flipkart_id = '" + flipkartId +"' or Croma_id = '"+ cromaId + "' or Tata_id = '" +tataId + "' or Rel_id = '"+ relId + "'";
       	 System.out.println(query);
       	 //send and execute SQL Query
       	 if(st!=null)
       		 rs=st.executeQuery(query);
       	 //process the ResultSet obj
       	 if(rs!=null) {
       		product = new Product();
       	     while(rs.next()) {
       	    	product.setFlipPrice(rs.getFloat(1));
       	    	product.setAmozPrice(rs.getFloat(2));
       	    	product.setTataPrice(rs.getFloat(3));
       	    	product.setCromaPrice(rs.getFloat(4));
       	    	product.setRelPrice(rs.getFloat(5));
       	     }
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
        return product;
	}
}
