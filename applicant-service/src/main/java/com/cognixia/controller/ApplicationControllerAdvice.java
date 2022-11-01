package com.cognixia.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cognixia.common.exception.ApplicationIDMismatchException;
import com.cognixia.common.exception.ApplicationNotFoundException;
import com.cognixia.common.exception.ErrorResponse;
import com.cognixia.common.exception.InvalidAgeException;

@RestControllerAdvice
public class ApplicationControllerAdvice {
	
	//for handling bad requests, user input errors
	@ExceptionHandler(ApplicationIDMismatchException.class)
	public ResponseEntity<ErrorResponse> handleUserIDMismatchException(ApplicationIDMismatchException e)
	{
		return ResponseEntity.badRequest().body(new ErrorResponse("APP-400", e.getMessage()));
	}
	
	//for handling not found errors
	@ExceptionHandler(ApplicationNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleUserNotFoundException(ApplicationNotFoundException e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("APP-404",  "Application not found!" ));
	}
	
	//for handling bad requests, user input errors
	@ExceptionHandler(InvalidAgeException.class)
	public ResponseEntity<ErrorResponse> handleInvalidAgeException(InvalidAgeException e)
	{
		return ResponseEntity.badRequest().body(new ErrorResponse("AGE-400", "Must be above 18 years to register!"));
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> methodArgumentNotValidException(MethodArgumentNotValidException ex)
	{
		BindingResult result = ex.getBindingResult();
		List<org.springframework.validation.FieldError> fieldErrors = result.getFieldErrors();

		return ResponseEntity.badRequest().body(new ErrorResponse("APP-400", ex.getMessage(), fieldErrors));
	}

}
