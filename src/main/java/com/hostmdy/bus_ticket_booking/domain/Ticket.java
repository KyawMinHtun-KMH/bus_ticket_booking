package com.hostmdy.bus_ticket_booking.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Double price;
	private Boolean status = true;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate depature;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = ISO.DATE_TIME)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime startDateTime;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = ISO.DATE_TIME)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime endDateTime;
	
	@OneToMany(mappedBy = "ticket",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Order> orders = new HashSet<>();
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "busType_id")
	private BusType busType;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "route_id")
	private Route route;
	
	@OneToMany(mappedBy = "ticket",cascade = CascadeType.PERSIST)
	@JsonIgnore
	private Set<TicketSeat> ticketSeats = new HashSet<>();
	
	/*
	@OneToOne(mappedBy = "ticket")
	@JoinColumn(name = "bus_id")
	private Bus bus;*/

	public Ticket(Double price) {
		super();
		this.price = price;
	}

	

	
	
	

	
	
	
}
