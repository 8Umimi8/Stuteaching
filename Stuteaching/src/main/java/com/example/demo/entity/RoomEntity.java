package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "room")
@Getter
@Setter
public class RoomEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "room_id")
	private Integer roomId;

	@ManyToOne
	@JoinColumn(name = "room_user_id", nullable = false)
	private UserEntity roomUserId;

	@ManyToOne
	@JoinColumn(name = "room_teacher_id", nullable = false)
	private UserEntity roomTeacherId;

	@Column(name = "is_deleted")
	private Boolean isDeleted;

}
