package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.AlertDTO;
import com.example.demo.service.AlertService;

@RestController
@RequestMapping("/alert")
public class AlertController {

	@Autowired
	private AlertService alertService;

	// ========================
	// アラート一覧表示（ユーザー別）
	// ========================
	@GetMapping("/display")
	public List<AlertDTO> display(@RequestParam int user_id) {
		return alertService.DisplayAlerts(user_id);
	}

	// ========================
	// アラート削除
	// ========================
	@DeleteMapping("/delete")
	public String delete(@RequestParam int alert_id) {

		boolean result = alertService.DeleteAlerts(alert_id);

		if (result) {
			return "アラートを削除しました";
		} else {
			return "アラート削除に失敗しました";
		}
	}
}
