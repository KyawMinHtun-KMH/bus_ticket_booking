package com.hostmdy.bus_ticket_booking.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String holderName;
	private String cardType;
	private Integer expireMonth;
	private Integer expireYear;
	private Integer cvv;
	
	@OneToOne(mappedBy = "payment")
	@JsonIgnore
	private Order order;
}
