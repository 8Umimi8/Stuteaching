package com.example.demo.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	@Column(name = "user_id")
	private Integer user_id;

	@Column(name = "user_name")
	private String user_name;

	@Column(name = "birthday")
	private Date birthday;

	@Column(name = "gender")
	private Integer gender;

	@Column(name = "subject_name")
	private String subject_name;

	@Column(name = "review")
	private String review;
}
