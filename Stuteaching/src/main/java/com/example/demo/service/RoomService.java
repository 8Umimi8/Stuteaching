package com.example.demo.service;

import java.util.List;

import com.example.demo.DTO.RoomDTO;

public interface RoomService {

	Integer getOrCreateRoom(Integer teacherId, Integer userId);

	List<RoomDTO> getMyChatRooms(Integer myUserId);

}
