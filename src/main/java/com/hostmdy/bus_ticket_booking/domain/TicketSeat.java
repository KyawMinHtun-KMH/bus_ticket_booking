package com.hostmdy.bus_ticket_booking.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter @Setter @NoArgsConstructor
public class TicketSeat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "ticket_id")
	private Ticket ticket;
	
	@ManyToOne
	@JoinColumn(name = "seat_id")
	private Seat seat;

	public TicketSeat(Ticket ticket, Seat seat) {
		super();
		this.ticket = ticket;
		this.seat = seat;
	}
	
	
	
}
