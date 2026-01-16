package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.DTO.UserDTO;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	// ========================
	// 表示
	// ========================
	@GetMapping("/login")
	public String showLogin() {
		log.info("ログイン");
		return "Register/regist";
	};

	// ========================
	// 会員登録
	// ========================
	@PostMapping("/insert")
	public String insert(@Valid @RequestBody UserDTO user, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("errors", bindingResult.getFieldErrors());
			model.addAttribute("UserDTO", user);
			return "Register/regist";
		}

		userService.InsertUser(user);

		// 登録完了後 → ログイン画面へ
		return "Register/agreement";
	}

	// ========================
	// 退会（ユーザー削除）
	// ========================
	@DeleteMapping("/delete")
	public String delete(@RequestParam int user_id) {

		userService.DeleteUser(user_id);

		// 削除後 → トップへ
		return "regist";
	}
}
