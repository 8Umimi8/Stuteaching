package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.DTO.AlertDTO;
import com.example.demo.service.AlertService;
import com.example.demo.service.security.LoginUserDetails;

@Controller
@RequestMapping("/alert")
public class AlertController {

	@Autowired
	private AlertService alertService;

	// ========================
	// アラート一覧表示（ユーザー別）
	// ========================
	@GetMapping("/display")
	public String display(Model model, @AuthenticationPrincipal LoginUserDetails user) {

		Integer user_id = user.getUser_id();

		List<AlertDTO> alerts = alertService.DisplayAlerts(user_id);
		model.addAttribute("alerts", alerts);

		return "alert"; // alert.html
	}

	// ========================
	// アラート削除
	// ========================
	@PostMapping("/delete")
	public String delete(@RequestParam int alert_id, @AuthenticationPrincipal LoginUserDetails user) {

		alertService.DeleteAlerts(alert_id);

		// 削除後、一覧を再表示
		return "redirect:/alert/display";
	}
}
