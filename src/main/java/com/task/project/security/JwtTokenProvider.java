package com.task.project.security;

import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {
	
	public String generateToken(Authentication authentication) {
		String email = authentication.getName();
		Date currentDate = new Date();
		Date expireDate = new Date(currentDate.getTime()+3600000); // 3600000 milli seconds = 60 minutes dynamically we should read it from properties file for
		
		String token = Jwts.builder()
				.setSubject(email)
				.setIssuedAt(currentDate)
				.setExpiration(expireDate)
				.signWith(SignatureAlgorithm.HS512, "JwtSecreteKey")
				.compact();
		return token;
	}
	
	public String getEmailFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey("JwtSecreteKey")
		.parseClaimsJws(token).getBody();
		
		return claims.getSubject();
	}
}