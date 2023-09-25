package com.hostmdy.bus_ticket_booking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionResponse {
	
	@ExceptionHandler(UsernameAlreadyExistsException.class)
	public ResponseEntity<UsernameAlreadyExistExceptionResponse> handleUsernameAlreadyExistsException(UsernameAlreadyExistsException ex){
		UsernameAlreadyExistExceptionResponse response = new UsernameAlreadyExistExceptionResponse(ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
	
	@ExceptionHandler(RoleNotFoundException.class)
	public ResponseEntity<NotFoundExceptionResponse> handleRoleNotFoundException(RoleNotFoundException ex){
		NotFoundExceptionResponse response = new NotFoundExceptionResponse(ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
	
}