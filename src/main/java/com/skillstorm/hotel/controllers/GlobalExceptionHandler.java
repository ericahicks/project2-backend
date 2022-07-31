package com.skillstorm.hotel.controllers;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// This controller class is an Aspect that intercepts any thrown exceptions
// by other controllers, and handles them
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class) // This is similar to @AfterThrowing
	public String invalidBean(Exception e) {
		return "Something went wrong";
	}
}
