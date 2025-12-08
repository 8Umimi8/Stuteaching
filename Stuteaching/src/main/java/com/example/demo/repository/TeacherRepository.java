package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.TeacherEntity;

import jakarta.transaction.Transactional;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherEntity, Integer> {

	//先生情報の表示
	@Query("SELECT a FROM TeacherEntity a WHERE a.user_id = :user_id")
	List<TeacherEntity> DisplayTeacher(@Param("user_id") int user_id);

	//先生情報の更新
	@Modifying
	@Transactional
	@Query("UPDATE FROM TeacherEntity a set a.user_name = :user_name"
			+ "								a.birthday  = :birthday"
			+ "								a.gender    = :gender"
			+ "								a.subject_name = :subject_name"
			+ "where a.user_id = :user_id")
	int UpdataTeacher(@Param("user_id") int user_id,
			@Param("user_name") String user_name,
			@Param("birthday") int birthday,
			@Param("gender") int gender,
			@Param("subject_name") int subject_name);
}
