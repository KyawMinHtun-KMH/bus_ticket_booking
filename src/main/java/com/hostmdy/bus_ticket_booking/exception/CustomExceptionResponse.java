package com.hostmdy.bus_ticket_booking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
	
	@ExceptionHandler(TicketNotFoundException.class)
	public ResponseEntity<NotFoundExceptionResponse> handleTicketNotFoundException(TicketNotFoundException ex){
		NotFoundExceptionResponse response = new NotFoundExceptionResponse(ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
	
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<NotFoundExceptionResponse> handleUsernameNotFoundException(UsernameNotFoundException ex){
		NotFoundExceptionResponse response = new NotFoundExceptionResponse(ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
	
	@ExceptionHandler(RouteNotFoundException.class)
	public ResponseEntity<NotFoundExceptionResponse> handleRouteNotFoundException(RouteNotFoundException ex){
		NotFoundExceptionResponse response = new NotFoundExceptionResponse(ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
	
	@ExceptionHandler(BusNotFoundException.class)
	public ResponseEntity<NotFoundExceptionResponse> handleBusTypeNotFoundException(BusNotFoundException ex){
		NotFoundExceptionResponse response = new NotFoundExceptionResponse(ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
	
	@ExceptionHandler(SeatNotFoundException.class)
	public ResponseEntity<NotFoundExceptionResponse> handleSeatNotFoundException(SeatNotFoundException ex){
		NotFoundExceptionResponse response = new NotFoundExceptionResponse(ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
	
	@ExceptionHandler(OrderCreationException.class)
	public ResponseEntity<OrderCreationExceptionResponse> handleOrderCreationException(OrderCreationException ex){
		OrderCreationExceptionResponse response = new OrderCreationExceptionResponse(ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
	
	@ExceptionHandler(OrderNotFoundException.class)
	public ResponseEntity<NotFoundExceptionResponse> handleOrderNotFoundException(OrderNotFoundException ex){
		NotFoundExceptionResponse response = new NotFoundExceptionResponse(ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
	
	@ExceptionHandler(ClaimsNullException.class)
	public ResponseEntity<ClaimsNullExceptionResponse> handleClaimsNullException(ClaimsNullException ex){
		ClaimsNullExceptionResponse response = new ClaimsNullExceptionResponse(ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
	
	
	
}
