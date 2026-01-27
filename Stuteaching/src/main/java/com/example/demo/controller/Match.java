package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.DTO.TeacherUsersDTO;
import com.example.demo.service.AccountService;

@Controller
public class Match {

	@Autowired
	private AccountService accountService;

	@GetMapping("/match")
	public String showMatchPage(Model model) {

		List<TeacherUsersDTO> users = accountService.getAllTeachers();
		model.addAttribute("users", users);

		return "/match/teacher-list"; // match.html
	}
}
