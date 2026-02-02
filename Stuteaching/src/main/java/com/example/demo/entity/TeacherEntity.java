package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "teacher_users")
@Getter
@Setter
public class TeacherEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "teacher_id")
	private Integer teacherId;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private UserEntity user_id;

	@ManyToOne
	@JoinColumn(name = "subject_id")
	private SubjectEntity subjectId;

	@Column(name = "review_id")
	private Integer reviewId;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@Column(name = "is_deleted")
	private Integer isDeleted;
}
