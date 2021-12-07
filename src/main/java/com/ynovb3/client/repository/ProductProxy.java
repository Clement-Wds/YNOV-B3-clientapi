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
	
	private String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlzcyI6ImNvbS5vcGVuY2xhc3Nyb29tcyIsImlhdCI6MTYzODg5MjMzMywiZXhwIjoxNjM5NDk3MTMzfQ.32iMp0r97o0XLL8-261PJHDDBGfLSItPcJOyfwLuYzdRzmF2LmDZcdGxNbPbLS03wcczzHmhTlBgv419ZBXdyw";
	
	private HttpHeaders createHeaders(String username, String password) {
        
        return new HttpHeaders() {{
            String auth = username + ":" + password;
            byte[] encodeAuth = Base64.encodeBase64(
                    auth.getBytes(Charset.forName("US-ASCII")) );
            String authHeader = "Basic " + new String(encodeAuth );
            set("Authorization", authHeader);
        }};
    }
	
	private HttpHeaders createTokenHeaders() {
		return new HttpHeaders() {{
			String authHeader = "Bearer " + token;
			set("Authorization", authHeader);
		}};
	}
	
	public List<Product> getProducts(){
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<List<Product>> response = restTemplate.exchange(
				props.getUrl() + "/product",
				HttpMethod.GET,
				new HttpEntity<>(createTokenHeaders()),
				new ParameterizedTypeReference<List<Product>>() {}
				);
		
		return response.getBody();
	}
	
	public Product getProductById(Integer id){
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<Product> response = restTemplate.exchange(
				props.getUrl() + "/product/" + id,
				HttpMethod.GET,
				new HttpEntity<>(createTokenHeaders()),
				Product.class
				);
		
		return response.getBody();
		
	}
	
	public void save(Product product) {
		RestTemplate restTemplate = new RestTemplate();

		HttpEntity<Product> request = new HttpEntity<>(product, createTokenHeaders());
		
		ResponseEntity<Product> response = restTemplate.exchange(
				props.getUrl() + "/product",
				HttpMethod.POST,
				request,
				Product.class
				);
	}

}
