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

	@Override
	public LoginEntity findByEmail(String email) {
		return loginRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("ユーザーが存在しません"));
	}

}
