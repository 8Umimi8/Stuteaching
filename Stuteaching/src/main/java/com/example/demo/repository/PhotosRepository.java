package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.PhotosEntity;
import com.example.demo.entity.TalkEntity;

@Repository
public interface PhotosRepository extends JpaRepository<PhotosEntity, Integer> {

	// 特定のトークに紐づく画像を取得
	List<PhotosEntity> findByTalk(TalkEntity talk);

}
