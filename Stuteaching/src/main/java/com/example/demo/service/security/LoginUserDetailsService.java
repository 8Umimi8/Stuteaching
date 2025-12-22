package com.example.demo.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.service.LoginService;

@Service
public class LoginUserDetailsService implements UserDetailsService {

	@Autowired
	private LoginService loginService;

	/**
	 * Spring Security がログイン時に自動で呼び出すメソッド
	 * メールアドレスを使ってユーザー情報を取得し、UserDetails として返す
	 */
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return new LoginUserDetails(loginService.findByEmail(email));
	}

}
