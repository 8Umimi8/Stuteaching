package com.example.demo.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.DTO.StudentUsersDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.entity.StudentEntity;
import com.example.demo.entity.SubjectEntity;
import com.example.demo.entity.TeacherEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.SubjectRepository;
import com.example.demo.repository.TeacherRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AccountService;
import com.example.demo.service.security.LoginUserDetails;
import com.example.demo.util.UtilService;
import com.example.demo.web.dto.TeacherUsersResponseDTO;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private StudentRepository studentRepository;

	private final UserRepository userRepository;

	@Autowired
	private SubjectRepository subjectRepository;

	@Autowired
	public AccountServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDTO getCurrentUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		LoginUserDetails userDetails = (LoginUserDetails) auth.getPrincipal();
		Integer id = userDetails.getUserId();

		UserEntity entity = userRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("User not found"));

		return convertToDTO(entity);
	}

	private UserDTO convertToDTO(UserEntity entity) {
		UserDTO dto = new UserDTO();
		dto.setUser_id(entity.getUser_id()); // ← user_id に合わせる
		dto.setUser_name(entity.getUser_name()); // ← user_name に合わせる
		dto.setGender(entity.getGender());
		dto.setBirthday(entity.getBirthday());
		dto.setEmail(entity.getEmail());
		// subject は UserEntity に存在しないので削除 or 別テーブルから取得
		return dto;
	}

	@Override
	public TeacherUsersResponseDTO DisplayTeacher(int user_id) {
		Optional<TeacherEntity> entity = teacherRepository.findById(user_id);
		TeacherUsersResponseDTO dto = new TeacherUsersResponseDTO();
		UtilService.ConvertDTO(entity, dto);
		return dto;
	}

	@Override
	public StudentUsersDTO DisplayStudent(int user_id) {
		Optional<StudentEntity> entity = studentRepository.findById(user_id);
		StudentUsersDTO dto = new StudentUsersDTO();
		UtilService.ConvertDTO(entity, dto);
		return dto;
	}

	@Override
	public List<TeacherUsersResponseDTO> getAllTeachers() {
		return teacherRepository.findAllTeachers()
				.stream()
				.map(p -> new TeacherUsersResponseDTO(
						p.getTeacherId(),
						p.getUserName(),
						p.getGender(),
						p.getReview(),
						p.getSubjectName()))
				.toList();
	}

	@Override
	public UserDTO findByEmail(String email) {
		UserEntity entity = userRepository.findByEmail(email);
		if (entity == null) {
			throw new RuntimeException("User not found with email: " + email);
		}

		UserDTO dto = new UserDTO();
		dto.setUser_id(entity.getUser_id());
		dto.setUser_name(entity.getUser_name());
		dto.setEmail(entity.getEmail());
		dto.setBirthday(entity.getBirthday());
		dto.setGender(entity.getGender());
		dto.setRole(entity.getRole());
		return dto;
	}

	@Override
	public TeacherEntity findByUserId(Integer userId) {
		// userId から UserEntity を取得して渡す
		UserEntity userEntity = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found"));
		return teacherRepository.findByUser(userEntity);
	}

	@Transactional
	@Override
	public void updateUser(UserDTO dto, String email) {
		// --- 1. ユーザーを取得 ---
		UserEntity user = userRepository.findByEmail(email);
		if (user == null) {
			throw new RuntimeException("ユーザーが見つからないよ");
		}

		// --- 2. 基本情報を更新 ---
		user.setUser_name(dto.getUser_name());
		user.setEmail(dto.getEmail());
		user.setBirthday(dto.getBirthday());
		user.setGender(dto.getGender());
		userRepository.save(user);

		// --- 3. 先生（role=2）の場合のみ、担当教科を更新 ---
		if (user.getRole() == 2 && dto.getSubject() != null) {
			// 先生エンティティを取得
			TeacherEntity teacher = teacherRepository.findByUser(user);
			if (teacher == null) {
				throw new RuntimeException("先生情報が見つかりません");
			}

			// 選択された教科を取得
			SubjectEntity subjectEntity = subjectRepository.findById(dto.getSubject())
					.orElseThrow(() -> new RuntimeException("存在しない教科IDです: " + dto.getSubject()));

			// 教科を更新
			teacher.setSubjectId(subjectEntity);
			teacherRepository.save(teacher);
		}
	}

	@Override
	public List<SubjectEntity> findAll() {
		// 削除されていない教科のみ返す（is_deleted = 0）
		return subjectRepository.findAll()
				.stream()
				.toList();
	}

}