package com.hostmdy.bus_ticket_booking.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hostmdy.bus_ticket_booking.domain.BusType;
import com.hostmdy.bus_ticket_booking.domain.Route;
import com.hostmdy.bus_ticket_booking.domain.Seat;
import com.hostmdy.bus_ticket_booking.domain.Ticket;
import com.hostmdy.bus_ticket_booking.domain.TicketSeat;
import com.hostmdy.bus_ticket_booking.exception.TicketNotFoundException;
import com.hostmdy.bus_ticket_booking.repository.BusTypeRepository;
import com.hostmdy.bus_ticket_booking.repository.RouteRepository;
import com.hostmdy.bus_ticket_booking.repository.SeatRepository;
import com.hostmdy.bus_ticket_booking.repository.TicketRepository;
import com.hostmdy.bus_ticket_booking.service.TicketService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
	private final TicketRepository ticketRepository;
	private final BusTypeRepository busTypeRepository;
	private final RouteRepository routeRepository;
	private final SeatRepository seatRepository;

	@Override
	public Ticket save(Ticket ticket) {
		// TODO Auto-generated method stub
		return ticketRepository.save(ticket);
	}

	@Override
	public Ticket createTicket(Ticket ticket, String typeName, Route route) {
		// TODO Auto-generated method stub
		BusType busType = busTypeRepository.findByTypeName(typeName);
		
		ticket.setBusType(busType);
		busType.getTickets().add(ticket);
		
		Integer capacity = busType.getCapacity();
		List<Seat> seats = (List<Seat>) seatRepository.findAll();
		Integer seatAmount = 1;
		for (final Seat seat : seats) {
			if (seatAmount <= capacity) {
				ticket.getTicketSeats().add(new TicketSeat(ticket, seat));
				seatAmount++;
			} else {
				break;
			}
		}
		seatAmount = 1;

		
		List<Route> routes = (List<Route>) routeRepository.findAll();
		for(final Route dbRoute : routes) {
			if(dbRoute.getStartLocation() == route.getStartLocation() && dbRoute.getEndLocation() == route.getEndLocation()) {
				ticket.setRoute(dbRoute);
				dbRoute.getTickets().add(ticket);
			}
		}
		
		return save(ticket);
	}

	@Override
	public List<Ticket> getAllTicket() {
		// TODO Auto-generated method stub
		return (List<Ticket>) ticketRepository.findAll();
	}

	@Override
	public Optional<Ticket> getTicketById(Long ticketId) {
		// TODO Auto-generated method stub
		Optional<Ticket> ticketOpt = ticketRepository.findById(ticketId);
		if(ticketOpt.isEmpty()) {
			throw new TicketNotFoundException("Ticket with id = "+ticketId+" is not found");
		}
		return ticketOpt;
	}

}
