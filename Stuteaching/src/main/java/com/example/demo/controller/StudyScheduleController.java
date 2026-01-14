package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.DTO.TimeManageDTO;
import com.example.demo.service.StudyScheduleService;

@Controller
@RequestMapping("/study-time")
public class StudyScheduleController {

	@Autowired
	private StudyScheduleService service;

	// ========================
	// 勉強時間 表示（一覧画面）
	// ========================
	@GetMapping("/display")
	public String display(
			@RequestParam int user_id,
			Model model) {

		List<TimeManageDTO> list = service.DisplayTime(user_id);
		model.addAttribute("timeList", list);

		return "study-schedule"; // time.html
	}

	// ========================
	// 勉強時間 登録
	// ========================
	@PostMapping("/insert")
	public String insert(TimeManageDTO dto) {

		service.InsertTime(dto);

		return "redirect:/study-time/display?user_id=1";
	}

	// ========================
	// 勉強時間 更新
	// ========================
	@PostMapping("/update")
	public String update(
			@RequestParam int time_id,
			TimeManageDTO dto) {

		service.UpdateTime(time_id, dto);

		return "redirect:/study-time/display?user_id=1";
	}

	// ========================
	// 勉強時間 削除
	// ========================
	@PostMapping("/delete")
	public String delete(@RequestParam int time_id) {

		service.DeleteTime(time_id);

		return "redirect:/study-time/display?user_id=1";
	}
}
