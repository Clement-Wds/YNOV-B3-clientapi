package com.ynovb3.client.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.ynovb3.client.ApiProperties;
import com.ynovb3.client.model.Product;

@Component
public class ProductProxy {
	
	@Autowired
	private ApiProperties props;
	
	public List<Product> getProducts(){
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<List<Product>> response = restTemplate.exchange(props.getUrl() + "/product", HttpMethod.GET, null, new ParameterizedTypeReference<List<Product>>() {});
		
		return response.getBody();
	}

}
