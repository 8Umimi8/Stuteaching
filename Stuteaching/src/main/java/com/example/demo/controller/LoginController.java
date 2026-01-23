package com.example.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.security.LoginUserDetails;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String loginPage() {
		return "login"; // login.html
	}

	@GetMapping("/home")
	public String home(@AuthenticationPrincipal LoginUserDetails user) {
		Integer userId = user.getUser_id();
		System.out.println("ログイン中の user_id = " + userId);
		return "/Match/teacher-list"; // home.html
	}
}