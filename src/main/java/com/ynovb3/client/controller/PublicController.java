package com.ynovb3.client.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.ynovb3.client.TokenContext;
import com.ynovb3.client.model.User;
import com.ynovb3.client.service.LoginService;

@Controller
public class PublicController {
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private TokenContext tokenContext;

	@GetMapping("/login")
	public String login(HttpSession session) {
		// Creation d'un user en dur qui existe dans la base de données côté API
		// A remplacer par un formulaire
		User user = new User();
		user.setUsername("admin");
		user.setPassword("admin");
		
		String token = loginService.login(user);
		session.setAttribute("loggeduser", user.getUsername());
		session.setAttribute("token", token); //possibily 1
		tokenContext.setToken(token); //possibility 2 - optimized
		
		return "logged";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		tokenContext.setToken(null);
		session.invalidate();
		return "logout";
		
	}
	
}
