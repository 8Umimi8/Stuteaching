package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter

public class LoginEntity {
	@Column(name = "role")
	private Integer role;

	@Column(name = "user_id")
	private Integer user_id;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;
}
