package com.hostmdy.bus_ticket_booking.payload;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter @Setter @NoArgsConstructor
public class RouteAndDepatureRequest {
	private String startLocation;
	private String endLocation;
	private LocalDate depature;
}
