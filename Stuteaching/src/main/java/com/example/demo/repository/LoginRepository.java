package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.LoginEntity;

@Repository
public interface LoginRepository extends JpaRepository<LoginEntity, Integer> {
	//メールアドレスで users テーブルからユーザー情報を取得する	
	Optional<LoginEntity> findByEmail(String email);
}
