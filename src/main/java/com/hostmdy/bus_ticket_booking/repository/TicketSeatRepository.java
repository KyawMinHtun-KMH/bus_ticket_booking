package com.hostmdy.bus_ticket_booking.repository;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.bus_ticket_booking.domain.TicketSeat;

public interface TicketSeatRepository extends CrudRepository<TicketSeat, Long> {

}
