package com.task.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class APIException extends RuntimeException {
	private String messagae;
	
	public APIException(String message) {
		super(message);
		this.messagae = message;
	}
}
