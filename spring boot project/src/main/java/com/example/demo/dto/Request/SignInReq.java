package com.example.demo.dto.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignInReq {
	@NotBlank(message = "{msg.login.err.username}")
	private String username;
	@NotBlank
	private String password;
}
