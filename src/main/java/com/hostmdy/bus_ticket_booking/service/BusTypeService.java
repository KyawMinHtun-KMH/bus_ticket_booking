package com.hostmdy.bus_ticket_booking.service;

import java.util.List;
import java.util.Optional;

import com.hostmdy.bus_ticket_booking.domain.BusType;

public interface BusTypeService {
	BusType save(BusType busType);
	
	List<BusType> getAllBusType();
	
	BusType createBusType(BusType busType);
	
	Optional<BusType> getBusTypeById(Long busTypeId);
	
	BusType updateBusType(BusType busType);
	
	void deletById(Long busTypeId);
} 
