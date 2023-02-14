package com.task.project.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.project.entity.Users;
import com.task.project.payload.UsersDto;
import com.task.project.repository.UsersRepository;
import com.task.project.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UsersRepository usersRepository;
	
	@Override
	public UsersDto createUser(UsersDto usersDto) {
		Users users = usersRepository.save(dtoToUsers(usersDto));
		return usersToDto(users);
	}
	
	private Users dtoToUsers(UsersDto usersDto) {
		Users users = new Users();
		BeanUtils.copyProperties(usersDto, users);
		return users;
	}
	
	private UsersDto usersToDto(Users users) {
		UsersDto usersDto = new UsersDto();
		BeanUtils.copyProperties(users, usersDto);
		return usersDto;
	}

}
