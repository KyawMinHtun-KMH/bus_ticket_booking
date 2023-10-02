package com.hostmdy.bus_ticket_booking.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "holderName is required")
	private String holderName;
	
	@NotNull(message = "paymentType is null")
	@Enumerated(EnumType.STRING)
	private PaymentType paymentType;
	
	@NotNull(message = "phone number is required")
	private String phoneNo;
	
	@NotNull(message = "transactionId is required")
	private String transactionId;
	
	@OneToOne(mappedBy = "payment")
	@JsonIgnore
	private Order order;
}
