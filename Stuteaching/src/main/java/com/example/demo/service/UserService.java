package com.example.demo.service;

import com.example.demo.DTO.UserDTO;

public interface UserService {

	public void Login(String email, String password);

	public void InsertUser(UserDTO user);

	public void DeleteUser(int user_id);

}
