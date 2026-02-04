package com.example.demo.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.RoomDTO;
import com.example.demo.entity.RoomEntity;
import com.example.demo.entity.TeacherEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.RoomRepository;
import com.example.demo.repository.TeacherRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.RoomService;

@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public Integer getOrCreateRoom(Integer teacherId, Integer userId) {

		// 🔑 teacher_id → user_id 変換
		UserEntity teacherUser = teacherRepository.findById(teacherId)
				.map(TeacherEntity::getUserId)
				.orElseThrow(() -> new IllegalArgumentException("教師が存在しない"));

		UserEntity user = userRepository.findById(userId)
				.orElseThrow(() -> new IllegalArgumentException("ユーザーが存在しない"));

		return roomRepository
				.findByRoomTeacherIdAndRoomUserIdAndIsDeletedFalse(
						teacherUser, user)
				.map(RoomEntity::getRoomId)
				.orElseGet(() -> {
					RoomEntity room = new RoomEntity();
					room.setRoomTeacherId(teacherUser);
					room.setRoomUserId(user);
					room.setIsDeleted(false);
					return roomRepository.save(room).getRoomId();
				});
	}

	@Override
	public List<RoomDTO> getMyChatRooms(Integer myUserId) {

		UserEntity me = userRepository.findById(myUserId)
				.orElseThrow(() -> new IllegalArgumentException("ユーザー不存在"));

		return roomRepository.findRoomsByUserId(myUserId)
				.stream()
				.map(room -> {
					RoomDTO dto = new RoomDTO();
					dto.setRoomId(room.getRoomId());

					UserEntity partner = room.getRoomUserId().equals(me)
							? room.getRoomTeacherId()
							: room.getRoomUserId();

					dto.setUserId(partner.getUserId());
					dto.setName(partner.getUserName());

					return dto;
				})
				.toList();
	}
}
