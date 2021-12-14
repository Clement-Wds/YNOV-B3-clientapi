package com.ynovb3.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ynovb3.client.TokenContext;
import com.ynovb3.client.model.ApiUser;
import com.ynovb3.client.repository.LoginProxy;

@Service
public class LoginService {

	@Autowired
	private TokenContext tokenContext;
	
	@Autowired
	private LoginProxy loginProxy;
	
	public void login(ApiUser user) {
		String token = loginProxy.login(user);
		tokenContext.setToken(token);
	}
	
}
