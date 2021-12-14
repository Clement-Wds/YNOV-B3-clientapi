package com.ynovb3.client.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.ynovb3.client.ApiProperties;
import com.ynovb3.client.model.Product;
import com.ynovb3.client.model.User;

@Repository
public class LoginProxy {
	
	@Autowired
	private ApiProperties props;
	
	public String login(User user) {
		RestTemplate restTemplate = new RestTemplate();

		HttpEntity<User> request = new HttpEntity<>(user);
		
		ResponseEntity<String> response = restTemplate.exchange(
				props.getPublicurl() + "/login",
				HttpMethod.POST,
				request,
				String.class				
				);
		System.out.println("Body response : " + response.getBody());
		
		// Extraction du token du header de la réponse
		String token = response.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
		System.out.println("Received token is " + token);
		
		// Ajout du token dans la classe ApiProperties, comme ça il sera accessible dans les autres proxy.
		props.setToken(token);
		
		return token;
	}

}
