package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.ReviewService;

@Controller
public class ReviewController {

	@Autowired
	private ReviewService reviewService;

	@PostMapping("/chat/{classId}/rating")
	public String saveRating(
			@PathVariable Integer classId,
			@RequestParam Integer score) {
		reviewService.save(classId, score);
		return "redirect:/chat/" + classId;
	}

}
