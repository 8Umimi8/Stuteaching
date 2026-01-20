package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.DTO.UserDTO;
import com.example.demo.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	// ========================
	// 新規会員登録表示
	// ========================
	@GetMapping("/login")
	public String display() {
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
<<<<<<< Updated upstream
	public String insert(UserDTO user) {
=======
	public String insert(@Valid @RequestBody UserDTO user, BindingResult bindingResult, Model model) {
		log.info("登録");
		if (bindingResult.hasErrors()) {
			model.addAttribute("errors", bindingResult.getFieldErrors());
			model.addAttribute("UserDTO", user);
			log.info("errors:{}", bindingResult.getFieldErrors());
			return "Register/regist";
		}
>>>>>>> Stashed changes

		userService.InsertUser(user);

		// 登録完了後 → ログイン画面へ
<<<<<<< Updated upstream
		return "login";
=======
		return "redirect:/agreement";
>>>>>>> Stashed changes
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
