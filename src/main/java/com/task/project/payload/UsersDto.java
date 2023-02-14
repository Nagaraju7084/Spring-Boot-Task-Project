package com.task.project.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersDto {
	private Long id;
	private String name;
	private String email;
	private String password;
}
