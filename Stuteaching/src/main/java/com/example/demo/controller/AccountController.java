package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.DTO.StudentUsersDTO;
import com.example.demo.service.AccountService;
import com.example.demo.web.dto.TeacherUsersResponseDTO;

@Controller
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private AccountService accountService;

	// ========================
	// 教師情報 表示
	// ========================
	@GetMapping("/teacher")
	public String displayTeacher(
			@RequestParam int user_id,
			Model model) {

		TeacherUsersResponseDTO teacher = accountService.DisplayTeacher(user_id);
		model.addAttribute("teacher", teacher);

		return "profile"; // teacher_account.html
	}

	// ========================
	// 教師情報 更新
	// ========================
	//	@PostMapping("/teacher/update")
	//	public String updateTeacher(
	//			@RequestParam int user_id,
	//			TeacherUsersDTO dto) {
	//
	//		accountService.UpdateTeacher(user_id, dto);
	//
	//		return "redirect:/account/teacher?user_id=" + user_id;
	//	}

	// ========================
	// 生徒情報 表示
	// ========================
	@GetMapping("/student")
	public String displayStudent(
			@RequestParam int user_id,
			Model model) {

		StudentUsersDTO student = accountService.DisplayStudent(user_id);
		model.addAttribute("student", student);

		return "profile"; // student_account.html
	}

	// ========================
	// 生徒情報 更新
	// ========================
	//	@PostMapping("/student/update")
	//	public String updateStudent(
	//			@RequestParam int user_id,
	//			StudentUsersDTO dto) {
	//
	//		accountService.UpdateStudent(user_id, dto);
	//
	//		return "redirect:/account/student?user_id=" + user_id;
	//	}
}
