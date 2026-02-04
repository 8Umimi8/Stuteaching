package com.example.demo.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.DTO.TalkDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.entity.RoomEntity;
import com.example.demo.entity.TalkEntity;
import com.example.demo.repository.RoomRepository;
import com.example.demo.repository.TalkRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.TalkService;
import com.example.demo.util.UtilService;

@Service
@Transactional
public class TalkServiceImpl implements TalkService {

	@Autowired
	private TalkRepository talkRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoomRepository roomRepository;

	@Override
	public void sendTalk(TalkDTO talkDTO) {
		// ① room を取得（FKの正解ルート）
		RoomEntity room = roomRepository.findById(talkDTO.getRoomId())
				.orElseThrow(() -> new IllegalArgumentException("roomが存在しない"));

		// ② Entityを組み立てる
		TalkEntity entity = new TalkEntity();
		entity.setRoomId(room); // ★ 超重要
		entity.setSenderId(talkDTO.getSenderId());
		entity.setMessageText(talkDTO.getMessageText());

		// ③ 保存
		talkRepository.save(entity);

	}

	@Override
	public List<TalkDTO> getTalkList(Integer roomId) {
		RoomEntity room = roomRepository.findById(roomId)
				.orElseThrow(() -> new IllegalArgumentException("roomが存在しない"));

		List<TalkEntity> entityList = talkRepository.findByRoomIdOrderByCreatedAtAsc(room);

		return entityList.stream().map(entity -> {
			TalkDTO dto = new TalkDTO();
			UtilService.ConvertDTO(entity, dto);

			if (entity.getSenderId() != null) {
				userRepository.findById(entity.getSenderId())
						.ifPresent(user -> dto.setSenderName(user.getUserName()));
			}

			return dto;
		}).toList();
	}

	@Override
	public UserDTO getChatPartner(Integer roomId, Integer myUserId) {

		RoomEntity room = roomRepository.findById(roomId)
				.orElseThrow(() -> new IllegalArgumentException("roomが存在しない"));

		Integer partnerId = room.getRoomUserId().getUserId().equals(myUserId)
				? room.getRoomTeacherId().getUserId()
				: room.getRoomUserId().getUserId();

		return userRepository.findById(partnerId)
				.map(entity -> {
					UserDTO dto = new UserDTO();
					UtilService.ConvertDTO(entity, dto); // ← 既存Utilを活用
					return dto;
				})
				.orElseThrow(() -> new IllegalArgumentException("ユーザーが存在しない"));
	}

}
