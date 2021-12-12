package com.ynovb3.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.ynovb3.client.model.User;
import com.ynovb3.client.service.LoginService;

@Controller
public class PublicController {
	
	@Autowired
	private LoginService loginService;

	@GetMapping("/login")
	public String login() {
		// Creation d'un user en dur qui existe dans la base de données côté API
		// A remplacer par un formulaire
		User user = new User();
		user.setUsername("user");
		user.setPassword("user");
		
		loginService.login(user);
		
		return "logged";
	}
	
}
