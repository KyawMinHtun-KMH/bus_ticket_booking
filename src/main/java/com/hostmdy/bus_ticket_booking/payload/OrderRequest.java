package com.hostmdy.bus_ticket_booking.payload;

import java.util.Set;
import com.hostmdy.bus_ticket_booking.domain.Passenger;
import com.hostmdy.bus_ticket_booking.domain.Payment;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class OrderRequest {
	
	@NotNull(message = "seat number is required")
	private Set<String> seatNumber;
	
	@Valid
	@NotNull(message = "payment is required")
	private Payment payment;
	
	@Valid
	@NotNull(message = "passenger information is required")
	private Passenger passenger;

}
