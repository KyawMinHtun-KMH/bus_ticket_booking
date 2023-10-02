package com.hostmdy.bus_ticket_booking.payload;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class LoginRequest {
	
	@NotNull(message = "email is required")
	private String username;
	
	@NotNull(message = "password is required")
	private String password;

}
