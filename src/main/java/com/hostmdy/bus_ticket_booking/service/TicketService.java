package com.hostmdy.bus_ticket_booking.service;

import java.util.Optional;
import com.hostmdy.bus_ticket_booking.domain.Ticket;

public interface TicketService {

	Ticket save(Ticket ticket);
	
	Optional<Ticket> getTicketById(Long ticketId);
}
