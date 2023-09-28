package com.hostmdy.bus_ticket_booking.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;
import com.hostmdy.bus_ticket_booking.domain.Ticket;
import com.hostmdy.bus_ticket_booking.repository.TicketRepository;
import com.hostmdy.bus_ticket_booking.service.TicketService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService{
	
	private final TicketRepository ticketRepository;
	
	@Override
	public Ticket save(Ticket ticket) {
		// TODO Auto-generated method stub
		return ticketRepository.save(ticket);
	}

	@Override
	public Optional<Ticket> getTicketById(Long ticketId) {
		// TODO Auto-generated method stub
		return ticketRepository.findById(ticketId);
	}

}
