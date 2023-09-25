package com.hostmdy.bus_ticket_booking.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hostmdy.bus_ticket_booking.domain.Bus;
import com.hostmdy.bus_ticket_booking.domain.BusSeat;
import com.hostmdy.bus_ticket_booking.domain.BusType;
import com.hostmdy.bus_ticket_booking.domain.Route;
import com.hostmdy.bus_ticket_booking.domain.Seat;
import com.hostmdy.bus_ticket_booking.domain.Ticket;
import com.hostmdy.bus_ticket_booking.repository.BusRepository;
import com.hostmdy.bus_ticket_booking.repository.BusTypeRepository;
import com.hostmdy.bus_ticket_booking.repository.RouteRepository;
import com.hostmdy.bus_ticket_booking.repository.SeatRepository;
import com.hostmdy.bus_ticket_booking.service.BusService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class BusServiceImpl implements BusService {
	
	private final BusRepository busRepository;
	private final SeatRepository seatRepository;
	private final BusTypeRepository busTypeRepository;
	private final RouteRepository routeRepository;

	@Override
	public Bus save(Bus bus) {
		// TODO Auto-generated method stub
		return busRepository.save(bus);
	}

	@Override
	public Bus createBus(Bus bus, Route route,String typeName,Double price) {
		// TODO Auto-generated method stub
		BusType busType = busTypeRepository.findByTypeName(typeName);
		Integer capacity = busType.getCapacity();
		List<Seat> seats = (List<Seat>) seatRepository.findAll();
		Integer seatAmount = 1;
		for(final Seat seat : seats) {
			if(seatAmount <= capacity) {
				bus.getBusSeats().add(new BusSeat(bus, seat));
				seatAmount++;
			}else {
				break;
			}
		}
		seatAmount = 1;
		
		route.getBuses().add(bus);
		bus.setRoute(route);
		
		routeRepository.save(route);
		busType.getBuses().add(bus);
		bus.setBusType(busType);
		
		busTypeRepository.save(busType);
		Ticket ticket = new Ticket(price);
		
		bus.setTicket(ticket);
		ticket.setBus(bus);
		return save(bus);
	}

	@Override
	public List<Bus> getAllBus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Bus> getBusById(Long busId) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<Bus> getAllBusByRoute(Route route) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Long busId) {
		// TODO Auto-generated method stub
		
	}

}
