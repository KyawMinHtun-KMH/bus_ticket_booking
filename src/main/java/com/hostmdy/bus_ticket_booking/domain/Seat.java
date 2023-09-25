package com.hostmdy.bus_ticket_booking.domain;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Seat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String seatNumber;
	private Boolean status;
	
	@OneToMany(mappedBy = "seat",cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<BusSeat> busSeats = new HashSet<>();
	/*
	@OneToMany(mappedBy = "seat")
	@JsonIgnore
	private Set<BusTypeSeat> busTypeSeats = new HashSet<>();
	
	@ManyToOne
	@JoinColumn(name = "bus_id")
	@JsonIgnore
	private Bus bus;*/

	
	
	
	
}
