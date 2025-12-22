package com.example.demo.service.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.entity.LoginEntity;

import lombok.Getter;

@Getter
public class LoginUserDetails implements UserDetails {

	private final Integer user_id;
	private final String email;
	private final String password;
	private final Collection<? extends GrantedAuthority> authorities;

	public LoginUserDetails(LoginEntity entity) {
		this.user_id = entity.getUser_id();
		this.email = entity.getEmail();
		this.password = entity.getPassword();
		this.authorities = List.of(
				new SimpleGrantedAuthority("ROLE_" + entity.getRole()));
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO 自動生成されたメソッド・スタブ
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
