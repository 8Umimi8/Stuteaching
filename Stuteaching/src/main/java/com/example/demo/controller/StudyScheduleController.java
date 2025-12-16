package com.example.demo.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.TimeManageDTO;

@RestController
@RequestMapping("/study-time")
public class StudyScheduleController {

	private List<TimeManageDTO> timeList = new ArrayList<>();

	// insert
	@PostMapping("/insert")
	public String insertTime(@RequestBody TimeManageDTO dto) {
		timeList.add(dto);
		return "勉強時間を登録しました";
	}

	// Update
	@PutMapping("/update")
	public String updateTime(@RequestBody TimeManageDTO dto) {
		for (TimeManageDTO t : timeList) {
			if (t.getDate().equals(dto.getDate())
					&& t.getStudy_subject().equals(dto.getStudy_subject())) {

				t.setStudy_time(dto.getStudy_time());
				return "勉強時間を更新しました";
			}
		}
		return "更新対象が見つかりません";
	}

	// Delete
	@DeleteMapping("/delete")
	public String deleteTime(@RequestParam Date date,
			@RequestParam String studySubject) {

		timeList.removeIf(t -> t.getDate().equals(date)
				&& t.getStudy_subject().equals(studySubject));

		return "勉強時間を削除しました";
	}

	// Display
	@GetMapping("/display")
	public List<TimeManageDTO> displayTime() {
		return timeList;
	}
}