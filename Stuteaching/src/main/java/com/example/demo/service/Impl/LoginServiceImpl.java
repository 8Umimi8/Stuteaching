package com.example.demo.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.LoginEntity;
import com.example.demo.repository.LoginRepository;
import com.example.demo.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginRepository loginRepository;

	/**
	 * メールアドレスでユーザー情報を取得する
	 * 存在しない場合は例外を投げる
	 */
	@Override
	public LoginEntity findByEmail(String email) {
		return loginRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("ユーザーが存在しません"));
	}

}
