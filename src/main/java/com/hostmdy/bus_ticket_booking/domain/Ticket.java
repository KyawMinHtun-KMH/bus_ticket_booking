package com.hostmdy.bus_ticket_booking.domain;

import java.util.HashSet;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
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
	private Boolean status = true ;
	
	@OneToMany(mappedBy = "ticket",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Order> orders = new HashSet<>();
	
	@OneToOne(mappedBy = "ticket")
	@JoinColumn(name = "bus_id")
	private Bus bus;

	public Ticket(Double price) {
		super();
		this.price = price;
	}

	

	
	
	

	
	
	
}
