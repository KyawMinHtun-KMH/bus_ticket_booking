package com.hostmdy.bus_ticket_booking.repository;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.bus_ticket_booking.domain.BusType;
import java.util.List;


public interface BusTypeRepository extends CrudRepository<BusType, Long> {
	
	BusType findByTypeName(String typeName);

}
