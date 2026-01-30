package com.example.demo.service;

import java.util.List;

import com.example.demo.DTO.TalkDTO;
import com.example.demo.DTO.UserDTO;

public interface TalkService {
	// トーク送信
	public void sendTalk(TalkDTO talkDTO);

	// トーク一覧取得
	public List<TalkDTO> getTalkList(Integer classId);

	UserDTO getChatPartner(Integer roomId, Integer myUserId);

}
