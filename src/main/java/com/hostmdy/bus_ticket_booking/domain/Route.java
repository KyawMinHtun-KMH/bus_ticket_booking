package com.hostmdy.bus_ticket_booking.domain;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Route {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message = "startLocation is requred")
	private City startLocation;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message = "endLocation is required")
	private City endLocation;
	
	@OneToMany(mappedBy = "route")
	@JsonIgnore
	private List<Ticket> tickets = new ArrayList<>();
	
	private LocalDateTime createdAt;
	
	@PrePersist
	private void prePersist() {
		createdAt = LocalDateTime.now();
	}
	
}
