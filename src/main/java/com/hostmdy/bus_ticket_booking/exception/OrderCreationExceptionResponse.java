package com.hostmdy.bus_ticket_booking.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter @RequiredArgsConstructor
public class OrderCreationExceptionResponse {
	
	private final String message;

}
