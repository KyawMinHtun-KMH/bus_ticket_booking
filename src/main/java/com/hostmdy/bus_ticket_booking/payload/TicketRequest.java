package com.hostmdy.bus_ticket_booking.payload;

import com.hostmdy.bus_ticket_booking.domain.Route;
import com.hostmdy.bus_ticket_booking.domain.Ticket;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class TicketRequest {
	
	private Ticket ticket;
	private String typeName;
	private Route route;

}
