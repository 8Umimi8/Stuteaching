package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.DTO.AlertDTO;
import com.example.demo.service.AlertService;

@Controller
@RequestMapping("/alert")
public class AlertController {

	@Autowired
	private AlertService alertService;

	// ========================
	// アラート一覧表示（ユーザー別）
	// ========================
	@GetMapping("/display")
	public String display(
			@RequestParam int user_id,
			Model model) {

		List<AlertDTO> alerts = alertService.DisplayAlerts(user_id);
		model.addAttribute("alerts", alerts);

		return "alert"; // alert.html
	}

	// ========================
	// アラート削除
	// ========================
	@PostMapping("/delete")
	public String delete(
			@RequestParam int alert_id,
			@RequestParam int user_id) {

		alertService.DeleteAlerts(alert_id);

		// 削除後、一覧を再表示
		return "redirect:/alert/display?user_id=" + user_id;
	}
}
