package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.UserDTO;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	// ========================
	// ログイン
	// ========================
	@PostMapping("/login")
	public String login(
			@RequestParam String email,
			@RequestParam String password) {

		userService.Login(email, password);
		return "ログイン処理を実行しました";
	}

	// ========================
	// 会員登録
	// ========================
	@PostMapping("/insert")
	public String insert(@RequestBody UserDTO user) {

		userService.InsertUser(user);
		return "ユーザー登録が完了しました";
	}

	// ========================
	// 退会（ユーザー削除）
	// ========================
	@DeleteMapping("/delete")
	public String delete(@RequestParam int user_id) {

		userService.DeleteUser(user_id);
		return "ユーザーを削除しました";
	}
}
