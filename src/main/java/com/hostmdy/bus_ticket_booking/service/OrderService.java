package com.hostmdy.bus_ticket_booking.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.hostmdy.bus_ticket_booking.domain.Order;
import com.hostmdy.bus_ticket_booking.domain.Passenger;
import com.hostmdy.bus_ticket_booking.domain.Payment;
import com.hostmdy.bus_ticket_booking.domain.User;

public interface OrderService{
	
    Order saveOrder(Order order);
	
	Order createOrder(Set<String> seatNumber,Long ticketId,Passenger passenger,Payment payment,Long userId);
	
	Optional<Order> getOrderById(Long id);
	
	List<Order> getallOrdersByUser(User user);
	
	List<Order> getallOrdersByTicket(Long ticketId);
	
	void deleteOrderById(Long id);

}
