package com.hostmdy.bus_ticket_booking.payload;

import com.hostmdy.bus_ticket_booking.domain.Bus;
import com.hostmdy.bus_ticket_booking.domain.Route;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter @Setter @NoArgsConstructor
public class BusRequest {
	private Bus bus;
	private Route route;
	private String typeName;
	private Double price;
	
}
