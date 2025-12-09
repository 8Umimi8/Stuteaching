package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.LoginEntity;

@Repository
public interface LoginRepository extends JpaRepository<LoginEntity, Integer> {

	//ログイン（情報を持ってくる）
	@Query("SELECT a FROM LoginEntity a WHERE a.user_id = :user_id")
	List<LoginEntity> Login(@Param("user_id") int user_id);
}
