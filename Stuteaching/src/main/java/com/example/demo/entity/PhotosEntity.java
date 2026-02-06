package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "photos")
@Getter
@Setter
public class PhotosEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "phot_id") // DBに合わせて phot_id のまま
	private Integer photId; // フィールド名を小文字始まりにする（Lombok対応）

	@ManyToOne
	@JoinColumn(name = "class_id", nullable = false)
	private TalkEntity talk;

	@Lob
	@Column(name = "image_data", columnDefinition = "LONGBLOB", nullable = false)
	private byte[] imageData;

	@Column(name = "image_type")
	private String imageType;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@PrePersist
	public void onCreate() {
		this.createdAt = LocalDateTime.now();
	}
}
