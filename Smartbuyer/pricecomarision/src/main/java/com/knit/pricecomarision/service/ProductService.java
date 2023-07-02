package com.knit.pricecomarision.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knit.pricecomarision.model.Image;
import com.knit.pricecomarision.model.MappingIds;
import com.knit.pricecomarision.model.MinProduct;
import com.knit.pricecomarision.model.Product;
import com.knit.pricecomarision.repository.ProductRepository;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;
	
	public Product getProduct(String id) {
		
		Product product = productRepository.getProductById(id);
		return product;
	}
	
	public MinProduct getBestProduct(String url){
		
		
		MappingIds mappingIds = new MappingIds();
		
	    if(url.contains("www.flipkart.com")) {
	    	
	    	String[] splittedString =url.split("pid=");
	    	if(splittedString.length>=2) {
	    		 String flipkartId = splittedString[1];
	    		if(flipkartId.contains("&")) {
		    		flipkartId = flipkartId.substring(0,flipkartId.indexOf("&"));
	    		}
	    		System.out.println(flipkartId);    		
	    		mappingIds = productRepository.getMappedIds("Flipkart_id" ,flipkartId);
	    	}
	    }
	    if(url.contains("www.amazon.in")) {
	    	
	    	String[] splitteStrings =url.split("/dp/");
	    	if(splitteStrings.length>=2) {
	    		 String amazonId = splitteStrings[1];
	    		if(amazonId.contains("/")) {
		    		amazonId = amazonId.substring(0,amazonId.indexOf("/"));
	    		}
	    		if(amazonId.contains("?")) {
	    			amazonId = amazonId.substring(0, amazonId.indexOf("?"));
	    		}
	    		System.out.println(amazonId);
	    		mappingIds = productRepository.getMappedIds("amozon_id" ,amazonId);
	    	}
	    	
	    }
	    if(url.contains("www.croma.com")) {
	    	String[] splitteStrings = url.split("/p/");
	    	
	    	String idString = splitteStrings[1];
	    	String cromaId = idString;
	    	System.out.println(cromaId);
	    	mappingIds = productRepository.getMappedIds("Croma_id" ,cromaId);
	    }
	    
	    if(url.contains("www.tatacliq.com")) {
	    	String[] splitteStrings = url.split("g/");
	    	if(splitteStrings.length>=2) {
	    		String idString = splitteStrings[1];
	    		String tataId = idString.substring(0,idString.indexOf("?"));
	    		System.out.println(tataId);;
	    	mappingIds = productRepository.getMappedIds("Tata_id" ,tataId);
	    	
	    }
	    }
	    if(url.contains("www.reliancedigital.in")) {
	    	String[] splitteStrings = url.split("/p/");
	    	
    		String idString = splitteStrings[1];
    		String relId = idString;
    		System.out.println(relId);
	    	mappingIds = productRepository.getMappedIds("Rel_id" ,relId);
	    	
	    }
	    updateLatestPriceByCrowler(mappingIds.getFlipkart_id(),mappingIds.getAmazon_id(),mappingIds.getRel_id(),mappingIds.getCroma_id(),mappingIds.getTata_id());
	  
	    MinProduct product = productRepository.getMinProductByflipkartIdOrAmazonId(mappingIds.getFlipkart_id(),mappingIds.getAmazon_id(), mappingIds.getRel_id(), mappingIds.getCroma_id() ,mappingIds.getTata_id());
		return product;
	}
	
	private void updateLatestPriceByCrowler(String flipkartId, String amazonId, String relId, String cromaId, String tataId) {
		
		float flipkartPrice = 0.0f;
		float amazonPrice = 0.0f;
		float relPrice = 0.0f;
		float tataPrice = 0.0f;
		float cromaPrice = 0.0f;
		
		float prodctPrice = 0.0f;
		if(flipkartId != null) {
			// call the scrapper 
			// 1. scap the flipkart price. ratings,images
			
			String flipkartUrl = "https://www.flipkart.com/infinix-smart-5a-quetzal-cyan-32-gb/p/itma0d1e81b14743?pid="+ flipkartId;
			System.setProperty("http.proxyHost", "103.19.229.194");
            System.setProperty("http.proxyPort", "80");
            Document doc;
			try {
				doc = Jsoup.connect(flipkartUrl).userAgent("Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)").get();
				Elements tem = doc.select("div._25b18c");
				Elements rating = doc.select("div._3LWZlK");
				
				//Elements images = doc.select("div._3A1TYz");
				
				String priceTxt = tem.first().text();
				String[] priceArr = priceTxt.split("₹");
				if(priceArr.length>0) {
					String bestPriceWithComma = priceArr[1];
					String bestPriceStr = bestPriceWithComma.replace(",","");
					 flipkartPrice = Integer.parseInt(bestPriceStr.trim());
					System.out.println(flipkartPrice);

				}
			    			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
       if(amazonId != null) {
    	// call the scrapper 
    	// scrap the amazon price, rattings  
    	   
    	   String amazonUrl = "https://www.amazon.in/OnePlus-Nord-Sierra-128GB-Storage/dp/" + amazonId;
			System.setProperty("http.proxyHost", "103.19.229.194");
           System.setProperty("http.proxyPort", "80");
           Document doc;
			try {
				doc = Jsoup.connect(amazonUrl).userAgent("Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)").get();
				Elements price = doc.select("span.a-offscreen");
				
				String bestPriceWithTxt = price.first().text();
				String bestPriceWithComma = bestPriceWithTxt.replace("₹","");
				
				String bestPriceStr = bestPriceWithComma.replace(",","");
				amazonPrice = Float.parseFloat(bestPriceStr);

				System.out.println(amazonPrice);
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
       
       if(relId != null) {
    	   
    	   // srp code for rel
       }
       
       if(cromaId != null) {
    	   
    	   // scrp code for croma
       }
       
       if(tataId != null) {
    	   
    	   // scrp code for tata
       }
       Product product =  productRepository.getAllPrice( flipkartId,  amazonId,  relId,  cromaId,  tataId);
       if(amazonPrice==0.0) {
    	   amazonPrice = product.getAmozPrice();
       }
        relPrice = product.getRelPrice();
		tataPrice = product.getTataPrice();
		cromaPrice = product.getCromaPrice();
		
       prodctPrice = amazonPrice;
       if(flipkartPrice < amazonPrice ) {
    	   prodctPrice = flipkartPrice;
       }
       
       if(amazonPrice == 0.0) {
    	   
    	   prodctPrice = flipkartPrice;
       }
       if(relPrice < prodctPrice && relPrice != 0.0) {
    	   prodctPrice = relPrice;
       }
       
       if(tataPrice < prodctPrice && tataPrice != 0.0) {
    	   prodctPrice = tataPrice;
       }
       
       if(cromaPrice < prodctPrice && cromaPrice != 0.0) {
    	   prodctPrice = cromaPrice;
       }
       //save the all the updtaed price in database
        
        
       if(prodctPrice>0.0) {
    	   productRepository.updateLatestPrice (prodctPrice, flipkartPrice,amazonPrice, flipkartId,amazonId);
       }
	}
}
