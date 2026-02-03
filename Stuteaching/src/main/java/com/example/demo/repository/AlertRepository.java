package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.AlertEntity;

@Repository
public interface AlertRepository extends JpaRepository<AlertEntity, Integer> {

	// ログイン中ユーザー宛てで削除されていない通知を新しい順で取得
	@Query("SELECT a FROM AlertEntity a WHERE a.userId = :userId AND (a.isDeleted = false OR a.isDeleted IS NULL) ORDER BY a.createdAt DESC")
	List<AlertEntity> findActiveAlertsByUserId(@Param("userId") Integer userId);
}
