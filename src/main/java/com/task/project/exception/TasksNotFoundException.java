package com.task.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TasksNotFoundException extends RuntimeException {
	private String message;
	
	public TasksNotFoundException(String message) {
		super(message);
		this.message = message;
	}
}
