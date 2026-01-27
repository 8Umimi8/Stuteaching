package com.example.demo.service;

import java.util.List;

import com.example.demo.DTO.StudentUsersDTO;
import com.example.demo.DTO.TeacherUsersDTO;

public interface AccountService {

	//	public void UpdateTeacher(int user_id, TeacherUsersDTO user);

	public TeacherUsersDTO DisplayTeacher(int user_id);

	public void UpdateStudent(int user_id, StudentUsersDTO user);

	public StudentUsersDTO DisplayStudent(int user_id);

	public List<TeacherUsersDTO> getAllTeachers();

}
