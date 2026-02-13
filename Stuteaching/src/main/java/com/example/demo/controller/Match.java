package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.AccountService;
import com.example.demo.web.dto.TeacherUsersResponseDTO;

@Controller
public class Match {

	@Autowired
	private AccountService accountService;

	@GetMapping("/match")
	public String showMatchPage(Model model) {

		List<TeacherUsersResponseDTO> users = accountService.getAllTeachers();
		model.addAttribute("users", users);

		model.addAttribute("activeMenu", "match"); // ← ここが重要

		return "match/teacher-list";
	}

}
