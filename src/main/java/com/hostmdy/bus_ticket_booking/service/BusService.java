package com.hostmdy.bus_ticket_booking.service;

import java.util.List;
import java.util.Optional;

import com.hostmdy.bus_ticket_booking.domain.Bus;
import com.hostmdy.bus_ticket_booking.domain.BusType;
import com.hostmdy.bus_ticket_booking.domain.Route;
import com.hostmdy.bus_ticket_booking.domain.Ticket;

public interface BusService {
	Bus save(Bus bus);
	
	Bus createBus(Bus bus,Route route,String typeName,Double price);
	
	List<Bus> getAllBus();
	
	Optional<Bus> getBusById(Long busId);
	
	List<Bus> getAllBusByRoute(Route route);
	
	void deleteById(Long busId);
}
