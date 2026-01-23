package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.DTO.UserDTO;
import com.example.demo.service.UserService;
import com.example.demo.service.security.LoginUserDetails;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	// ========================
	// 新規会員登録表示
	// ========================
	@GetMapping("/login")
	public String showLogin(Model model) {
		model.addAttribute("UserDTO", new UserDTO());
		log.info("ログイン");
		return "Register/regist";
	};

	// ========================
	// プライバシーポリシー表示
	// ========================
	@GetMapping("/agreement")
	public String showAgreement() {
		log.info("プライバシーポリシー");
		return "Register/agreement";
	};

	// ========================
	// 会員登録完了表示
	// ========================
	@GetMapping("/compleat")
	public String showCompleat() {
		log.info("登録完了");
		return "Register/regist-compleat";
	};

	// ========================
	// 会員登録
	// ========================
	@PostMapping("/insert")
	public String insert(@Valid UserDTO user, BindingResult bindingResult, Model model) {
		log.info("登録");
		if (bindingResult.hasErrors()) {
			model.addAttribute("errors", bindingResult.getFieldErrors());
			model.addAttribute("UserDTO", user);
			log.info("errors:{}", bindingResult.getFieldErrors());
			return "Register/regist";
		}

		userService.InsertUser(user);

		// 登録完了後 → ログイン画面へ

		return "redirect:/user/agreement";

	}

	// ========================
	// 退会（ユーザー削除）
	// ========================
	@PostMapping("/delete")
	public String delete(@AuthenticationPrincipal LoginUserDetails user) {

		Integer user_id = user.getUser_id();

		userService.DeleteUser(user_id);

		// 削除後 → トップへ
		return "login";
	}
}
