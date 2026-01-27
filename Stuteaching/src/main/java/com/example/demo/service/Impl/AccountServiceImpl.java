package com.example.demo.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.StudentUsersDTO;
import com.example.demo.DTO.TeacherUsersDTO;
import com.example.demo.entity.StudentEntity;
import com.example.demo.entity.TeacherEntity;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.TeacherRepository;
import com.example.demo.service.AccountService;
import com.example.demo.util.UtilService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private StudentRepository studentRepository;

	//	@Override
	//	public void UpdateTeacher(int user_id, TeacherUsersDTO user) {
	//		Optional<TeacherEntity> optional = teacherRepository.findById(user_id);
	//		TeacherEntity entity = optional.get();
	//
	//		entity.setUser_name(user.getUser_name());
	//		entity.setBirthday(user.getBirthday());
	//		entity.setGender(user.getGender());
	//		entity.setSubject_name(user.getSubject_name());
	//		entity.setReview(user.getReview());
	//
	//		teacherRepository.save(entity);
	//
	//	}

	@Override
	public TeacherUsersDTO DisplayTeacher(int user_id) {
		Optional<TeacherEntity> entity = teacherRepository.findById(user_id);
		TeacherUsersDTO dto = new TeacherUsersDTO();
		UtilService.ConvertDTO(entity, dto);

		return dto;
	}

	@Override
	public void UpdateStudent(int user_id, StudentUsersDTO user) {
		Optional<StudentEntity> optional = studentRepository.findById(user_id);
		StudentEntity entity = optional.get();

		entity.setUser_name(user.getUser_name());

		studentRepository.save(entity);

	}

	@Override
	public StudentUsersDTO DisplayStudent(int user_id) {
		Optional<StudentEntity> entity = studentRepository.findById(user_id);
		StudentUsersDTO dto = new StudentUsersDTO();
		UtilService.ConvertDTO(entity, dto);

		return dto;
	}

	@Override
	public List<TeacherUsersDTO> getAllTeachers() {
		return teacherRepository.findAllTeachers();
	}

}
