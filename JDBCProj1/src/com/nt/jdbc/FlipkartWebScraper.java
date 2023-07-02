package com.nt.jdbc;

//FlipkartWebScraper.java

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class FlipkartWebScraper {

	public static void main(String[] args) {
		try {
			
			System.setProperty("http.proxyHost", "103.19.229.194");
         System.setProperty("http.proxyPort", "80");
         String pid = "pid=MOBG43UGBTGGB99V";   //"pid=MOBG62SFDH9CEJWG";
         String url = "https://www.flipkart.com/redmi-note-10s-cosmic-purple-64-gb/p/itm2a1b4b90344a3?"+pid+"&lid=LSTMOBG62SFDH9CEJWGQ0FZG8&marketplace=FLIPKART&store=tyy%2F4io&srno=b_1_1&otracker=clp_banner_1_5.bannerX3.BANNER_mobile-phones-store_KKEGT8D5EA0X&fm=neo%2Fmerchandising&iid=9eddd6a7-45f1-4cca-9ee1-727a6661e6de.MOBG62SFDH9CEJWG.SEARCH&ppt=clp&ppn=mobile-phones-store&ssid=h9xjom4yps0000001650781959852";
			// Document doc = Jsoup.connect("https://www.flipkart.com/redmi-note-10s-cosmic-purple-64-gb/p/itm2a1b4b90344a3?pid=MOBG62SFDH9CEJWG&lid=LSTMOBG62SFDH9CEJWGQ0FZG8&marketplace=FLIPKART&store=tyy%2F4io&srno=b_1_1&otracker=clp_banner_1_5.bannerX3.BANNER_mobile-phones-store_KKEGT8D5EA0X&fm=neo%2Fmerchandising&iid=9eddd6a7-45f1-4cca-9ee1-727a6661e6de.MOBG62SFDH9CEJWG.SEARCH&ppt=clp&ppn=mobile-phones-store&ssid=h9xjom4yps0000001650781959852").userAgent("Mozilla/17.0").get();
			Document doc = Jsoup.connect(url).userAgent("Mozilla/17.0").get();
			Elements tem = doc.select("div._25b18c");
			Elements rating = doc.select("div._3LWZlK");
			
			//Elements images = doc.select("div._3A1TYz");
			
			System.out.println(tem.first().text());
			System.out.println(rating.first().text());
			/*
			for(Element img:images) {
				System.out.println(img.attr("src"));
			}
			
			//System.out.println(images.first().text());
			
			int i=0,j=0;
			for(Element price : tem) {
				i++;
				System.out.println(i+" "+ price.getElementsByTag("div").first().text());
			
			}// for
					
				for(Element rt: rating) {
				j++;
				System.out.println(j+" "+rt.getElementsByTag("span").first().text());
			}
			*/
		}// try
		catch(IOException e) {
			e.printStackTrace();
		}
	}

}
                       // amozon scrapper

class AmazoneWebScraper {

	public static void main(String[] args) {
		
		Document doc;
		try {
			
			System.setProperty("http.proxyHost", "103.19.229.194");
            System.setProperty("http.proxyPort", "80");
            System.setProperty("file.encoding", "UTF-8");
            
            String pid = "pf_rd_m=A1K21FY43GMZF8";
            String url = "https://www.amazon.in/gp/product/B07WHS7MZ1/ref=s9_acss_bw_cg_Budget_2a1_w?"+pid+"&pf_rd_s=merchandised-search-15&pf_rd_r=B81VZR3VX0D6TZNWP1V2&pf_rd_t=101&pf_rd_p=244f6fab-05d0-4a3d-b5e9-e68ba1b7bc08&pf_rd_i=1389401031&th=1";
			// doc = Jsoup.connect("https://www.amazon.in/gp/product/B07WHS7MZ1/ref=s9_acss_bw_cg_Budget_2a1_w?pf_rd_m=A1K21FY43GMZF8&pf_rd_s=merchandised-search-15&pf_rd_r=B81VZR3VX0D6TZNWP1V2&pf_rd_t=101&pf_rd_p=244f6fab-05d0-4a3d-b5e9-e68ba1b7bc08&pf_rd_i=1389401031&th=1").userAgent("Mozilla/17.0").get();
			doc = Jsoup.connect(url).userAgent("Mozilla/17.0").get();
			Elements price = doc.select("span.a-offscreen");
			Elements rating = doc.select("span.a-icon-alt");
			Elements images = doc.getElementsByTag("img");
			
			System.out.println(price.first().text());
			System.out.println(rating.first().text());
			for(Element img:images) {
				System.out.println("src :"+img.attr("src"));
			}
			/*
			int i=0,j=0;
			for(Element price1 : price) {
				i++;
				System.out.println(i+" "+ price1.getElementsByTag("span").first().text());
			
			}// for
					
				for(Element rt: rating) {
				j++;
				System.out.println(j+" "+rt.getElementsByTag("span").first().text());
			}
			*/
		}// try
		catch(IOException e) {
			e.printStackTrace();
		}
			System.out.println("end of main");
	}

}
