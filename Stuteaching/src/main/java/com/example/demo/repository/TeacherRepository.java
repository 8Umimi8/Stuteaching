package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.TeacherEntity;
import com.example.demo.entity.UserEntity;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherEntity, Integer> {

	@Query("SELECT t FROM TeacherEntity t WHERE t.user_id = :user")
	Optional<TeacherEntity> findByUser(@Param("user") UserEntity user);

}
