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
	// 新規会員登録表示
	// ========================
	@GetMapping("/login")
	public String showLogin() {
		log.info("ログイン");
		return "Register/regist";
	};

	// ========================
	// 会員登録完了表示
	// ========================
	@GetMapping("/compleat")
	public String showCompleat() {
		log.info("登録完了");
		return "Register/regist-compleat";
	};

	@GetMapping("/agreement")
	public String showAgreement() {
		log.info("プライバシーポリシー");
		return "Register/agreement";
	};

	// ========================
	// 会員登録
	// ========================
	@PostMapping("/insert")
<<<<<<< HEAD
<<<<<<< Updated upstream
	public String insert(UserDTO user) {
=======
=======
>>>>>>> feature/コントローラー作り３
	public String insert(@Valid @RequestBody UserDTO user, BindingResult bindingResult, Model model) {
		log.info("登録");
		if (bindingResult.hasErrors()) {
			model.addAttribute("errors", bindingResult.getFieldErrors());
			model.addAttribute("UserDTO", user);
			log.info("errors:{}", bindingResult.getFieldErrors());
			return "Register/regist";
		}
<<<<<<< HEAD
>>>>>>> Stashed changes
=======
>>>>>>> feature/コントローラー作り３

		userService.InsertUser(user);

		// 登録完了後 → ログイン画面へ
<<<<<<< HEAD
<<<<<<< Updated upstream
		return "login";
=======
		return "redirect:/agreement";
>>>>>>> Stashed changes
=======
		return "redirect:/agreement";
>>>>>>> feature/コントローラー作り３
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
