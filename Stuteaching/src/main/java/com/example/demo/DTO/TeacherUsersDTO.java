package com.example.demo.DTO;

import java.sql.Date;

import lombok.Data;

@Data
public class TeacherUsersDTO {

	private String user_name;
	private Date birthday;
	private Integer gender;
	private String subject_name;
	private String review;
}
