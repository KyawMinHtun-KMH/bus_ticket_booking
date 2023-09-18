package com.hostmdy.bus_ticket_booking.domain;


import java.time.LocalDate;
import java.time.LocalDateTime;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Route {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private City startLocation;
	private City endLocation;
	private LocalDate depature;
	private LocalDateTime startDateTime;
	private LocalDateTime endDateTime;
	private Integer duration;
	
}
