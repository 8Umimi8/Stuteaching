package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ReviewEntity;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Integer> {

	@Query(value = """
			SELECT AVG(review)
			FROM review
			WHERE teacher_id = :teacherId
			""", nativeQuery = true)
	Double calcAvgByTeacherId(Integer teacherId);

}
