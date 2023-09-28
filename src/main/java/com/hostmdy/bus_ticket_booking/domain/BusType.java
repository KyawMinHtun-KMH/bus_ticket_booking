package com.hostmdy.bus_ticket_booking.domain;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class BusType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String typeName;
	private Integer capacity;
	
	@OneToMany(mappedBy = "busType")
	@JsonIgnore
	private List<Ticket> tickets = new ArrayList<>();
	
	
	
	/*@OneToMany(mappedBy = "busType", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<BusTypeSeat> busTypeSeats= new HashSet<>();

	@Override
	public String toString() {
		return "BusType [busTypeSeats=" + busTypeSeats + "]";
	}*/

	

	
	
	
	
	

}
