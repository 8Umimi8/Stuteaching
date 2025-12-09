package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UserEntity;

import jakarta.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	//退会
	@Modifying
	@Transactional
	@Query("DELETE FROM UserEntity a WHERE a.user_id IN :user_id")
	int DeleteUser(@Param("user_id") int[] user_id);
}
