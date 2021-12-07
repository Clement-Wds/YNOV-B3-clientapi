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
	
	public void login(User user) {
		RestTemplate restTemplate = new RestTemplate();

		HttpEntity<User> request = new HttpEntity<>(user, createTokenHeaders());
		
		ResponseEntity<User> response = restTemplate.exchange(
				props.getUrl() + "/login",
				HttpMethod.POST,
				request,
				User.class
				);
		System.out.println("Body reponse : " + response.getBody());
		
		String token = response.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
		System.out.println("Received token is " + token);
		
		props.setToken(token);
	}

}
