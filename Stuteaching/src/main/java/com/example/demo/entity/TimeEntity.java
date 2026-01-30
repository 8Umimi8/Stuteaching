package com.example.demo.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "time")
@Getter
@Setter

public class TimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "time_id")
	private Integer timeId;

	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "study_subject")
	private String studySubject;

	@Column(name = "study_time")
	private Integer studyTime;

	@Column(name = "date")
	private Date date;
}
