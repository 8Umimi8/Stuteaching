package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.TalkEntity;

@Repository
public interface TalkRepository extends JpaRepository<TalkEntity, Integer> {

	//トーク情報の表示
	@Query("SELECT a FROM TalkEntity a WHERE a.class_id = :class_id")
	List<TalkEntity> DisplayTalk(@Param("class_id") int class_id);
}
