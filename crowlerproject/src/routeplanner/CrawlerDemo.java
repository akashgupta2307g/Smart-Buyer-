package routeplanner;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CrawlerDemo {

  
	       public static void main(String[] args) {
	              Document doc;
	              try {
	                     System.setProperty("http.proxyHost", "103.19.229.194");
	                     System.setProperty("http.proxyPort", "80");
	                     doc = Jsoup.connect("https://www.pricedekho.com/search/productsearch?q=Apple%20iPhone%206S%20Plus%20(Silver,%2016%20GB)&lang=en").userAgent("Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)").get();
	                     Elements newsHeadlines = doc.select(".container");
	                    // Elements main= newsHeadlines.select(".other");
	                    // Elements clearFix=main.select(".clearfix");
	                    // Elements resultwise=clearFix.select(".resultwise");
	                    // Elements searchview=newsHeadlines.select(".searchview");
	                     Elements productDiv= doc.getElementsByClass("producttitle");
	                    // String link= productDiv.attr("href");
	                     String link="/mobiles/apple-iphone-6s-plus-16gb-silver-price-piwDYV.html";
	                     System.out.println("link"+link);
	                     //newsHeadlines.select("");
	                     new CrawlerDemo().parsepage(link);
	              }
	              catch (IOException e) {
	                     // TODO Auto-generated catch block
	                     e.printStackTrace();
	              }
	             
	       }
	      
	      
	      
	       private void parsepage(String link)
	       {
	              System.setProperty("http.proxyHost", "103.19.229.194");
	              System.setProperty("http.proxyPort", "80");
	              Document doc;
	              try {
	                     String url="https://www.pricedekho.com"+link;
	                     doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)").get();
	                     //newsHeadlines.select("");
	                    
	              Elements elements= doc.getElementsByClass("price_col");
	              Elements storelink= elements.select(".GoToStore"); 
	 
	              for (Element element : storelink) {
	              String linkString=  element.attr("data-directurl"); 
	              System.out.println(linkString);
	              if(linkString.contains("http://www.amazon.in/"))
	              {
	              String linkWithUrl= linkString.split("dp/")[1];
	              System.out.println("AMAZON "+linkWithUrl.substring(0, linkWithUrl.indexOf("?")));
	              }
	             
	              if(linkString.contains("http://dl.flipkart.com"))
	              {
	                    
	              String linkWithUrl=linkString.split("pid=")[1];
	              System.out.println("Flipkart "+linkWithUrl.substring(0, linkWithUrl.indexOf("&")));      
	              }
	             
	 
	              }
	             
	              /*Elements ul = elements.select("ul");
	              Elements li = ul.select("li"); // select all li from ul
	 
	              for (int i = 0; i < li.size(); i++) {
	                 System.out.println(li.get(i).select("span > span").text()); 
	              }*/
	             
	              }
	              catch (Exception e) {
	                     // TODO Auto-generated catch block
	                     e.printStackTrace();
	              }
	             
	             
	             
	       }
	      
	}