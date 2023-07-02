
    var pageUrl = window.location.href;
	ckecomerce(pageUrl);
	
	function ckecomerce(pageUrl){
		if(pageUrl.includes ("www.amazon.in")){
			
			var linkWithUrl=null;
			if(pageUrl.includes("dp/")&&!pageUrl.includes("/ref=sr_1_1?")){
	    	         linkWithUrl= pageUrl.split("dp/")[1];
	   	     
	    	        linkWithUrl= linkWithUrl.split("?")[0];
	    	        	}else{ if(pageUrl.includes("/ref=sr_1_1?")){
	   	    	             linkWithUrl= pageUrl.split("dp/")[1];
	   		    	         linkWithUrl= linkWithUrl.split("/ref=sr_1_1?");
	    	           	    	            	
	    	        	 	}
	    	        	}
			
			        if(linkWithUrl != null){
						cklocaldata(linkWithUrl);
					}

		}
		if(pageUrl.includes ("www.flipkart.com")){

               	if(pageUrl.includes("?pid=")){
			
   		         var linkWithUrl=pageUrl.split("pid=")[1];
				 linkWithUrl=linkWithUrl.split("&")[0];
				 if(linkWithUrl != null){
					 cklocaldata(linkWithUrl);
		        }

	       }

      }
      if(pageUrl.includes ("www.tatacliq.com")){

                     	if(pageUrl.includes("/p-mp")){
                            if(pageUrl.includes("?")){
                                var linkWithUrl=pageUrl.split("?")[0];
                            }
      				        if(linkWithUrl != null){
      					        cklocaldata(linkWithUrl);
      		                 }

      	                }

            }
            if(pageUrl.includes ("www.croma.com")){

                                 	if(pageUrl.includes("")){

                  				        if(pageUrl != null){
                  					        cklocaldata(pageUrl);
                  		                 }

                  	                }

                        }

                        if(pageUrl.includes ("www.reliancedigital.in")){

                                                         	if(pageUrl.includes("/p/")){

                                          				        if(pageUrl != null){
                                          					        cklocaldata(pageUrl);
                                          		                 }

                                          	                }

                                                }



}

    
	
	function cklocaldata(vendor_name){
		
		redirect(pageUrl, vendor_name);
	}

function redirect(page_url,storage_key){

		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {

            var response = this.responseText;
			//prsing JSON response
			var obj = JSON.parse(response);
			var price=obj.price;
			var prod_url=obj.prod_url;
			var prod_name=obj.prod_name;
			
            if(prod_name!=null){
					productMatched(price,prod_url,prod_name);
				}else{
					
					productNotMatched(price,prod_url,prod_name);
				}
			}
   };
  
      xhttp.open("POST", "http://localhost:9988/pricecontroler/getproductMinPrice", true);
	  xhttp.setRequestHeader("Content-type", "application/json");

      xhttp.send(JSON.stringify({ "url" : page_url  }));


}

	function productMatched(price,url,prod_name) {
			alertify.set({ delay: 1050000 });
			alertify.log("<div class='row gutter'><div class='' style='height:142px; width:70%!important; float:left; color: rgb(255, 255, 255);text-shadow: rgba(0, 0, 0, 0.5) -1px -1px 0px;background: rgba(0, 0, 0, 0.9);border-radius: 4px;padding: 0px !important;'>"+"<div class='lpTop'>Lowest Price Availiable</div>"+"<div class='proName'>"+prod_name+"</div>"+"<div class='proPrice'>Rs."+ price +"</div>"+"<a target='blank' href='"+url+"'class='getBttn'>Get It Now </a><div class='powBy'>Powered by : SmartBuyer</div></div>"+"<a style='width: 30%important;float: left; position:absolute; right:0;height:142px;' class='lpBG' href='"+url+"' target='blank'><div class='lpTxt'>Lowest Price"+"</div></a></div>");
			return false;
		}
			
	function productNotMatched(price,url,prod_name) {
			alertify.set({ delay: 1050000 });

			var home_url="http://localhost:8000/home";
			alertify.log("<div class='row gutter'> <div class='' style='text-align: center;height:auto; width:100%!important; float:left; color: rgb(255, 255, 255);text-shadow: rgba(0, 0, 0, 0.5) -1px -1px 0px;background: rgba(0, 0, 0, 0.9);border-radius: 4px;padding: 0px !important;'> "+"<div class='lpTop'>SmartBuyer.COM</div> "+"<div class=''>"+ "No Mapping available" +"</div> "+"<a target='blank' href='"+home_url+"'class='getBttn'>Get It Now </a> <div class='powBy'>Powered by : SmartBuyer</div> </div> </div>");
			return false;
		}