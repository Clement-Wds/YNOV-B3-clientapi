package com.ynovb3.client.repository;

import java.nio.charset.Charset;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
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
	
	private HttpHeaders createHeaders(String username, String password) {
        
        return new HttpHeaders() {{
            String auth = username + ":" + password;
            byte[] encodeAuth = Base64.encodeBase64(
                    auth.getBytes(Charset.forName("US-ASCII")) );
            String authHeader = "Basic " + new String(encodeAuth );
            set("Authorization", authHeader);
        }};
    }
	
	public List<Product> getProducts(){
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<List<Product>> response = restTemplate.exchange(
				props.getUrl() + "/product",
				HttpMethod.GET,
				new HttpEntity<>(createHeaders("admin", "1234")),
				new ParameterizedTypeReference<List<Product>>() {}
				);
		
		return response.getBody();
	}
	
	public Product getProductById(Integer id){
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<Product> response = restTemplate.exchange(
				props.getUrl() + "/product/" + id,
				HttpMethod.GET,
				null,
				Product.class
				);
		
		return response.getBody();
		
	}
	
	public void save(Product product) {
		RestTemplate restTemplate = new RestTemplate();

		HttpEntity<Product> request = new HttpEntity<>(product);
		
		ResponseEntity<Product> response = restTemplate.exchange(
				props.getUrl() + "/product",
				HttpMethod.POST,
				request,
				Product.class
				);
	}

}
