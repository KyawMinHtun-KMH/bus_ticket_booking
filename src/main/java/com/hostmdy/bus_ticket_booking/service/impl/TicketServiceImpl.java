package com.hostmdy.bus_ticket_booking.service.impl;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hostmdy.bus_ticket_booking.domain.Bus;
import com.hostmdy.bus_ticket_booking.domain.Route;
import com.hostmdy.bus_ticket_booking.domain.Seat;
import com.hostmdy.bus_ticket_booking.domain.Ticket;
import com.hostmdy.bus_ticket_booking.domain.TicketSeat;
import com.hostmdy.bus_ticket_booking.exception.TicketNotFoundException;
import com.hostmdy.bus_ticket_booking.repository.BusRepository;
import com.hostmdy.bus_ticket_booking.repository.RouteRepository;
import com.hostmdy.bus_ticket_booking.repository.SeatRepository;
import com.hostmdy.bus_ticket_booking.repository.TicketRepository;
import com.hostmdy.bus_ticket_booking.repository.TicketSeatRepository;
import com.hostmdy.bus_ticket_booking.service.TicketService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
	private final TicketRepository ticketRepository;
	private final BusRepository busRepository;
	private final RouteRepository routeRepository;
	private final SeatRepository seatRepository;
	private final TicketSeatRepository ticketSeatRepository;

	@Override
	public Ticket save(Ticket ticket) {
		// TODO Auto-generated method stub
		return ticketRepository.save(ticket);
	}

	@Override
	public Ticket createTicket(Ticket ticket, String typeName, Route route) {
		// TODO Auto-generated method stub
		Bus bus = busRepository.findByTypeName(typeName);

		ticket.setBus(bus);
		bus.getTickets().add(ticket);

		Integer capacity = bus.getCapacity();
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
		for (final Route dbRoute : routes) {
			if (dbRoute.getStartLocation() == route.getStartLocation()
					&& dbRoute.getEndLocation() == route.getEndLocation()) {
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

		return ticketRepository.findById(ticketId);
	}

	@Override
	public List<Ticket> getAllTicketByRouteAndDepature(String startLocation, String endLocation, LocalDate depature) {
		// TODO Auto-generated method stub
		List<Route> routes = (List<Route>) routeRepository.findAll();
		for (final Route route : routes) {

			if (route.getStartLocation().toString().equals(startLocation)
					&& route.getEndLocation().toString().equals(endLocation)) {
				List<Ticket> tickets = route.getTickets();
				List<Ticket> filterTickets = tickets.stream().filter(t -> t.getDepature().equals(depature)).toList();
				if(filterTickets.isEmpty()) {
					return null;
				}

				return filterTickets;
			}
		}
		return null;
	}

	@Override
	public void deletTicket(Long ticketId) {
		Optional<Ticket> ticketOpt = ticketRepository.findById(ticketId);
		if (ticketOpt.isEmpty()) {
			throw new TicketNotFoundException("Ticket with id = " + ticketId + " is not found");
		}
		Ticket ticket = ticketOpt.get();
		ticket.setBus(null);
		ticket.setRoute(null);
		save(ticket);

		ticketRepository.deleteById(ticketId);
	}
	
	@Transactional
	@Override
	public Ticket updateTicket(Ticket ticket, String typeName, Route route) {
		//System.out.println("#########");
		Bus bus = busRepository.findByTypeName(typeName);
		ticket.setBus(bus);
		bus.getTickets().add(ticket);

		Integer capacity = bus.getCapacity();
		List<Seat> seats = (List<Seat>) seatRepository.findAll();
		
		Long ticketId = ticket.getId();
		Optional<Ticket> ticketOpt = ticketRepository.findById(ticketId);
		Ticket updateTicket = ticketOpt.get();
		ticket.setCreatedAt(updateTicket.getCreatedAt());
		
		if(updateTicket.getBus().getTypeName() != typeName) {
			Set<TicketSeat> ticketSeats = updateTicket.getTicketSeats();
			System.out.println("####"+ticketSeats);
			
			for(final TicketSeat ticketSeat : ticketSeats) {
				
				ticketSeat.setTicket(null);
				ticketSeat.setSeat(null);
				
				ticketSeatRepository.save(ticketSeat);
				
				ticketSeatRepository.deleteById(ticketSeat.getId());
				
			}
			
			Set<TicketSeat> newTicketSeat = new HashSet<>();
			Integer seatAmount = 1;
			for (final Seat seat : seats) {
				if (seatAmount <= capacity) {
					newTicketSeat.add(new TicketSeat(ticket, seat));
					seatAmount++;
				} else {
					break;
				}
			}
			seatAmount = 1;
			updateTicket.setTicketSeats(newTicketSeat);
		}else {
			updateTicket.setTicketSeats(updateTicket.getTicketSeats());
			System.out.println("#####");
		}
		

		List<Route> routes = (List<Route>) routeRepository.findAll();
		for (final Route dbRoute : routes) {
			if (dbRoute.getStartLocation() == route.getStartLocation()
					&& dbRoute.getEndLocation() == route.getEndLocation()) {
				ticket.setRoute(dbRoute);
				dbRoute.getTickets().add(ticket);
			}
		}

		return save(ticket);
	}

	@Override
	public List<TicketSeat> getAllTicketSeats(Ticket ticket) {
		// TODO Auto-generated method stub
		return ticketSeatRepository.findByTicket(ticket);
	}

}
