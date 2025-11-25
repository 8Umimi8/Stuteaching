package com.example.demo.DTO;

import java.sql.Date;

import lombok.Data;

@Data
public class UserDTO {

	private Integer user_id;
	private String user_name;
	private String password;
	private String email;
	private Date birthday;
	private Integer gender;
	private Integer role;
}
