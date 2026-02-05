package com.example.demo.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.DTO.UserDTO;
import com.example.demo.entity.TeacherEntity;
import com.example.demo.service.AccountService;
import com.example.demo.service.UserService; // ← 忘れずに追加

@Controller
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private UserService userService; // ← 追加（User情報取得用）

	@GetMapping("/profile")
	public String showProfile(Model model, Principal principal) {
		// ログイン中のユーザー情報を取得
		UserDTO user = accountService.findByEmail(principal.getName());
		model.addAttribute("user", user);

		// teacherをあらかじめnullで登録（テンプレート安全対策）
		TeacherEntity teacher = null;

		// ロールが2（先生）の場合のみteacher情報を取得
		if (user.getRole() == 2) {
			teacher = accountService.findByUserId(user.getUserId());
		}

		model.addAttribute("teacher", teacher); // ← 常に追加（null可）

		return "Account/profile";
	}

	@GetMapping("/profile/edit")
	public String editProfile(Model model, Principal principal) {
		UserDTO user = accountService.findByEmail(principal.getName());
		model.addAttribute("user", user);

		// 先生の場合、教科一覧と担当教科をセット
		if (user.getRole() == 2) {
			model.addAttribute("subjects", accountService.findAll());
			TeacherEntity teacher = accountService.findByUserId(user.getUserId());
			model.addAttribute("teacher", teacher);
		}

		return "Account/edit";
	}

	@PostMapping("/profile/update")
	public String updateProfile(@ModelAttribute("user") UserDTO user, Principal principal) {
		accountService.updateUser(user, principal.getName());
		return "redirect:/account/profile";
	}

}
