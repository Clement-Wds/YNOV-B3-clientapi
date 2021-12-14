package com.ynovb3.client.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ynovb3.client.model.Product;
import com.ynovb3.client.repository.ProductProxy;

@Service
public class ProductService {

	@Autowired
	private ProductProxy productProxy;
	
	public List<Product> getProducts(String token){
		return productProxy.getProducts(token);
	}
	
	public Product getProductById(Integer id, String token) {
		return productProxy.getProductById(id, token);
	}
	
	public void save(Product product, String token) {
		productProxy.save(product, token);
	}
	
}
