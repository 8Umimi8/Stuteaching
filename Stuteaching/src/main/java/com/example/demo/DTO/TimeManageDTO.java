package com.example.demo.DTO;

import java.sql.Date;

import lombok.Data;

@Data
public class TimeManageDTO {

	private String study_subject;
	private Integer study_time;
	private Date date;
}
