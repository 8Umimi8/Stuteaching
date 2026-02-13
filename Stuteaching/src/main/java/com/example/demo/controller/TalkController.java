package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.DTO.ReviewDTO;
import com.example.demo.DTO.TalkDTO;
import com.example.demo.entity.PhotosEntity;
import com.example.demo.repository.PhotosRepository;
import com.example.demo.service.ReviewService;
import com.example.demo.service.RoomService;
import com.example.demo.service.Impl.TalkServiceImpl;
import com.example.demo.service.security.LoginUserDetails;

@Controller
public class TalkController {

	@Autowired
	private TalkServiceImpl talkServiceImpl;

	@Autowired
	private RoomService roomService;

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private PhotosRepository photoRepository;

	@GetMapping("/chat")
	public ModelAndView chatRoot(@AuthenticationPrincipal LoginUserDetails user) {
		ModelAndView mav = new ModelAndView("CHAT/chat");

		// 初期状態はroomなし
		mav.addObject("messages", List.of()); // 空リスト
		mav.addObject("classId", 0); // roomなし
		mav.addObject("currentUser", user);
		mav.addObject("chatPartner", null); // 相手なし

		mav.addObject("users", roomService.getMyChatRooms(user.getUser_id()));

		return mav;
	}

	// チャット画面表示
	@GetMapping("/chat/{classId}")
	public ModelAndView chat(
			@PathVariable Integer classId,
			@AuthenticationPrincipal LoginUserDetails user) {

		ModelAndView mav = new ModelAndView("CHAT/chat");

		mav.addObject("activeMenu", "chat");

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
			@RequestParam(required = false) String messageText,
			@RequestParam(required = false, name = "image") MultipartFile image,
			@AuthenticationPrincipal LoginUserDetails user) throws IOException {

		TalkDTO dto = new TalkDTO();
		dto.setRoomId(roomId);
		dto.setMessageText(messageText);
		dto.setSenderId(user.getUser_id());
		dto.setSenderName(user.getUsername());

		// 画像が送られてきた場合
		if (image != null && !image.isEmpty()) {
			dto.setImageData(image.getBytes());
			dto.setImageType(image.getContentType());
		}

		talkServiceImpl.sendTalk(dto);
		System.out.println("image = " + image);
		System.out.println("image.isEmpty = " + (image == null ? "null" : image.isEmpty()));
		System.out.println("image bytes = " + (image == null ? "null" : image.getBytes().length));
		return "redirect:/chat/" + roomId;
	}

	@GetMapping("/chat/{classId}/messages")
	@ResponseBody
	public List<TalkDTO> getMessages(@PathVariable Integer classId) {
		return talkServiceImpl.getTalkList(classId);
	}

	@PostMapping("/chat/{classId}/rating")
	public String submitRating(
			@PathVariable Integer classId,
			@RequestParam Integer score,
			@AuthenticationPrincipal LoginUserDetails loginUser) {
		ReviewDTO dto = new ReviewDTO();
		dto.setRoomId(classId);
		dto.setScore(score);

		reviewService.registerReview(dto);

		return "redirect:/chat/" + classId;
	}

	@GetMapping("/chat/image/{photoId}")
	@ResponseBody
	public ResponseEntity<byte[]> getImage(@PathVariable Integer photoId) {
		PhotosEntity photo = photoRepository.findById(photoId)
				.orElseThrow(() -> new RuntimeException("画像が見つかりません"));

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_TYPE, photo.getImageType())
				.body(photo.getImageData());
	}
}
