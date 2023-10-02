package com.hostmdy.bus_ticket_booking.service;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.hostmdy.bus_ticket_booking.domain.Route;
import com.hostmdy.bus_ticket_booking.domain.Ticket;

public interface TicketService {
	Ticket save(Ticket ticket);
	
	Ticket createTicket(Ticket ticket,String typeName,Route route);
	
	List<Ticket> getAllTicket();
	
	Optional<Ticket> getTicketById(Long ticketId);
	
	List<Ticket> getAllTicketByRouteAndDepature(String startLocation,String endLocation,LocalDate depature);
	
	void deletTicket(Long ticketId);
	
	Ticket updateTicket(Ticket ticket,String typeName,Route route);
}
