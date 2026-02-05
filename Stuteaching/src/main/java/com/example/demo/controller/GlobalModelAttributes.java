package com.example.demo.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.DTO.UserDTO;
import com.example.demo.entity.AlertEntity;
import com.example.demo.repository.AlertRepository;
import com.example.demo.service.AccountService;

@ControllerAdvice
public class GlobalModelAttributes {

	@Autowired
	private AlertRepository alertRepository;

	@Autowired
	private AccountService accountService;

	// 全ページに通知を追加
	@ModelAttribute("notifications")
	public List<AlertEntity> addNotifications(Principal principal) {
		if (principal == null) {
			return List.of(); // ログインしていない場合は空
		}

		// ログインユーザーを UserDTO で取得
		UserDTO user = accountService.findByEmail(principal.getName());
		if (user == null) {
			return List.of();
		}

		return alertRepository.findActiveAlertsByUserId(user.getUserId());
	}
}
