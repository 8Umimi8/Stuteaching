package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.TimeManageDTO;
import com.example.demo.service.StudyScheduleService;

@RestController
@RequestMapping("/study-time")
public class StudyScheduleController {

	@Autowired
	private StudyScheduleService service;

	// ========================
	// 勉強時間 登録
	// ========================
	@PostMapping("/insert")
	public String insert(@RequestBody TimeManageDTO dto) {
		service.InsertTime(dto);
		return "勉強時間を登録しました";
	}

	// ========================
	// 勉強時間 更新
	// ========================
	@PutMapping("/update")
	public String update(
			@RequestParam int time_id,
			@RequestBody TimeManageDTO dto) {

		service.UpdateTime(time_id, dto);
		return "勉強時間を更新しました";
	}

	// ========================
	// 勉強時間 削除
	// ========================
	@DeleteMapping("/delete")
	public String delete(@RequestParam int time_id) {
		service.DeleteTime(time_id);
		return "勉強時間を削除しました";
	}

	// ========================
	// 勉強時間 表示（ユーザー別）
	// ========================
	@GetMapping("/display")
	public List<TimeManageDTO> display(@RequestParam int user_id) {
		return service.DisplayTime(user_id);
	}
}
