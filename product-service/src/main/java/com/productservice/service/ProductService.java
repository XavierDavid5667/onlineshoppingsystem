package com.productservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.productservice.dto.ProductRequest;
import com.productservice.dto.ProductResponse;
import com.productservice.model.Product;
import com.productservice.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

	private final ProductRepository productRepository;


	public void createProduct(ProductRequest productRequest) {

		Product product = Product.builder().name(productRequest.getName()).description(productRequest.getDescription())
				.price(productRequest.getPrice()).build();
		productRepository.save(product);
		log.info("Product {} is saved",product.getId());
	}

	public List<ProductResponse> getAllProducts() {
		List<Product> products = productRepository.findAll();
		System.out.println(products);
       List<ProductResponse> list = products.stream().map(p-> new ProductResponse(p.getId(),p.getName(), p.getDescription(), p.getPrice())).toList();
       return list;
	}

}
