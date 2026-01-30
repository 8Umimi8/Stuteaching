package com.example.demo.DTO;

import lombok.Data;

@Data
public class TeacherUpdateDTO {
	private String userName; // users.user_name
	private String email; // users.email
	private String subjectName; // subjects.subject_name

}
