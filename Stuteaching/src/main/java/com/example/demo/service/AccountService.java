package com.example.demo.service;

import java.util.List;

import com.example.demo.DTO.StudentUsersDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.entity.SubjectEntity;
import com.example.demo.entity.TeacherEntity;
import com.example.demo.web.dto.TeacherUsersResponseDTO;

public interface AccountService {

	//	public void UpdateTeacher(int user_id, TeacherUsersDTO user);

	public TeacherUsersResponseDTO DisplayTeacher(int user_id);

	//	public void UpdateStudent(int user_id, StudentUsersDTO user);

	public StudentUsersDTO DisplayStudent(int user_id);

	public List<TeacherUsersResponseDTO> getAllTeachers();

	public UserDTO getCurrentUser();

	UserDTO findByEmail(String email);

	TeacherEntity findByUserId(Integer userId);

	void updateUser(UserDTO dto, String email);

	List<SubjectEntity> findAll();
}
