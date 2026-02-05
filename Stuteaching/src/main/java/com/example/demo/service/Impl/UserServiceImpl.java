package com.example.demo.service.Impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.UserDTO;
import com.example.demo.entity.AlertEntity;
import com.example.demo.entity.StudentEntity;
import com.example.demo.entity.SubjectEntity;
import com.example.demo.entity.TeacherEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.AlertRepository;
import com.example.demo.repository.LoginRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.SubjectRepository;
import com.example.demo.repository.TeacherRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.util.UtilService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private LoginRepository loginrepository;

	@Autowired
	private SubjectRepository subjectRepository;
	@Autowired
	private UserRepository userrepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AlertRepository alertRepository;

	@Override
	public void InsertUser(UserDTO user) {

		System.out.println("DTO role = " + user.getRole());
		// ===== DTO → Entity 変換 =====
		UserEntity userEntity = new UserEntity();
		UtilService.ConvertEntity(userEntity, user);

		System.out.println("Entity role = " + userEntity.getRole());
		// ===== パスワードをハッシュ化 =====
		String rawPassword = userEntity.getPassword();
		String encodedPassword = passwordEncoder.encode(rawPassword);
		userEntity.setPassword(encodedPassword);

		// ===== DB保存 =====
		userrepository.save(userEntity);

		// INSERT 後の user_id を取得（スネークケースのまま）
		Integer userId = userEntity.getUserId();

		if (user.getRole() == 1) {
			StudentEntity student = new StudentEntity();
			student.setUserId(userEntity);
			studentRepository.save(student);

			AlertEntity alert = new AlertEntity();
			alert.setUserId(userId);
			alert.setTitle("ようこそスタチンへ！まずは先生を探してみましょう！");
			alert.setIsRead(false);
			alert.setIsDeleted(false);
			alert.setCreatedAt(LocalDateTime.now());
			alert.setUpdatedAt(LocalDateTime.now());
			alertRepository.save(alert);
		}

		// 先生
		else if (user.getRole() == 2) {
			TeacherEntity teacher = new TeacherEntity();
			teacher.setUserId(userEntity);

			// ✅ 実際に存在する Subject を取得してセット
			if (user.getSubject() != null) {
				SubjectEntity subjectEntity = subjectRepository.findById(user.getSubject())
						.orElseThrow(() -> new RuntimeException("存在しない教科IDです: " + user.getSubject()));
				teacher.setSubjectId(subjectEntity);
			}
			teacherRepository.save(teacher);

			AlertEntity alert = new AlertEntity();
			alert.setUserId(userId);
			alert.setTitle("ようこそスタチンへ！楽しく授業をしよう！");
			alert.setIsRead(false);
			alert.setIsDeleted(false);
			alert.setCreatedAt(LocalDateTime.now());
			alert.setUpdatedAt(LocalDateTime.now());
			alertRepository.save(alert);
		}
	}

	@Override
	public void DeleteUser(int user_id) {
		userrepository.deleteById(user_id);

	}

	//今回は使わない
	@Override
	public void Login(String email, String password) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
