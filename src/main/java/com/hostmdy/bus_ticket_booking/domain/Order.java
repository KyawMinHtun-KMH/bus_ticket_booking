package com.hostmdy.bus_ticket_booking.domain;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDateTime bookingDateTime;
	
	@NotNull(message = "seat number is required")
	private Set<String> seatNumber;
	private Integer seatAmount;
	private Double totalPrice;
	
	@PrePersist
    private void prepersist() {
		bookingDateTime = LocalDateTime.now();
	}
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToOne(mappedBy = "order",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "passenger_id")
	private Passenger passenger;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "payment_id")
	private Payment payment;
	
	@ManyToOne
	@JoinColumn(name = "ticket_id")
	private Ticket ticket;

}
