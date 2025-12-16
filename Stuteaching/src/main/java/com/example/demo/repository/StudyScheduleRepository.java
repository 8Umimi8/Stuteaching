package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.TimeEntity;

import jakarta.transaction.Transactional;

@Repository
public interface StudyScheduleRepository extends JpaRepository<TimeEntity, Integer> {

	//勉強時間表示
	@Query("SELECT a FROM TimeEntity a WHERE a.user_Id = :user_Id")
	List<TimeEntity> DisplayTime(@Param("user_id") int user_id);

	//勉強時間削除
	@Modifying
	@Transactional
	@Query("DELETE FROM TimeEntity a WHERE a.time_id IN :time_id")
	int DeleteTime(@Param("time_id") int[] time_id);

	//勉強時間更新
	@Modifying
	@Transactional
	@Query("UPDATE FROM TimeEntity a set a.study_subject = :study_subject"
			+ "								a.study_time  = :study_time"
			+ "								a.date    = :date"
			+ "where a.time_id = :time_id")
	int UpdataTime(@Param("time_id") int time_id,
			@Param("study_subject") String study_subject,
			@Param("study_time") int study_time,
			@Param("date") int date);

}
