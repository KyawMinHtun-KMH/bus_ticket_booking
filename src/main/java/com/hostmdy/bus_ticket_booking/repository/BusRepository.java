package com.hostmdy.bus_ticket_booking.repository;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.bus_ticket_booking.domain.Bus;


public interface BusRepository extends CrudRepository<Bus, Long> {
	Bus findByTypeName(String typeName);
}
