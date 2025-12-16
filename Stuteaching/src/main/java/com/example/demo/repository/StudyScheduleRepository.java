package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.TimeEntity;

@Repository
public interface StudyScheduleRepository
		extends JpaRepository<TimeEntity, Integer> {

	// ユーザーIDごとの勉強時間一覧取得
	List<TimeEntity> findByUser_id(Integer user_id);
}
