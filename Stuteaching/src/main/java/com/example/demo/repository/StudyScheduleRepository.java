package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.TimeEntity;

@Repository
public interface StudyScheduleRepository
		extends JpaRepository<TimeEntity, Integer> {

	List<TimeEntity> findByUserId(Integer userId);
}
