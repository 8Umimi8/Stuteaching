package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.service.security.LoginUserDetailsService;

@Configuration
public class SecurityConfig {

	@Autowired
	private LoginUserDetailsService userDetailsService;

	/**
	 * Spring Security の全体設定
	 * ・URLごとの認可
	 * ・ログイン画面
	 * ・ログアウト処理
	 */
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/login", "/user/login", "/user/insert").permitAll()
						.anyRequest().authenticated())
				.formLogin(login -> login
						.loginPage("/login")
						.loginProcessingUrl("/login")
						.usernameParameter("email")
						.passwordParameter("password")
						.defaultSuccessUrl("/Match/teacher-list", true)
						.failureUrl("/login?error"))
				.logout(logout -> logout
						.logoutSuccessUrl("/login?logout"));

		return http.build();
	}

	//パスワードの暗号化方式を定義（BCrypt）
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	//Spring Security に UserDetailsService を登録する
	@Bean
	public UserDetailsService userDetailsService() {
		return userDetailsService;
	}

}
