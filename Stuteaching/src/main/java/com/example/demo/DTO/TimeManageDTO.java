package com.example.demo.DTO;

import java.sql.Date;

import lombok.Data;

@Data
public class TimeManageDTO {

	private Integer timeId;
	private Integer userId;
	private String studySubject;
	private Integer studyTime;
	private Date date;

	public Integer getTimeId() {
		return timeId;
	}

	public void setTimeId(Integer timeId) {
		this.timeId = timeId;
	}
}
