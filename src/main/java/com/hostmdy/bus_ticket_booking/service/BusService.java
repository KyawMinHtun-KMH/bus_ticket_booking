package com.hostmdy.bus_ticket_booking.service;

import java.util.List;
import java.util.Optional;

import com.hostmdy.bus_ticket_booking.domain.Bus;

public interface BusService {
	Bus save(Bus busType);
	
	List<Bus> getAllBusType();
	
	Optional<Bus> getBusTypeById(Long busTypeId);
	
	/*void deletById(Long busTypeId);*/
} 
