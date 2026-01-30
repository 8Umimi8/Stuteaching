package com.example.demo.DTO;

import lombok.Data;

@Data
public class RoomDTO {

	private Integer roomId; // chat/{roomId} 用
	private Integer userId; // 相手ユーザーID
	private String name; // 相手ユーザー名

}
