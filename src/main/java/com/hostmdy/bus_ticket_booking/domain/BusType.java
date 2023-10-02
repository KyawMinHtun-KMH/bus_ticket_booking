package com.hostmdy.bus_ticket_booking.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class BusType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Type name is required")
	private String typeName;
	
	@DecimalMin(value = "1",inclusive = true,message = "capacity must be greater than 0")
	@NotNull(message = "capacity is required")
	private Integer capacity;
	
	@OneToMany(mappedBy = "busType")
	@JsonIgnore
	private List<Ticket> tickets = new ArrayList<>();
	
	private LocalDateTime createdAt;
	
	@PrePersist
	private void prePersist() {
		createdAt = LocalDateTime.now();
	}
	
	

	

	
	
	
	
	

}
