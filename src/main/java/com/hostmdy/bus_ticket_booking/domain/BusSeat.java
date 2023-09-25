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

@Entity
@Getter @Setter @NoArgsConstructor
public class BusSeat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "bus_id")
	private Bus bus;
	
	@ManyToOne
	@JoinColumn(name = "seat_id")
	private Seat seat;

	public BusSeat(Bus bus, Seat seat) {
		super();
		this.bus = bus;
		this.seat = seat;
	}
	
	
}
