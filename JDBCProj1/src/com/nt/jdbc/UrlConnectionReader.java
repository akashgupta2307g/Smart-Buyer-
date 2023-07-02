package com.nt.jdbc;  
public class UrlConnectionReader  
{  
  public static void main(String[] args)  
  {  
    String output  = "https://www.flipkart.com/samsung-galaxy-f23-5g-forest-green-128-gb/p/itm4001e68fda319?pid=MOBGBKQF3QM4GHWN&lid=LSTMOBGBKQF3QM4GHWNEXFBQE&marketplace=FLIPKART&store=tyy%2F4io&srno=b_1_1&otracker=hp_bannerads_1_3.bannerAdCard.BANNERADS_Mob_HPW_Samsung%2BF23-sale%2Bis%2Bon_L63NDKAZVR5I&fm=organic&iid=5b93014c-1877-4b90-bc1f-85e911f8baca.MOBGBKQF3QM4GHWN.SEARCH&ppt=hp&ppn=homepage&ssid=7vkb6c2tf40000001647678734986";  
    if(output.contains("www.flipkart.com")) {
    	
    	String[] splittedString =output.split("pid=");
    	if(splittedString.length>=2) {
    		String idString = splittedString[1];
    		String id = idString.substring(0,idString.indexOf("&"));
    		System.out.println(id);    		
    	}
    }
    String output1 ="https://www.amazon.in/dp/B0844592FT/ref=sspa_dk_detail_2?psc=1&pd_rd_i=B09P7G7Y95&pd_rd_w=r8aH2&pf_rd_p=4e9225d2-7473-4eb0-95d5-670190275218&pd_rd_wg=GmlwW&pf_rd_r=NQTB8AGNJ8Q9DMXD76BF&pd_rd_r=6e9b84b0-5ab9-497c-b0e3-f09c416ebcbc&s=electronics&spLa=ZW5jcnlwdGVkUXVhbGlmaWVyPUExODdMMklZOUlHRDZNJmVuY3J5cHRlZElkPUEwODk0NDQ5VkRIUk5CRk45VkE5JmVuY3J5cHRlZEFkSWQ9QTAxNTYwMzIyN1RWN0tJTTBVOFNEJndpZGdldE5hbWU9c3BfZGV0YWlsJmFjdGlvbj1jbGlja1JlZGlyZWN0JmRvTm90TG9nQ2xpY2s9dHJ1ZQ==";
    if(output1.contains("www.amazon.in")) {
    	
    	String[] splitteStrings =output1.split("/dp/");
    	if(splitteStrings.length>=2) {
    		String idString = splitteStrings[1];
    		String id = idString.substring(0,idString.indexOf("/"));
    		System.out.println(id);
    		
    	}
    }
    
    String output2 = "https://www.reliancedigital.in/samsung-galaxy-m52-128-gb-6-gb-ram-icy-blue-mobile-phone/p/491998466";
    if(output2.contains("www.reliancedigital.in")){
    	
    	String[] splitteStrings = output2.split("/p/");
    	
    		String idString = splitteStrings[1];
    		String id = idString;
    		System.out.println(id);
    		
     }
    String output3 = "https://www.croma.com/redmi-note-10t-5g-64gb-rom-4gb-ram-mzb09dyin-graphite-black-/p/241196";
    if(output3.contains("www.croma.com")) {
    	
    	String[] splitteStrings = output3.split("/p/");
    	
    	String idString = splitteStrings[1];
    	String id = idString;
    	System.out.println(id);
    }
    	
    String output4 = "https://www.tatacliq.com/samsung-galaxy-m32-128-gb-sky-blue-6-gb-ram-dual-sim-5g/p-mp000000010825084?sessionId=17d77cc8-8268-4cf3-914f-95f3a3a3c9e0";
    if(output4.contains("www.tatacliq.com"));{
    	
    	String[] splitteStrings = output4.split("g/");
    	if(splitteStrings.length>=2) {
    		String idString = splitteStrings[1];
    		String id = idString.substring(0,idString.indexOf("?"));
    		System.out.println(id);
    		
    	}
    }
  }  
  
  
}  