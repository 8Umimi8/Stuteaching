package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.DTO.TalkDTO;
import com.example.demo.service.RoomService;
import com.example.demo.service.Impl.TalkServiceImpl;
import com.example.demo.service.security.LoginUserDetails;

@Controller
public class TalkController {

	@Autowired
	private TalkServiceImpl talkServiceImpl;

	@Autowired
	private RoomService roomService;

	@GetMapping("/chat")
	public String chatRoot() {
		return "redirect:/chat/1";
	}

	// チャット画面表示
	@GetMapping("/chat/{classId}")
	public ModelAndView chat(
			@PathVariable Integer classId,
			@AuthenticationPrincipal LoginUserDetails user) {

		ModelAndView mav = new ModelAndView("CHAT/chat");

		mav.addObject("messages",
				talkServiceImpl.getTalkList(classId));
		mav.addObject("classId", classId);
		mav.addObject("currentUser", user);
		mav.addObject("chatPartner",
				talkServiceImpl.getChatPartner(classId, user.getUser_id()));

		mav.addObject("users",
				roomService.getMyChatRooms(user.getUser_id()));

		return mav;
	}

	// メッセージ送信
	@PostMapping("/chat/{roomId}/send")
	public String send(
			@PathVariable Integer roomId,
			@RequestParam String messageText,
			@AuthenticationPrincipal LoginUserDetails user) {

		TalkDTO dto = new TalkDTO();
		dto.setRoomId(roomId);
		dto.setMessageText(messageText);
		dto.setSenderId(user.getUser_id()); // ID取れる
		dto.setSenderName(user.getUsername()); // 名前取れる

		talkServiceImpl.sendTalk(dto);

		return "redirect:/chat/" + roomId;
	}

}
