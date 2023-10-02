package com.hostmdy.bus_ticket_booking.repository;

import org.springframework.data.repository.CrudRepository;


import com.hostmdy.bus_ticket_booking.domain.Seat;
import com.hostmdy.bus_ticket_booking.domain.Ticket;
import com.hostmdy.bus_ticket_booking.domain.TicketSeat;
import java.util.List;


public interface TicketSeatRepository extends CrudRepository<TicketSeat, Long>{
	
	List<TicketSeat> findByTicket(Ticket ticket);
	
	List<TicketSeat> findBySeat(Seat seat);

}
