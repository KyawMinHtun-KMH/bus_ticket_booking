package com.hostmdy.bus_ticket_booking.repository;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.bus_ticket_booking.domain.Order;
import com.hostmdy.bus_ticket_booking.domain.User;

import java.util.List;


public interface OrderRepository extends CrudRepository<Order, Long>{
	
	List<Order> findByUser(User user);
}
