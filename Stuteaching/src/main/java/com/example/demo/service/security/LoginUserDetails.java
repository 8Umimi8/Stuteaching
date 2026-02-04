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
	//ユーザーの権限情報
	private final Collection<? extends GrantedAuthority> authorities;

	//Entity から Security 用の UserDetails を生成する
	public LoginUserDetails(LoginEntity entity) {
		this.user_id = entity.getUserId();
		this.email = entity.getEmail();
		this.password = entity.getPassword();
		String roleName = switch (entity.getRole()) {
		case 1 -> "ROLE_USER";
		case 2 -> "ROLE_ADMIN";
		default -> "ROLE_USER";
		};

		this.authorities = List.of(new SimpleGrantedAuthority(roleName));
	}

	//ユーザーが持つ権限情報を返す
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO 自動生成されたメソッド・スタブ
		return authorities;
	}

	public Integer getUserId() {
		return user_id;
	}

	//認証に使用するパスワードを返す
	@Override
	public String getPassword() {
		return password;
	}

	//認証に使用するメールアドレスを返す
	@Override
	public String getUsername() {
		return email;
	}

	//-----以下は未使用状態-----

	//アカウントの有効期限チェック（常に有効）
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	//アカウントのロック状態チェック（常に未ロック）
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	//資格情報の有効期限チェック（常に有効）
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	//アカウントの有効／無効チェック（常に有効）
	// アカウント無効化機能を実装する場合に使用
	@Override
	public boolean isEnabled() {
		return true;
	}

}
