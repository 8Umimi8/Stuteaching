package com.example.demo.DTO;

import java.sql.Date;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDTO {

	private Integer user_id;
	@NotBlank(message = "必須です")
	private String user_name;
	@NotBlank(message = "必須です")
	private String password;
	@NotBlank(message = "必須です")
	@Email(message = "メールアドレスの形式が正しくありません")
	private String email;
	@NotBlank(message = "必須です")
	private Date birthday;
	@NotBlank(message = "必須です")
	private Integer gender;
	@NotBlank(message = "必須です")
	private Integer role;
}
