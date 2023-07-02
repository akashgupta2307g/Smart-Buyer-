package com.knit.pricecomarision.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.knit.pricecomarision.model.MinProduct;
import com.knit.pricecomarision.model.Product;
import com.knit.pricecomarision.model.UrlRequest;
import com.knit.pricecomarision.service.ProductService;

@RestController
@RequestMapping("pricecontroler")
public class ProductControler {
	
	@Autowired
	ProductService productService;
	
//@CrossOrigin("*")
@CrossOrigin(origins = "http://localhost:8000")
@GetMapping("/getproductPrice/{id}")	
public Product getProduct(@PathVariable String id) {
	
	Product product = productService.getProduct(id);
	return product;
	
	
}

@CrossOrigin("*")
@PostMapping("/getproductMinPrice")
public MinProduct getProduct1(@RequestBody UrlRequest prod_name) {
	
MinProduct product = productService.getBestProduct(prod_name.getUrl());
return product;
}

}
