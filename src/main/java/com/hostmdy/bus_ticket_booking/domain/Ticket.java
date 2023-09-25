package com.hostmdy.bus_ticket_booking.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	
	private Boolean status = true ;
	
	@OneToOne(mappedBy = "ticket")
	@JoinColumn(name = "bus_id")
	private Bus bus;

	public Ticket(Double price) {
		super();
		this.price = price;
	}

	

	
	
	

	
	
	
}
