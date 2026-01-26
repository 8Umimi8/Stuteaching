package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.DTO.TimeManageDTO;
import com.example.demo.service.StudyScheduleService;
import com.example.demo.service.security.LoginUserDetails;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/study-time")
public class StudyScheduleController {

	@Autowired
	private StudyScheduleService service;

	// ========================
	// 勉強時間 表示（一覧画面）
	// ========================
	@GetMapping("/display")
	public String display(Model model, @AuthenticationPrincipal LoginUserDetails user) {

		Integer user_id = user.getUser_id();

		List<TimeManageDTO> list = service.DisplayTime(user_id);
		model.addAttribute("timeList", list);

		return "Schedule/study-schedule"; // time.html
	}

	// ========================
	// 勉強時間編集 表示（一覧画面）
	// ========================
	@GetMapping("/add-display")
	public String addDisplay(Model model) {

		model.addAttribute("TimeManageDTO", new TimeManageDTO());

		return "Schedule/study-schedule-insert"; // time.html
	}

	// ========================
	// 勉強時間 登録
	// ========================
	@PostMapping("/insert")
	public String insert(@Valid TimeManageDTO dto, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("errors", bindingResult.getFieldErrors());
			model.addAttribute("TimeManageDTO", dto);
			return "Register/regist";
		}

		service.InsertTime(dto);

		return "redirect:/study-time/display";
	}

	// ========================
	// 勉強時間 更新
	// ========================
	@PostMapping("/update")
	public String update(
			@RequestParam int time_id,
			TimeManageDTO dto) {

		service.UpdateTime(time_id, dto);

		return "redirect:/study-time/display";
	}

	// ========================
	// 勉強時間 削除
	// ========================
	@PostMapping("/delete")
	public String delete(@RequestParam int time_id) {

		service.DeleteTime(time_id);

		return "redirect:/study-time/display";
	}
}
