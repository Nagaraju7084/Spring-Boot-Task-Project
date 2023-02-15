package com.task.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.project.payload.JwtAuthenticationResponse;
import com.task.project.payload.LoginDto;
import com.task.project.payload.UsersDto;
import com.task.project.security.JwtTokenProvider;
import com.task.project.service.UsersService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	//post store the user in db
	@PostMapping("/register")
	public ResponseEntity<UsersDto> createUser(@RequestBody UsersDto usersDto) {
		return new ResponseEntity<UsersDto>(usersService.createUser(usersDto), HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthenticationResponse> loginUser(@RequestBody LoginDto loginDto){
		Authentication authentication = 
				authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
						);
		
		String token = jwtTokenProvider.generateToken(authentication);
		
		log.info("user details from authentication object :\t"+authentication);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return ResponseEntity.ok(new JwtAuthenticationResponse(token));
	}
}
