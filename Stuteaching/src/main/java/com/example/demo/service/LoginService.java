package com.example.demo.service;

import com.example.demo.entity.LoginEntity;

public interface LoginService {

	//メールアドレスでユーザー情報を取得する
	LoginEntity findByEmail(String email);

}
