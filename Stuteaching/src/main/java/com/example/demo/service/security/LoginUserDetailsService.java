package com.example.demo.service.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.service.LoginService;

@Service
public class LoginUserDetailsService implements UserDetailsService {

	private final LoginService loginService;

	public LoginUserDetailsService(LoginService loginService) {
		this.loginService = loginService;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return new LoginUserDetails(loginService.findByEmail(email));
	}

}
