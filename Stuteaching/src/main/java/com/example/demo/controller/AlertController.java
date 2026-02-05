package com.example.demo.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.DTO.UserDTO;
import com.example.demo.entity.AlertEntity;
import com.example.demo.repository.AlertRepository;
import com.example.demo.service.AccountService;
import com.example.demo.service.security.CustomUserDetails;

@Controller
@RequestMapping("/alert")
public class AlertController {

	@Autowired
	private AlertRepository alertRepository;

	@Autowired
	private AccountService accountService;

	@GetMapping("/header")
	public String showHeader(Model model, @AuthenticationPrincipal CustomUserDetails user) {

		// ユーザーがログインしていない場合の安全処理
		if (user == null) {
			model.addAttribute("notifications", List.of());
			return "fragments/header";
		}

		Integer userId = user.getUserId(); // ログインユーザーのID取得
		List<AlertEntity> notifications = alertRepository.findActiveAlertsByUserId(userId);

		System.out.println("notifications size = " + notifications.size());

		model.addAttribute("notifications", notifications);
		return "fragments/header"; // header.htmlを返す
	}

	@GetMapping("/read/{id}")
	public String markAsRead(@PathVariable("id") Integer id, Principal principal) {
		if (principal == null) {
			return "redirect:/login";
		}

		UserDTO user = accountService.findByEmail(principal.getName());
		if (user == null)
			return "redirect:/login";

		AlertEntity alert = alertRepository.findById(id).orElse(null);
		if (alert != null && alert.getUserId().equals(user.getUserId())) {
			alert.setIsRead(true);
			alertRepository.save(alert);
		}

		return "redirect:/"; // 必要に応じて Ajax に置き換え可能
	}

	@PostMapping("/read/batch")
	@Transactional
	@ResponseBody
	public Map<String, String> markAllAsRead(@RequestBody List<Integer> alertIds, Principal principal) {
		if (principal == null) {
			return Map.of("status", "ERROR", "message", "Unauthorized");
		}

		UserDTO user = accountService.findByEmail(principal.getName());
		if (user == null) {
			return Map.of("status", "ERROR", "message", "User not found");
		}

		List<AlertEntity> alerts = alertRepository.findAllById(alertIds);
		for (AlertEntity alert : alerts) {
			if (alert.getUserId().equals(user.getUserId())) {
				alert.setIsRead(true);
			}
		}

		alertRepository.saveAll(alerts);
		return Map.of("status", "OK");
	}

}
