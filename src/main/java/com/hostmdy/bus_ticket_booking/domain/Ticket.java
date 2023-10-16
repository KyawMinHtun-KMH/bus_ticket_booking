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
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@DecimalMin(value = "0",inclusive = false,message = "price is less than 1")
	@NotNull(message = "price is required")
	private Double price;
	
	private Boolean status = true;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@NotNull(message = "depature is required")
	private LocalDate depature;
	
	/*@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = ISO.DATE_TIME)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")*/
	@NotNull(message = "startDateTime is required")
	private LocalDateTime startDateTime;
	
	/*@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = ISO.DATE_TIME)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")*/
	@NotNull(message = "endDateTime is required")
	private LocalDateTime endDateTime;
	
	@OneToMany(mappedBy = "ticket",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Order> orders = new HashSet<>();
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "busType_id")
	private Bus bus;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "route_id")
	private Route route;
	
	@OneToMany(mappedBy = "ticket",cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<TicketSeat> ticketSeats = new HashSet<>();
	
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	@PrePersist
	private void prePersist() {
		createdAt = LocalDateTime.now();
	}
	
	@PreUpdate
	private void preUpdate() {
		updatedAt = LocalDateTime.now();
	}

	public Ticket(Double price) {
		super();
		this.price = price;
	}
	
}
