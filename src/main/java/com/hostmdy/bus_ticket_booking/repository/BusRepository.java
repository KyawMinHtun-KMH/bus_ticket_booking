package com.hostmdy.bus_ticket_booking.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.bus_ticket_booking.domain.Bus;
import com.hostmdy.bus_ticket_booking.domain.Route;

public interface BusRepository extends CrudRepository<Bus, Long> {
	List<Bus> findByRoute(Route route);
}
