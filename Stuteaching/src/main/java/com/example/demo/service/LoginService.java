package com.example.demo.service;

import com.example.demo.entity.LoginEntity;

public interface LoginService {

	LoginEntity findByEmail(String email);

}
