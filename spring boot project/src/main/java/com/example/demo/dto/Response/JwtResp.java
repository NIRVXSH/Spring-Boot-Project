package com.example.demo.dto.Response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtResp {
    private String token;
	private String type = "Bearer";
	private String id;
	private String username;
	private String email;
	private List<String> roles;

	public JwtResp(Object jwtToken, String id, String username,  List<String> roles) {
		this.token = (String) jwtToken;
		this.id = id;
		this.username = username;
		this.roles = roles;
	}
}
