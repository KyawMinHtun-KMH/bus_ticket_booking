package com.hostmdy.bus_ticket_booking.payload;

import com.hostmdy.bus_ticket_booking.domain.Route;
import com.hostmdy.bus_ticket_booking.domain.Ticket;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class TicketRequest {
	@Valid
	private Ticket ticket;
	
	private String typeName;
	
	@Valid
	private Route route;

}
