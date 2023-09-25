package com.hostmdy.bus_ticket_booking.service;

import java.util.List;
import java.util.Optional;
import com.hostmdy.bus_ticket_booking.domain.Order;
import com.hostmdy.bus_ticket_booking.domain.Passenger;
import com.hostmdy.bus_ticket_booking.domain.Payment;
import com.hostmdy.bus_ticket_booking.domain.Ticket;
import com.hostmdy.bus_ticket_booking.domain.User;

public interface OrderService{
	
    Order saveOrder(Order order);
	
	Order createOrder(Order order,Ticket ticket,Passenger passenger,Payment payment,User user);
	
	Optional<Order> getOrderById(Long id);
	
	List<Order> getallOrdersByUser(User user);
	
	void deleteOrderById(Long id);

}
