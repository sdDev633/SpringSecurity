package com.example.spring_security_6.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class ProductController {
	private record Product(Integer id, String name, double price) {}
	
	List<Product> products = new ArrayList<>(
			List.of(new Product(1, "product", 120), new Product(2, "product2", 120), new Product(3, "product3", 120))
			);
	
	@GetMapping("/")
	public List<Product> getAllProducts(){
		return products;
	}
	
	@PostMapping("/product")
	public Product saveProduct(@RequestBody Product product) {
		products.add(product);
		return product;
	}
	
	@GetMapping("/csrf")
	public CsrfToken getToken(HttpServletRequest request) {
		return (CsrfToken) request.getAttribute("_csrf");
	}
	
}
