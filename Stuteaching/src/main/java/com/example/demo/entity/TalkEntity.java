package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "class")
@Getter
@Setter

public class TalkEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "class_id")
	private Integer class_id;

	@Column(name = "message_text")
	private String message_text;

	@Column(name = "post_message")
	private String post_message;

	@Column(name = "user_name")
	private String user_name;
}
