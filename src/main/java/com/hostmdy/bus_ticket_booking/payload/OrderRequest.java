package com.hostmdy.bus_ticket_booking.payload;

import java.util.Set;
import com.hostmdy.bus_ticket_booking.domain.Passenger;
import com.hostmdy.bus_ticket_booking.domain.Payment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class OrderRequest {
	
	private Set<String> seatNumber;
	private Payment payment;
	private Passenger passenger;

}
