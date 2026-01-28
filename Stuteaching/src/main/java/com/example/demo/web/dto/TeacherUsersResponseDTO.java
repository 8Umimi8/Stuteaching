package com.example.demo.web.dto;

public class TeacherUsersResponseDTO {

	private Integer teacherId;
	private String userName;
	private Integer gender;
	private Integer review;
	private String subjectName;

	public TeacherUsersResponseDTO() {
	}

	public TeacherUsersResponseDTO(Integer teacherId, String userName, Integer gender,
			Integer review, String subjectName) {
		this.teacherId = teacherId;
		this.userName = userName;
		this.gender = gender;
		this.review = review;
		this.subjectName = subjectName;
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Integer getReview() {
		return review;
	}

	public void setReview(Integer review) {
		this.review = review;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
}