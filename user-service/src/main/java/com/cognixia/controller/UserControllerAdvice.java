package com.cognixia.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cognixia.common.exception.UserIDMismatchException;
import com.cognixia.common.exception.UserNotFoundException;
import com.cognixia.common.exception.ErrorResponse;

@RestControllerAdvice
public class UserControllerAdvice {
	
	//for handling bad requests, user input errors
	@ExceptionHandler(UserIDMismatchException.class)
	public ResponseEntity<ErrorResponse> handleUserIDMismatchException(UserIDMismatchException e)
	{
		return ResponseEntity.badRequest().body(new ErrorResponse("USER-400", e.getMessage()));
	}
	
	//for handling not found errors
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("USER-404",  "User not found!" ));
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> methodArgumentNotValidException(MethodArgumentNotValidException ex)
	{
		BindingResult result = ex.getBindingResult();
		List<org.springframework.validation.FieldError> fieldErrors = result.getFieldErrors();

		return ResponseEntity.badRequest().body(new ErrorResponse("USER-400", ex.getMessage(), fieldErrors));
	}

}
