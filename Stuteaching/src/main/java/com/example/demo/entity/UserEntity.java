package com.example.demo.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter

public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer user_id;

	@Column(name = "password")
	private String password;

	@Column(name = "email")
	private String email;

	@Column(name = "user_name")
	private String user_name;

	@Column(name = "birthday")
	private Date birthday;

	@Column(name = "gender")
	private Integer gender;

	@Column(name = "role")
	private Integer role;
}
