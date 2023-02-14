package com.task.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.project.payload.UsersDto;
import com.task.project.service.UsersService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private UsersService usersService;
	
	//post store the user in db
	@PostMapping("/register")
	public ResponseEntity<UsersDto> createUser(@RequestBody UsersDto usersDto) {
		return new ResponseEntity<UsersDto>(usersService.createUser(usersDto), HttpStatus.CREATED);
	}
}
