package com.example.demo.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "teacher_users")
@Getter
@Setter

public class TeacherEntity {
	@Column(name = "user_name")
	private String user_name;

	@Column(name = "birthday")
	private Date birthday;

	@Column(name = "gender")
	private Integer gender;

	@Column(name = "subject_name")
	private String subject_name;

	@Column(name = "review")
	private Integer review;
}
