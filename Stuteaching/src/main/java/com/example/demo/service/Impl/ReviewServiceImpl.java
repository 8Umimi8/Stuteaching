package com.example.demo.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ReviewEntity;
import com.example.demo.entity.RoomEntity;
import com.example.demo.entity.TeacherEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.ReviewRepository;
import com.example.demo.repository.RoomRepository;
import com.example.demo.repository.TeacherRepository;
import com.example.demo.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private TeacherRepository teacherRepository;

	@Override
	public void save(Integer classId, Integer score) {

		// ① room 取得
		RoomEntity room = roomRepository.findById(classId)
				.orElseThrow(() -> new RuntimeException("room not found"));

		// ② room から users の teacher を取得
		UserEntity teacherUser = room.getRoomTeacherId();

		// ③ users → teacher_users に変換
		TeacherEntity teacher = teacherRepository
				.findByUser(teacherUser)
				.orElseThrow(() -> new RuntimeException("teacher not found"));

		// ④ review 作成
		ReviewEntity review = new ReviewEntity();
		review.setReview(score);
		review.setTeacherId(teacher);

		// ⑤ 保存
		reviewRepository.save(review);
	}

}
