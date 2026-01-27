package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.DTO.TeacherUsersDTO;
import com.example.demo.entity.TeacherEntity;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherEntity, Integer> {

	@Query(value = """

			SELECT
			  t.teacher_id AS teacher_id,
			  u.user_name AS user_name,
			  u.gender AS gender,
			  r.review AS review,
			  s.subject_name AS subject_name
			FROM teacher_users t
			JOIN users u ON t.user_id = u.user_id
			LEFT JOIN review r ON t.review_id = r.review_id
			LEFT JOIN subject s ON t.subject_id = s.subject_id
			WHERE u.role = 2;
			""", nativeQuery = true)
	List<TeacherUsersDTO> findAllTeachers();

}
