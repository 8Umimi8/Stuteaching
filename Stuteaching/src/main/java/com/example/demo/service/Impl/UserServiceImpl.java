package com.example.demo.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Override
	public void Login(String email, String password) {

	}

	@Override
	public void InsertUser(UserDTO user) {
		UserEntity userEntity = new UserEntity();
		UtilService.ConvertEntity(user, userEntity);
		userrepository.save(userEntity);

	}

	@Override
	public void DeleteUser(int user_id) {
		userrepository.deleteById(user_id);

	}

}
