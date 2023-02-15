package com.task.project.payload;

import lombok.Getter;

@Getter
public class JwtAuthenticationResponse {
	private String token;
	private String tokenType = "Bearer";
	
	public JwtAuthenticationResponse(String token) {
		this.token = token;
	}
}