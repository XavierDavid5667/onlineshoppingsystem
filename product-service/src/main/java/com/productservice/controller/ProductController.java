package com.productservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.productservice.dto.ProductRequest;
import com.productservice.dto.ProductResponse;
import com.productservice.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {
	
	private final ProductService productService;
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public void createProduct(@RequestBody ProductRequest product) {
		productService.createProduct(product);
	}
	
	@GetMapping
	@ResponseStatus(value =HttpStatus.OK)
	public List<ProductResponse>getAllProducts(){
		return productService.getAllProducts();
	}
	
	

}
