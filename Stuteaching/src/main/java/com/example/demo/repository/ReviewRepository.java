package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ReviewEntity;
import com.example.demo.entity.TeacherEntity;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Integer> {

	Optional<ReviewEntity> findByTeacherId(TeacherEntity teacher);

}
