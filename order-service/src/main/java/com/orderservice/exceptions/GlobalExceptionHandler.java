package com.orderservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(OrderNotValidException.class)
	public ResponseEntity<String>OrderNotValidExceptionHandler(OrderNotValidException e){
		String message = e.getMessage();
		return new ResponseEntity<String>(message,HttpStatus.NOT_FOUND);
	}

}
