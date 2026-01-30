package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.service.RoomService;
import com.example.demo.service.security.LoginUserDetails;

@Controller
public class ChatStartController {

	@Autowired
	private RoomService roomService;

	@GetMapping("/chat/start/{teacherId}")
	public String startChat(
			@PathVariable Integer teacherId,
			@AuthenticationPrincipal LoginUserDetails user) {

		Integer roomId = roomService.getOrCreateRoom(
				teacherId,
				user.getUser_id());

		return "redirect:/chat/" + roomId;
	}
}
