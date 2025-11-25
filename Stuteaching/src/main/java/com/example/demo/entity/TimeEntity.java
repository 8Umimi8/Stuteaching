package com.example.demo.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "time")
@Getter
@Setter

public class TimeEntity {
	@Column(name = "study_subject")
	private String study_subject;

	@Column(name = "study_time")
	private Integer study_time;

	@Column(name = "date")
	private Date date;
}
