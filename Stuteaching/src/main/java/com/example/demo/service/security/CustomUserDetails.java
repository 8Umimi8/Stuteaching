package com.example.demo.service.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.entity.UserEntity;

public class CustomUserDetails implements UserDetails {

	private final UserEntity user;

	public CustomUserDetails(UserEntity user) {
		this.user = user;
	}

	// ★ ここで Entity の role をそのまま返せる
	public Integer getRole() {
		return user.getRole(); // 1=先生, 2=生徒
	}

	public String getUserName() {
		return user.getUser_name();
	}

	public Integer getUserId() {
		return user.getUser_id();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	@Override
	public String getPassword() {
		return user.getPassword();
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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// 数値ロールを Spring 権限に変換して返す
		String roleName = switch (user.getRole()) {
		case 2 -> "ROLE_TEACHER";
		case 1 -> "ROLE_STUDENT";
		default -> "ROLE_USER";
		};

		return List.of((GrantedAuthority) () -> roleName);
	}

}