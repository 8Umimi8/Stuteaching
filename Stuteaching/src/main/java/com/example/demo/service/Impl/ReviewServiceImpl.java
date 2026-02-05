package com.example.demo.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.DTO.ReviewDTO;
import com.example.demo.entity.ReviewEntity;
import com.example.demo.entity.RoomEntity;
import com.example.demo.entity.TeacherEntity;
import com.example.demo.repository.ReviewRepository;
import com.example.demo.repository.RoomRepository;
import com.example.demo.repository.TeacherRepository;
import com.example.demo.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private ReviewRepository reviewRepository;

	@Override
	@Transactional
	public void registerReview(ReviewDTO dto) {

		// room → teacher
		RoomEntity room = roomRepository.findById(dto.getRoomId())
				.orElseThrow();

		TeacherEntity teacher = teacherRepository.findByUserId(room.getRoomTeacherId());

		// ① 評価履歴を保存
		ReviewEntity review = new ReviewEntity();
		review.setTeacher(teacher); // ★ Entityを渡す
		review.setReview(dto.getScore());
		reviewRepository.save(review);

		// ② 平均を再計算
		Double avg = reviewRepository
				.calcAvgByTeacherId(teacher.getTeacherId());

		int avgInt = (avg == null) ? 0 : (int) Math.round(avg);

		// ③ teacher.review_id に平均を保存
		teacherRepository.updateAvgReview(
				teacher.getTeacherId(),
				avgInt);
	}

}
