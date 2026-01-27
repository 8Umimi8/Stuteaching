package com.example.demo.DTO;

import lombok.Data;

@Data
public class TalkDTO {
	private Integer classId;
	private Integer roomId;
	private Integer senderId;
	private String senderName;
	private String messageText;
}
