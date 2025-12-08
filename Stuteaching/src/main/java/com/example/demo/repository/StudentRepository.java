package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.StudentEntity;

import jakarta.transaction.Transactional;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {

	@Query("SELECT a FROM StudentEntity a WHERE a.user_id = :user_id")
	List<StudentEntity> DisplayStudent(@Param("user_id") int user_id);

	@Modifying
	@Transactional
	@Query("UPDATE FROM StudentEntity a set a.user_name = :user_name where a.user_id = :user_id")
	int UpdataStudent(@Param("user_id") int user_id,
			@Param("user_name") String user_name);
}
