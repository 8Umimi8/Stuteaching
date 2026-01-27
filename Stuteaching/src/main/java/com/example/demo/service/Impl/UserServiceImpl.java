package com.example.demo.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.UserDTO;
import com.example.demo.entity.StudentEntity;
import com.example.demo.entity.TeacherEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.LoginRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.TeacherRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.util.UtilService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private LoginRepository loginrepository;

	@Autowired
	private UserRepository userrepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

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
		Integer userId = userEntity.getUser_id();

		// role に応じて student / teacher に保存
		if (user.getRole() == 1) {
			StudentEntity student = new StudentEntity();
			student.setUser_id(userId); // ← ここもスネークケース
			studentRepository.save(student);
		}

		if (user.getRole() == 2) {
			TeacherEntity teacher = new TeacherEntity();
			teacher.setUserId(userId);
			teacherRepository.save(teacher);
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
