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

import com.example.demo.entity.TimeEntity;
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
	public String insert(@RequestBody TimeEntity entity) {
		service.insert(entity);
		return "勉強時間を登録しました";
	}

	// ========================
	// 勉強時間 更新
	// ========================
	@PutMapping("/update")
	public String update(@RequestBody TimeEntity entity) {
		service.UpdateTime(entity);
		return "勉強時間を更新しました";
	}

	// ========================
	// 勉強時間 削除
	// ========================
	@DeleteMapping("/delete")
	public String delete(@RequestParam Integer time_id) {
		service.delete(time_id);
		return "勉強時間を削除しました";
	}

	// ========================
	// 勉強時間 表示（ユーザー別）
	// ========================
	@GetMapping("/display")
	public List<TimeEntity> display(@RequestParam Integer user_id) {
		return service.findByUserId(user_id);
	}
}
