package com.example.demo.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.DTO.TalkDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.entity.PhotosEntity;
import com.example.demo.entity.RoomEntity;
import com.example.demo.entity.TalkEntity;
import com.example.demo.repository.PhotosRepository;
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
	@Autowired
	private PhotosRepository photoRepository;

	@Override
	public void sendTalk(TalkDTO talkDTO) {
		// ① room を取得
		RoomEntity room = roomRepository.findById(talkDTO.getRoomId())
				.orElseThrow(() -> new IllegalArgumentException("roomが存在しない"));

		// ② Talk を保存
		TalkEntity talk = new TalkEntity();
		talk.setRoomId(room);
		talk.setSenderId(talkDTO.getSenderId());
		talk.setMessageText(talkDTO.getMessageText());

		TalkEntity savedTalk = talkRepository.save(talk);

		if (talkDTO.getImageData() != null && talkDTO.getImageData().length > 0) {
			PhotosEntity photo = new PhotosEntity();
			photo.setTalk(savedTalk);
			photo.setImageData(talkDTO.getImageData());
			photo.setImageType(talkDTO.getImageType());

			PhotosEntity savedPhoto = photoRepository.save(photo);
			System.out.println("Photo saved with ID: " + savedPhoto.getPhotId());
		}

	}

	@Override
	public List<TalkDTO> getTalkList(Integer roomId) {
		RoomEntity room = roomRepository.findById(roomId)
				.orElseThrow(() -> new IllegalArgumentException("roomが存在しない"));

		List<TalkEntity> entityList = talkRepository.findByRoomIdOrderByCreatedAtAsc(room);

		return entityList.stream().map(entity -> {
			TalkDTO dto = new TalkDTO();
			UtilService.ConvertDTO(entity, dto);

			// 送信者名をセット
			if (entity.getSenderId() != null) {
				userRepository.findById(entity.getSenderId())
						.ifPresent(user -> dto.setSenderName(user.getUserName()));
			}

			// ★ 画像が存在する場合はURLをセット（ここが追加部分） ★
			List<PhotosEntity> photos = photoRepository.findByTalk(entity);
			if (!photos.isEmpty()) {
				dto.setImageUrl("/chat/image/" + photos.get(0).getPhotId());
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
