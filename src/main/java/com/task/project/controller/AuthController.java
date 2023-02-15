package com.task.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.project.payload.LoginDto;
import com.task.project.payload.UsersDto;
import com.task.project.service.UsersService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	//post store the user in db
	@PostMapping("/register")
	public ResponseEntity<UsersDto> createUser(@RequestBody UsersDto usersDto) {
		return new ResponseEntity<UsersDto>(usersService.createUser(usersDto), HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestBody LoginDto loginDto){
		Authentication authentication = 
				authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
						);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return new ResponseEntity<String>("User logged in successfully!", HttpStatus.OK);
	} 
	
	@GetMapping("/home")
	public String home() {
		return "Home Page";
	}
	
	@GetMapping("/login")
	public String login() {
		return "Login Page";
	}
}
