package com.ynovb3.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ynovb3.client.model.User;
import com.ynovb3.client.repository.LoginProxy;

@Service
public class LoginService {

	@Autowired
	private LoginProxy loginProxy;
	
	public String login(User user) {
		return loginProxy.login(user);
	}
	
}
