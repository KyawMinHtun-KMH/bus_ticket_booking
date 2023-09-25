package com.hostmdy.bus_ticket_booking.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter 
public class UsernameAlreadyExistExceptionResponse {
	private final String username;
}
