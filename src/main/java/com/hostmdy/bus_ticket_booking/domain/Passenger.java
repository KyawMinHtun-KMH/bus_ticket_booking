package com.hostmdy.bus_ticket_booking.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Passenger {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "name is required")
	private String name;
	
	@NotNull(message = "gender is required")
	private String gender;
	
	@NotNull(message = "phone number is required")
	private String phoneNumber;
	
	@NotNull(message = "email is required")
	private String email;
	private String specialRequest;
	
	@OneToOne
	@JsonIgnore
	private Order order;
	
}
