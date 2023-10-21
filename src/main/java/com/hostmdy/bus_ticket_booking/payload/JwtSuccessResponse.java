package com.hostmdy.bus_ticket_booking.payload;

import java.util.List;

import com.hostmdy.bus_ticket_booking.domain.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class JwtSuccessResponse {
	
	private Boolean success;
	private User user;
	private List<String> roles;

}
