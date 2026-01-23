package com.example.demo.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.UserDTO;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.LoginRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.util.UtilService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private LoginRepository loginrepository;

	@Autowired
	private UserRepository userrepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void InsertUser(UserDTO user) {

		// ===== DTO → Entity 変換 =====
		UserEntity userEntity = new UserEntity();
		UtilService.ConvertEntity(user, userEntity);

		// ===== パスワードをハッシュ化 =====
		String rawPassword = userEntity.getPassword();
		String encodedPassword = passwordEncoder.encode(rawPassword);
		userEntity.setPassword(encodedPassword);

		// ===== DB保存 =====
		userrepository.save(userEntity);

	}

	@Override
	public void DeleteUser(int user_id) {
		userrepository.deleteById(user_id);

	}

	//今回は使わない
	@Override
	public void Login(String email, String password) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
