package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "student_users")
@Getter
@Setter

public class StudentEntity {
	@Column(name = "user_name")
	private String user_name;

}
