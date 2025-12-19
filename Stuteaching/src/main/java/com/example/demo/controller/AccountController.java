package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.StudentUsersDTO;
import com.example.demo.DTO.TeacherUsersDTO;
import com.example.demo.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private AccountService accountService;

	// ========================
	// 教師情報 表示
	// ========================
	@GetMapping("/teacher/display")
	public TeacherUsersDTO displayTeacher(@RequestParam int user_id) {
		return accountService.DisplayTeacher(user_id);
	}

	// ========================
	// 教師情報 更新
	// ========================
	@PutMapping("/teacher/update")
	public String updateTeacher(
			@RequestParam int user_id,
			@RequestBody TeacherUsersDTO dto) {

		accountService.UpdateTeacher(user_id, dto);
		return "教師情報を更新しました";
	}

	// ========================
	// 生徒情報 表示
	// ========================
	@GetMapping("/student/display")
	public StudentUsersDTO displayStudent(@RequestParam int user_id) {
		return accountService.DisplayStudent(user_id);
	}

	// ========================
	// 生徒情報 更新
	// ========================
	@PutMapping("/student/update")
	public String updateStudent(
			@RequestParam int user_id,
			@RequestBody StudentUsersDTO dto) {

		accountService.UpdateStudent(user_id, dto);
		return "生徒情報を更新しました";
	}
}
