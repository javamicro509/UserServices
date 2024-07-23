package com.vivek.user.service.user.service.exceptions;

import org.hibernate.annotations.NotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.vivek.user.service.user.service.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResoueceNotFoundException e){
		String message = e.getMessage();
		ApiResponse response = ApiResponse.builder()
			.message(message)
			.success(true)
			.status(HttpStatus.NOT_FOUND)
			.build();
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		
	}
}
