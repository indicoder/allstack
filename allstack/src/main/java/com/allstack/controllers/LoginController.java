package com.allstack.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("/auth")
public class LoginController {
	
	//RequestParam annotation binds the method param to the HTTP field
	@RequestMapping(value = "/login", method = RequestMethod.GET)	
	public String login(@RequestParam(value="username") String username, @RequestParam(value="password") String password){
		return "Hello World";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)	
	public String signup(HttpSession httpSession, @RequestParam(value="username") String username, @RequestParam(value="password") String password){
		//httpSession.setAttribute("user", user);
		return null;
	}
	
	@RequestMapping(value = "/testPost", method = RequestMethod.POST)	
	public String testPost(HttpSession httpSession, @RequestParam(value="username") String username){
		//httpSession.setAttribute("user", user);
		return "Post Working";
	}
}
