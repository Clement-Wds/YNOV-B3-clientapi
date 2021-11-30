package com.ynovb3.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ynovb3.client.model.Product;
import com.ynovb3.client.service.ProductService;

@SpringBootApplication
public class ClientApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}
	
	@Autowired
	private ProductService productService;
	
	@Override
	public void run(String... args) throws Exception {
		
		List<Product> products = productService.getProducts();
		products.stream().forEach(
				(product) -> System.out.println(product.getName())
				);
	}
	

}
